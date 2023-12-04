package day4

import readInput


fun main() {
    val testInput = "day4/test/test1"
    val day4Input = "day4/Day04"
    val input = readInput(testInput)

//    println("Day 04, Part 1: ${part1(input)}")
    println("Day 04, Part 2: ${part2(input)}")
}


fun part1(input: List<String>): Int {
    return input.sumOf { countScore(it) }
}

fun part2(input: List<String>): Int {
    val sortedScoreCards = input.map { ScoreCard(it) }.sortedBy { it.cardNo }
    return determineCardsWon(sortedScoreCards, sortedScoreCards, listOf()).size
}


class ScoreCard(line: String) {
    private val rawCard = line.split(':')
    val cardNo: Int = rawCard.first().split(' ').last().toInt()

    private val rawNumbers = rawCard.last().split('|')

    val winners: List<Int> = parseNumbers(rawNumbers.first())
    val picked: List<Int> = parseNumbers(rawNumbers.last())

    fun countWinners(): Int {
        return winners.filter { it in picked }.size
    }

    override fun toString(): String {
        return "ScoreCard(cardNo=$cardNo, winners=$winners, picked=$picked)"
    }
}

tailrec fun determineCardsWon(
    allCards: List<ScoreCard>,
    hand: List<ScoreCard>,
    scoredCards: List<ScoreCard>
): List<ScoreCard> {

//    println("current hand: $hand")

    if (hand.isEmpty()) return scoredCards

    val head = hand.last()
    println("looking at ${head.cardNo}, scored: ${scoredCards.size}")
    val tail = hand.dropLast(1)

    val cardsToDraw = allCards
        .filter { it.cardNo > head.cardNo }
        .take(head.countWinners())

    val newHand = tail.plus(cardsToDraw)

    return determineCardsWon(allCards, newHand, scoredCards.plus(hand.first()))
}

fun countScore(line: String): Int {
    val parts = line.split(':').last().split('|')

    val winners: List<Int> = parseNumbers(parts.first())
    val picked: List<Int> = parseNumbers(parts.last())

    val correctPicks = winners.filter { it in picked }
    return correctPicks.fold(0) { acc, _ -> if (acc == 0) 1 else acc * 2 }
}

private fun parseNumbers(first: String): List<Int> {
    return first.split(' ')
        .filter { it.isNotBlank() }
        .map { it.trim().toInt() }
}
