package day7

import readInput


enum class CamelCard(val value: String) {
    A("A"),
    K("K"),
    Q("Q"),
    J("J"),
    T("T"),
    `9`("9"),
    `8`("8"),
    `7`("7"),
    `6`("6"),
    `5`("5"),
    `4`("4"),
    `3`("3"),
    `2`("2")
}


data class Hand(val cards: List<CamelCard>, val bid: Long)

class CamelCardReader(val fileName: String) {
    fun readHands(): List<Hand> {
        val lines = readInput(fileName)

        return lines.map { it.split(" ") }
            .map { line ->
                val cards = line.first().map { CamelCard.valueOf("$it") }
                val bid = line.last().toLong()
                Hand(cards, bid)
            }
    }
}