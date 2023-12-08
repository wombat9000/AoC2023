package day8

import readInput


data class Directions(val left: String, val right: String)

class DesertIslandMap(mapLines: List<String>) {

    val mappings: Map<String, Directions> = mapLines.associate { line ->
        val (key, value) = line.split(" = ")
        val (left, right) = value.drop(1).dropLast(1).split(", ")
        key to Directions(left, right)
    }

    fun getStartingPoints(): List<String> {
        return mappings.keys.filter { it.endsWith("A") }
    }
}

class MapReader(input: String) {
    private val lines = readInput(input)

    fun getInstructionSequence(): List<Char> {
        return lines[0].toList()
    }

    fun getMap(): DesertIslandMap {
        return DesertIslandMap(lines.drop(2))
    }
}
