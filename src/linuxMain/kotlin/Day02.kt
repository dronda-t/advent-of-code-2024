import kotlin.math.absoluteValue

/**
 * linuxX64 summary:
 * Benchmark                               Mode  Cnt     Score   Error    Units
 * Benchmarks.Day 2 part 2 diff approach  thrpt    5  1218.986 ± 3.568  ops/sec
 * Benchmarks.Day 2 part 2                thrpt    5  1131.801 ± 5.350  ops/sec
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
