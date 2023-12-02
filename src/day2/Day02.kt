package day2

import readInput


data class CubeComposition(val red: Int, val green: Int, val blue: Int)

fun main() {
    val input = readInput("day2/Day02")

    println("Day 02, Part 1: ${part1(input, CubeComposition(12, 13, 14))}")
    println("Day 02, Part 2: ${part2(input)}")
}


fun part1(input: List<String>, referenceComposition: CubeComposition): Int {
    return input.sumOf {
        val parts = it.split(":")
        val lineID = parts.first().substringAfter("Game ").toInt()

        val cubeLines = parts.last().split(";")
        val cubeComps = cubeLines.map { cubeLine -> CubeReader.readCubes(cubeLine) }
        val satisfiedReferenceComp = cubeComps.all { cubes ->
            val validRed = cubes.red <= referenceComposition.red
            val validGreen = cubes.green <= referenceComposition.green
            val validBlue = cubes.blue <= referenceComposition.blue
            validRed && validGreen && validBlue
        }

        when (satisfiedReferenceComp) {
            true -> lineID.toString().toInt()
            false -> 0
        }
    }
}

fun part2(input: List<String>): Int {
    return input.sumOf { it ->
        val parts = it.split(":")

        val cubeLines = parts.last().split(";")
        val cubeComps = cubeLines.map { cubeLine -> CubeReader.readCubes(cubeLine) }

        val minRedCubes = cubeComps.maxOf { it.red }
        val minGreenCubes = cubeComps.maxOf { it.green }
        val minBlueCubes = cubeComps.maxOf { it.blue }

        minRedCubes * minGreenCubes * minBlueCubes
    }
}