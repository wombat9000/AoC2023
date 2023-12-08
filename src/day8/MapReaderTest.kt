package day8

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MapReaderTest {

    @Test
    fun `should read a small map`() {
        val reader = MapReader("day8/Day08.test")
        val map = reader.getMap()

        map.mappings["AAA"].let { directions ->
            assertEquals("BBB", directions?.left)
            assertEquals("CCC", directions?.right)
        }

        val insructions = reader.getInstructionSequence()
        assertEquals(listOf('R', 'L'), insructions)
    }
}