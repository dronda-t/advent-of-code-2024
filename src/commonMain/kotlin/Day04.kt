object Day04 : AdventDay {
    private const val xmasStr = "MAS"

    sealed class Direction(val move: (x: Int, y: Int) -> Pair<Int, Int>) {
        object Up : Direction({ x,y -> x to (y - 1) })
        object RightUp : Direction({ x,y -> (x + 1) to (y - 1) })
        object Right: Direction({ x,y -> (x + 1) to y})
        object DownRight : Direction({ x,y -> (x + 1) to (y + 1)})
        object Down : Direction({ x,y -> x to (y + 1)})
        object LeftDown : Direction({ x,y -> (x - 1) to (y + 1)})
        object Left : Direction({ x,y -> (x - 1) to y})
        object LeftUp : Direction({ x,y -> (x - 1) to (y - 1)})

        companion object {
            val values = listOf(
                Up,
                RightUp,
                Right,
                DownRight,
                Down,
                LeftDown,
                Left,
                LeftUp
            )
        }
    }

    private fun findXmas(
        grid: List<List<Char>>,
        position: Pair<Int, Int>,
        currentChar: Iterator<Char>,
        direction: Direction,
    ): Boolean {
        val yRange = grid.indices
        val xRange = grid[0].indices
        val (oldX, oldY) = position
        val (x, y) = direction.move(oldX, oldY)

        if (x !in xRange || y !in yRange) {
            return false
        }
        if (!currentChar.hasNext()) {
            return false
        }
        val gridChar = grid[y][x]

        val nextChar = currentChar.next()
        if (gridChar != nextChar) {
            return false
        }
        if (!currentChar.hasNext()) {
            return true
        }
        return findXmas(grid, x to y, currentChar, direction)
    }

    override fun part1(lines: List<String>): Int {
        val grid: List<List<Char>> = lines.map {
            it.toList()
        }

        var totalCount = 0
        for (y in grid.indices) {
            for (x in grid[0].indices) {
                if (grid[y][x] == 'X') {
                    Direction.values.map { direction ->
                        findXmas(grid, x to y, xmasStr.iterator(), direction)
                    }.count { it }.let { totalCount += it }
                }
            }
        }
        return totalCount
    }

    override fun part2(lines: List<String>): Int {
        val grid: List<List<Char>> = lines.map {
            it.toList()
        }

        var totalCount = 0
        for (y in grid.indices) {
            for (x in grid[0].indices) {
                if (grid[y][x] == 'A') {
                    val direction1 = findXmas(grid, (x - 2) to (y - 2), xmasStr.iterator(), Direction.DownRight)
                            || findXmas(grid, (x + 2) to (y + 2), xmasStr.iterator(), Direction.LeftUp)

                    val direction2 = findXmas(grid, (x - 2) to (y + 2), xmasStr.iterator(), Direction.RightUp)
                            || findXmas(grid, (x + 2) to (y - 2), xmasStr.iterator(), Direction.LeftDown)
                    if (direction1 && direction2) {
                        totalCount += 1
                    }
                }
            }
        }
        return totalCount
    }
}