package day3

import readInput

fun main() {
    val input = readInput("day3/Day03")

    val symbols = input.flatMap { it.asIterable() }
        .filter { it !in "0123456789." }
        .toSet()
        .joinToString("")

    println("symbols: $symbols")

    println("Day 03, Part 1: ${part1(input)}")
    println("Day 03, Part 2: ${part2(input)}")

}

fun part1(input: List<String>): Int {
    val lineLength = input.first().length
    val fillerLine = "".padEnd(lineLength, '.')

    val paddedInput = listOf(fillerLine) + input + listOf(fillerLine)

    val windowed = paddedInput.windowed(3, 1, false)
    return windowed.sumOf {
        val windowSum = SchematicReader.readPartsSum(it)
        windowSum
    }
}

fun part2(input: List<String>): Int {
    val lineLength = input.first().length
    val fillerLine = "".padEnd(lineLength, '.')

    val paddedInput = listOf(fillerLine) + input + listOf(fillerLine)

    val windowed = paddedInput.windowed(3, 1, false)

    return windowed.sumOf {
        val windowSum = SchematicReader.readGears(it)
        windowSum
    }
}