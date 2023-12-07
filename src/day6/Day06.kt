package day6

fun main() {
    val testInput = "day6/Day06.test"
    assert(part1(testInput) == 288L)

    val input = "day6/Day06"

    println("Day 06, Part 1: ${part1(input)}")
    println("Day 06, Part 2: ${part2(input)}")
}

fun part1(input: String): Long {
    val races = RaceFileReader(input).readRaces()
    return races.map { getWinningButtonPresses(it) }.reduce { acc, l -> acc * l }
}

fun part2(input: String): Long {
    val races = RaceFileReader(input).readSingleRace()
    return getWinningButtonPresses(races)
}