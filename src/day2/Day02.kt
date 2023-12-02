package day2

import readInput


data class CubeComposition(val red: Int, val green: Int, val blue: Int)

fun main() {
    val input = readInput("day2/Day02.txt2.txt")
    println("Day 02, Part 1: ${day1.part1(input)}")

}


fun part1(input: List<String>, composition: CubeComposition): Int {
    return input.sumOf {
        val parts = it.split(":")
        val lineID = parts.first().substringAfter("Game ").toInt()

        val cubes = parts.last().split(",").map { it.trim() }
        println(cubes)



        lineID.toString().toInt()
    }
}