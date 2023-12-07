package day7

import readInput


enum class CamelCard(val value: Int) {
    A(14),
    K(13),
    Q(12),
    T(10),
    `9`(9),
    `8`(8),
    `7`(7),
    `6`(6),
    `5`(5),
    `4`(4),
    `3`(3),
    `2`(2),
    J(1)
}

enum class WinCondition {
    HIGH_CARD,
    PAIR,
    TWO_PAIR,
    THREE_OF_A_KIND,
    FULL_HOUSE,
    FOUR_OF_A_KIND,
    FIVE_OF_A_KIND;

    fun applyJokerUpgrade(): WinCondition {
        return when (this) {
            HIGH_CARD -> PAIR
            PAIR -> THREE_OF_A_KIND
            TWO_PAIR -> FULL_HOUSE
            THREE_OF_A_KIND -> FOUR_OF_A_KIND
            FOUR_OF_A_KIND -> FIVE_OF_A_KIND
            else -> this
        }
    }
}

data class Hand(val cards: List<CamelCard>, val bid: Long) : Comparable<Hand> {

    val winCondition: WinCondition = determineStandardWinCondition()

    private fun determineStandardWinCondition(): WinCondition {
        val jokers = cards.filter { it == CamelCard.J }.size
        val regularCards = cards.filter { it != CamelCard.J }
        val pairs = regularCards.groupBy { it }.filter { it.value.size == 2 }
        val triples = regularCards.groupBy { it }.filter { it.value.size == 3 }
        val fourOfAKind = regularCards.groupBy { it }.filter { it.value.size == 4 }
        val fiveOfAKind = regularCards.groupBy { it }.filter { it.value.size == 5 }

        val originalCondition = when {
            fiveOfAKind.size == 1 -> WinCondition.FIVE_OF_A_KIND
            fourOfAKind.size == 1 -> WinCondition.FOUR_OF_A_KIND
            triples.size == 1 && pairs.size == 1 -> WinCondition.FULL_HOUSE
            triples.size == 1 -> WinCondition.THREE_OF_A_KIND
            pairs.size == 2 -> WinCondition.TWO_PAIR
            pairs.size == 1 -> WinCondition.PAIR
            else -> WinCondition.HIGH_CARD
        }

        return (1..jokers).fold(originalCondition) { acc, _ -> acc.applyJokerUpgrade() }
    }

    override fun compareTo(other: Hand): Int {
        return if (winCondition != other.winCondition) {
            winCondition.compareTo(other.winCondition)
        } else {
            // compare cards in order until one is better than the other:
            cards.zip(other.cards).forEach { (card1, card2) ->
                if (card1 != card2) {
                    return card1.value.compareTo(card2.value)
                }
            }
            // if we get here, the hands are equal:
            0
        }
    }
}

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