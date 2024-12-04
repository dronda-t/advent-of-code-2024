
/*
jvm summary:
Benchmark             Mode  Cnt  Score   Error  Units
Benchmarks.Day3part2  avgt    5  0.259 ± 0.002  ms/op

linuxX64 summary:
Benchmark             Mode  Cnt  Score   Error  Units
Benchmarks.Day3part2  avgt    5  1.413 ± 0.004  ms/op
 */
object Day03 : AdventDay {
    private val mulRegex = """mul\((\d+),(\d+)\)""".toRegex()
    override fun part1(lines: List<String>): Int {
        return lines.sumOf { line ->
            mulRegex.findAll(line).sumOf {
                val x = it.groups[1]!!.value.toInt()
                val y = it.groups[2]!!.value.toInt()
                x * y
            }
        }
    }

    private val mulRegex2 = """(mul\((\d+),(\d+)\)|((do|don't)\(\)))""".toRegex()
    override fun part2(lines: List<String>): Int {
        var doFn = true
        return lines.sumOf { line ->
            mulRegex2.findAll(line).sumOf { matchGroup ->
                var value = 0
                when {
                    matchGroup.value.contains("don't") -> doFn = false
                    matchGroup.value.contains("do") -> doFn = true
                    matchGroup.value.contains("mul") -> {
                        if (doFn) {
                            val x = matchGroup.groups[2]!!.value.toInt()
                            val y = matchGroup.groups[3]!!.value.toInt()
                            value = x * y
                        }
                    }
                    else -> error("Illegal state")
                }
                value
            }
        }
    }
}