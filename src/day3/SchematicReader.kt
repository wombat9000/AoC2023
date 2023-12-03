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

        // get all symbols from window
        val symbolIndexes = window.flatMap(::getSymbolIndicesFromLine).distinct()
        if (symbolIndexes.isEmpty()) return 0

        // sum up relevant part numbers for middle line
        return getPartsFromLine(window[1])
            .toList().filter { it.isAdjacentToAny(symbolIndexes) }
            .sumOf { it.value }
    }

    fun readGears(window: List<String>): Int {
        require(window.size == 3) { "Window must have size 3" }

        // get all parts from window
        val allParts = window.flatMap(::getPartsFromLine)

        // sum up gear ratios for middle line
        return window[1].mapIndexed { index, char -> index to char }
            .filter { it.second == '*' }
            .map { it.first }
            .sumOf { gearIndex ->
                val adjacentParts = allParts.filter { it.isAdjacentToAny(listOf(gearIndex)) }
                if (adjacentParts.size != 2) return@sumOf 0
                adjacentParts.first().value * adjacentParts.last().value
            }
    }

    private fun getPartsFromLine(line: String): List<NumberCoords> {
        val numberPattern = "\\d+".toRegex()
        return numberPattern.findAll(line)
            .map { match -> NumberCoords(match.value.toInt(), match.range.first, match.range.last) }
            .toList()
    }

    private fun getSymbolIndicesFromLine(line: String): List<Int> {
        return line.mapIndexed { index, char -> index to char }
            .filter { it.second in SYMBOLS }
            .map { it.first }
    }
}