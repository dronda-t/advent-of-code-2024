
object Day05 : AdventDay {
    data class Rule(var before: HashSet<Int> = hashSetOf(), var after: HashSet<Int> = hashSetOf())

    private fun addRule(rules: HashMap<Int, Rule>, first: Int, second: Int) {
        rules.computeWithDefault(first, Rule()) { value ->
            value.after.add(second)
            value
        }
        rules.computeWithDefault(second, Rule()) { value ->
            value.before.add(first)
            value
        }
    }

    override fun part1(lines: List<String>): Int {
        val ruleMap = hashMapOf<Int, Rule>()
        val pageUpdates: MutableList<List<Int>> = arrayListOf()
        lines.asSequence().filter { it.isNotBlank() }.forEach { line ->
            if (line.contains('|')) {
                val (first, second) = line.split('|').map { it.toInt() }
                addRule(ruleMap, first, second)
            } else {
                val pageUpdate = line.split(',').map { it.toInt() }
                pageUpdates.add(pageUpdate)
            }
        }

        return pageUpdates.sumOf { pageUpdate ->
            if (isCorrectOrder(pageUpdate, ruleMap)) {
                pageUpdate[pageUpdate.size / 2]
            } else {
                0
            }
        }
    }

    private fun isCorrectOrder(pageUpdate: List<Int>, ruleMap: HashMap<Int, Rule>): Boolean {
        pageUpdate.forEachIndexed { index, update ->
            val rules = ruleMap[update] ?: error("not found")
            for (i in 0..<index) {
                if (pageUpdate[i] in rules.after) {
                    return false
                }
            }
            for (i in (index + 1)..pageUpdate.lastIndex) {
                if (pageUpdate[i] in rules.before) {
                    return false
                }
            }
        }
        return true
    }

    class RuleComparator(private val ruleMap: HashMap<Int, Rule>) : Comparator<Int> {
        override fun compare(a: Int, b: Int): Int {
            val rules = ruleMap[a] ?: return 0
            return when (b) {
                in rules.before -> 1
                in rules.after -> -1
                else -> 0
            }
        }
    }

    override fun part2(lines: List<String>): Int {
        val ruleMap = hashMapOf<Int, Rule>()
        val pageUpdates: MutableList<List<Int>> = arrayListOf()
        lines.asSequence().filter { it.isNotBlank() }.forEach { line ->
            if (line.contains('|')) {
                val (first, second) = line.split('|').map { it.toInt() }
                addRule(ruleMap, first, second)
            } else {
                val pageUpdate = line.split(',').map { it.toInt() }
                pageUpdates.add(pageUpdate)
            }
        }
        val ruleComparator = RuleComparator(ruleMap)

        return pageUpdates.sumOf { pageUpdate ->
            if (isCorrectOrder(pageUpdate, ruleMap)) {
                0
            } else {
                val sorted = pageUpdate.sortedWith(ruleComparator)
                sorted[sorted.size / 2]
            }
        }
    }
}

fun <K, V> MutableMap<K, V>.computeWithDefault(key: K, defaultValue: V, compute: (V) -> V): V {
    val currentValue = get(key) ?: defaultValue
    val newValue = compute(currentValue)
    set(key, newValue)
    return newValue
}