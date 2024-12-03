
fun main() {
    val input = readInput("Day02")
//    input.map {
//        val splits = it.split(" ").map { it.toInt() }
//        val firstSecond = splits[0] > splits[1]
//        val firstThird = splits[0] < splits[2]
//        if (firstSecond && firstThird && firstSecond == firstThird) {
//            println(splits)
//        }
//    }
    println(Day02_2.part2(input))
}