package day3

data class NumberCoords(val value: Int, val startsAt: Int, val endsAt: Int) {
    fun isAdjacentToAny(symbolIndexes: List<Int>): Boolean {
        return symbolIndexes.any { it in startsAt - 1..endsAt + 1 }
    }
}

object SchematicReader {

    const val SYMBOLS = "*/=+%@#&-\\\$"

    fun readPartsSum(window: List<String>): Int {
        require(window.size == 3) { "Window must have size 3" }

        val symbolIndexes = window.flatMap { row ->
            row.mapIndexed { index, char -> index to char }
                .filter { it.second in SYMBOLS }
                .map { it.first }
        }.distinct().sorted()
        if (symbolIndexes.isEmpty()) return 0

        val numbersForRow = getNumbersForRow(window[1])

        return numbersForRow
            .toList().filter { it.isAdjacentToAny(symbolIndexes) }
            .sumOf { it.value }
    }

    fun readGears(window: List<String>): Int {
        require(window.size == 3) { "Window must have size 3" }

        val topRowNumbers = getNumbersForRow(window[0])
        val middleRowNumbers = getNumbersForRow(window[1])
        val bottomRowNumbers = getNumbersForRow(window[2])

        val allParts = topRowNumbers.plus(middleRowNumbers).plus(bottomRowNumbers)

        val gearIndices = window[1].mapIndexed { index, char -> index to char }
                .filter { it.second == '*' }
                .map { it.first }
        if (gearIndices.isEmpty()) return 0


        val gearRatios = gearIndices.map { gearIndex ->
            val adjacentParts = allParts.filter { it.isAdjacentToAny(listOf(gearIndex)) }
            if (adjacentParts.size != 2) return@map 0
            adjacentParts.first().value * adjacentParts.last().value
        }.sum()
        return gearRatios
    }

    private fun getNumbersForRow(line: String): List<NumberCoords> {
        val numberPattern = "\\d+".toRegex()
        return numberPattern.findAll(line)
            .map { match -> NumberCoords(match.value.toInt(), match.range.first, match.range.last) }
            .toList()
    }
}