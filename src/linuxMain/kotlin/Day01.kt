import kotlin.math.absoluteValue

fun day01() {
    fun part1(lines: List<String>): Int {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        lines.forEach { line ->
            val (first, second) = line.split(" ").filter { it.isNotBlank() }

            list1.add(first.toInt())
            list2.add(second.toInt())
        }
        return list1.sorted().zip(list2.sorted()).sumOf { (first, second) ->
            (first - second).absoluteValue
        }
    }

    fun part2(lines: List<String>): Int {
        val nums = mutableListOf<Int>()
        val occurances = hashMapOf<Int, Int>()
        lines.forEach { line ->
            val (first, second) = line.split(" ").filter { it.isNotBlank() }
            val secondNum = second.toInt()

            nums.add(first.toInt())
            val count = occurances.getOrPut(secondNum) { 0 }
            occurances[secondNum] = count + 1
        }
        return nums.sumOf { number ->
            val occuranceCount = occurances[number] ?: 0
            number * occuranceCount
        }
    }

    val result = readInput("Day01")
//    println(part1(result))
    part2(result)
}
