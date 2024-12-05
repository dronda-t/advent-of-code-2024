import kotlin.math.absoluteValue

/**
jvm summary:
Benchmark                          Mode  Cnt     Score     Error  Units
Benchmarks.Day2part2              thrpt    5  4878.767 ± 105.972  ops/s
Benchmarks.Day2part2diffapproach  thrpt    5  5496.809 ± 502.555  ops/s

linuxX64 summary:
Benchmark                          Mode  Cnt     Score    Error    Units
Benchmarks.Day2part2              thrpt    5  1240.524 ± 21.338  ops/sec
Benchmarks.Day2part2diffapproach  thrpt    5  1534.222 ± 18.371  ops/sec
*/

object Day02 : AdventDay {
    private fun validLevel(levels: List<Int>): Boolean {
        val isIncreasing = levels[0] < levels[1]

        for (i in 0..<(levels.size - 1) ) {
            val first = levels[i]
            val second = levels[i + 1]
            val diff = (first - second).absoluteValue
            if (isIncreasing && (first >= second || diff > 3)) {
                return false
            } else if (!isIncreasing && (second >= first || diff > 3)) {
                return false
            }
        }
        return true
    }

    override fun part1(lines: List<String>): Int {
        return lines.map { line ->
            val levels = line.split(" ").map { it.toInt() }
            validLevel(levels)
        }.count { it }
    }

    private fun validLevel2(levels: List<Int>): Boolean {
        val isIncreasing = levels[0] < levels[1]

        for (i in 0..<(levels.size - 1) ) {
            val first = levels[i]
            val second = levels[i + 1]
            val diff = (first - second).absoluteValue
            if (isIncreasing && (first >= second || diff > 3)) {
                return false
            } else if (!isIncreasing && (second >= first || diff > 3)) {
                return false
            }
        }
        return true
    }

    override fun part2(lines: List<String>): Int {
        return lines.map { line ->
            val levels = line.split(" ").map { it.toInt() }
            var valid = validLevel2(levels)
            var pos = 0
            while (!valid && pos < levels.size) {
                val newLevels = levels.toMutableList().apply { removeAt(pos) }
                valid = validLevel2(newLevels)
                pos++
            }
            valid

        }.count { it }
    }
}

object Day02_2 : AdventDay {
    override fun part1(lines: List<String>): Int {
        return Day02.part1(lines)
    }

    fun isIncreasing(levels: List<Int>, pos: Int): Boolean {
        return levels[pos] < levels[pos + 1]
    }

    private fun validLevel2(levels: MutableList<Int>, pos: Int, isIncreasing: Boolean, isModified: Boolean = false): Boolean {
        if (pos + 1 >= levels.size) {
            return true
        }
        val first = levels[pos]
        val second = levels[pos + 1]
        val diff = (first - second).absoluteValue
        if (isIncreasing && (first >= second || diff > 3)) {
            if (!isModified) {
                val newList = levels.apply { removeAt(pos + 1) }
                var isIncreasing = isIncreasing
                if (pos == 0) {
                    isIncreasing = isIncreasing(newList, pos)
                }
                return validLevel2(newList, pos, isIncreasing, true)
            } else {
                return false
            }
        } else if (!isIncreasing && (second >= first || diff > 3)) {
            if (!isModified) {
                val newList = levels.apply { removeAt(pos + 1) }
                var isIncreasing = isIncreasing
                if (pos == 0) {
                    isIncreasing = isIncreasing(newList, pos)
                }
                return validLevel2(newList, pos, isIncreasing, true)
            } else {
                return false
            }
        }
        return validLevel2(levels, pos + 1, isIncreasing, isModified)
    }

    override fun part2(lines: List<String>): Int {
        return lines.map { line ->
            val levels = line.split(" ").map { it.toInt() }
            var isIncreasing = isIncreasing(levels, 0)
            var result = validLevel2(levels.toMutableList(), 0, isIncreasing)
            if (!result) {
                val newLevels = levels.drop(1)
                isIncreasing = isIncreasing(newLevels, 0)
                result = validLevel2(newLevels.toMutableList(), 0, isIncreasing, true)
            }
            result

        }.count { it }
    }
}
