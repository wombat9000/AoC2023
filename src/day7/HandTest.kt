package day7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class HandTest {

    @Test
    fun `highest card is stronger than lower card`() {
        val lowerCard = Hand(listOf(CamelCard.`3`, CamelCard.`2`, CamelCard.`4`, CamelCard.`5`, CamelCard.`6`), 0L)
        val higherCard = Hand(listOf(CamelCard.`3`, CamelCard.`2`, CamelCard.`4`, CamelCard.`5`, CamelCard.`7`), 0L)

        assertTrue(higherCard > lowerCard)
    }

    @Test
    fun `secondary win condition is applied if there are two high cards`() {
        val betterSecondary = Hand(listOf(CamelCard.`4`, CamelCard.`2`, CamelCard.`3`, CamelCard.`5`, CamelCard.`6`), 0L)
        val higherCard = Hand(listOf(CamelCard.`3`, CamelCard.`2`, CamelCard.`4`, CamelCard.`5`, CamelCard.`7`), 0L)

        assertTrue(betterSecondary > higherCard)
    }

    @Test
    fun `pair is stronger than high card`() {
        val highCard = Hand(listOf(CamelCard.`3`, CamelCard.`2`, CamelCard.`4`, CamelCard.`5`, CamelCard.A), 0L)
        val pair = Hand(listOf(CamelCard.`2`, CamelCard.`2`, CamelCard.`4`, CamelCard.`5`, CamelCard.`6`), 0L)

        assertTrue(pair > highCard)
    }

    @Test
    fun `pair of aces is stronger than pair of twos`() {
        val pairOfTwos = Hand(listOf(CamelCard.`2`, CamelCard.`2`, CamelCard.`4`, CamelCard.`5`, CamelCard.`6`), 0L)
        val pairOfAces = Hand(listOf(CamelCard.A, CamelCard.A, CamelCard.`4`, CamelCard.`5`, CamelCard.`6`), 0L)

        assertTrue(pairOfAces > pairOfTwos)
    }

    @Test
    fun `two pair is stronger than one pair`() {
        val onePair = Hand(listOf(CamelCard.A, CamelCard.A, CamelCard.`2`, CamelCard.T, CamelCard.`5`), 0L)
        val twoPair = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`2`, CamelCard.`2`, CamelCard.`5`), 0L)

        assertTrue(twoPair > onePair)
    }

    @Test
    fun `three of a kind is stronger than two pair`() {
        val twoPair = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`2`, CamelCard.`2`, CamelCard.`5`), 0L)
        val threeOfAKind = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`2`, CamelCard.`5`), 0L)

        assertTrue(threeOfAKind > twoPair)
    }

    @Test
    fun `full house is stronger than three of a kind`() {
        val threeOfAKind = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`2`, CamelCard.`5`), 0L)
        val fullHouse = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`2`, CamelCard.`2`), 0L)

        assertTrue(fullHouse > threeOfAKind)
    }

    @Test
    fun `four of a kind is stronger than fullhouse`() {
        val fullHouse = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`2`, CamelCard.`2`), 0L)
        val fourOfAKind = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`2`), 0L)

        assertTrue(fourOfAKind > fullHouse)
    }

    @Test
    fun `five of a kind is stronger than four of a kind`() {
        val fourOfAKind = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`2`), 0L)
        val fiveOfAKind = Hand(listOf(CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`3`, CamelCard.`3`), 0L)

        assertTrue(fiveOfAKind > fourOfAKind)
    }
}