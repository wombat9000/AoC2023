package day1

import println
import readInput

fun main() {
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}

internal fun part1(input: List<String>): Int {
    return input.sumOf { line ->
        val firstDigit = line.first { it.isDigit() }
        val lastDigit = line.last { it.isDigit() }
        "$firstDigit$lastDigit".toInt()
    }
}

internal fun part2(input: List<String>): Int {
    return input.sumOf { line ->
        try {
            val digits = replaceNumberWords(line)
            val firstDigit = digits.first()
            val lastDigit = digits.last()

            "$firstDigit$lastDigit".toInt()
        } catch (e: Exception) {
            println("Failed at line $line")
            throw e
        }
    }
}

fun replaceNumberWords(input: String): String {
    return input
        .replace("one", "1", true)
        .replace("two", "2", true)
        .replace("three", "3", true)
        .replace("four", "4", true)
        .replace("five", "5", true)
        .replace("six", "6", true)
        .replace("seven", "7", true)
        .replace("eight", "8", true)
        .replace("nine", "9", true)
        .filter { it.isDigit() }
}