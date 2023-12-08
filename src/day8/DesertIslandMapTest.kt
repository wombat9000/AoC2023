package day8

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class DesertIslandMapTest {

    @Test
    fun `should read an input of two lines`() {
        val input = """
            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
        """.trimIndent()

        val map = DesertIslandMap(input.lines())

        // assert that map contains value "BBB" for key "AAA":
        assertEquals(Directions("BBB", "CCC"), map.mappings["AAA"])
        assertEquals(Directions("DDD", "EEE"), map.mappings["BBB"])
    }

    @Test
    fun `should read all starting points`() {
        val reader = MapReader("day8/Day08.test3")
        val startingPoints = reader.getMap().getStartingPoints()

        assertEquals(listOf("11A", "22A"), startingPoints)
    }
}