package day7

fun main() {
    val testInput = "day7/Day07.test"
//    assert(part1(testInput) == 6440L)

    val input = "day7/Day07"

//    println("Day 07, Part 1: ${part1(input)}")
    println("Day 07, Part 2: ${part2(input)}")
}

fun part1(input: String): Long {
    return CamelCardReader(input).readHands().sorted()
        .mapIndexed { index, it -> (index+1) * it.bid }
        .sum()
}

fun part2(input: String): Long {
    return CamelCardReader(input).readHands().sorted()
        .mapIndexed { index, it -> (index+1) * it.bid }
        .sum()
//    return 0L
}