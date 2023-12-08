package day8


fun main() {
    val testInput = "day8/Day08.test2"
    assert(part1(testInput) == 6L)

    val input = "day8/Day08"

    println("Day 08, Part 1: ${part1(input)}")
//    println("Day 08, Part 2: ${part2(input)}")
}

fun part1(input: String): Long {
    val mapReader = MapReader(input)

    val instructions = mapReader.getInstructionSequence()
    val map = mapReader.getMap()
    val infiniteInstructions = generateSequence(0) { (it + 1) % instructions.size }
        .map { instructions[it] }
    return followInstructions(infiniteInstructions, map, "AAA")
}

fun part2(input: String): Long {
    TODO()
}

tailrec fun followInstructions(
    instructions: Sequence<Char>,
    map: DesertIslandMap,
    location: String,
    steps: Long = 0
): Long {
    if (location == "ZZZ") {
        return steps
    }

    val nextLocation = when (val nextInstruction = instructions.first()) {
        'L' -> map.mappings[location]?.left
        'R' -> map.mappings[location]?.right
        else -> throw IllegalArgumentException("Unknown instruction: $nextInstruction")
    } ?: throw IllegalArgumentException("Unknown location: $location")

    return followInstructions(instructions.drop(1), map, nextLocation, steps + 1)
}