package benchmark

import Day01
import kotlinx.benchmark.Benchmark
import kotlinx.benchmark.BenchmarkMode
import kotlinx.benchmark.BenchmarkTimeUnit
import kotlinx.benchmark.Mode
import kotlinx.benchmark.OutputTimeUnit
import kotlinx.benchmark.Scope
import kotlinx.benchmark.State
import readInput

@State(Scope.Benchmark)
//@BenchmarkMode(Mode.AverageTime)
//@OutputTimeUnit(BenchmarkTimeUnit.MILLISECONDS)
class Benchmarks {
    private val input = readInput("Day02")

    @Benchmark
    fun `Day 2 part 2`() {
        Day02.part2(input)
    }

    @Benchmark
    fun `Day 2 part 2 diff approach`() {
        Day02.part2(input)
    }
}