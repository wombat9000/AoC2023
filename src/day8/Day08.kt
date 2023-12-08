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
    return followInstructionsP1(instructions, map, "AAA")
}

fun part2(input: String): Long {
    val mapReader = MapReader(input)
    val instructions = mapReader.getInstructionSequence()
    val desertMap = mapReader.getMap()
    val startingPoints = desertMap.getStartingPoints()

    return 0L
}

tailrec fun followInstructionsP1(
    instructions: List<Char>,
    map: DesertIslandMap,
    location: String,
    steps: Long = 0
): Long {
    if (location == "ZZZ") {
        return steps
    }

    val nextIndex = (steps % instructions.size).toInt()

    val nextLocation = when (val nextInstruction = instructions[nextIndex]) {
        'L' -> map.mappings[location]?.left
        'R' -> map.mappings[location]?.right
        else -> throw IllegalArgumentException("Unknown instruction: $nextInstruction")
    } ?: throw IllegalArgumentException("Unknown location: $location")

    return followInstructionsP1(instructions, map, nextLocation, steps + 1)
}