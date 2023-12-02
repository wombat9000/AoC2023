package day2

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day02Test {

    @Test
    fun `should return game ID if combination is possible`() {
       val composition = CubeComposition(1, 1, 1)
        val result = part1(listOf("Game 1: 1 red, 1 green"), composition)

        assertEquals(1, result)
    }

    @Test
    fun `should return 0 if combination is not possible`() {
        val composition = CubeComposition(1, 1, 1)
        val result = part1(listOf("Game 1: 2 red, 1 green"), composition)

        assertEquals(0, result)
    }
}