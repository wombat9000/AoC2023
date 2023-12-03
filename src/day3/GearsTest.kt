package day3

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class GearsTest {

    val emptyLine = "......"

    @Test
    fun `should return the the gear ratio for a single line`() {
        val input = listOf(emptyLine, "..3*2..", emptyLine)
        val result = SchematicReader.readGears(input)
        Assertions.assertEquals(6, result)
    }

    @Test
    fun `should return the the gear ratio for multiple gears on a single line`() {
        val input = listOf(emptyLine, "..3*2....3*2...", emptyLine)
        val result = SchematicReader.readGears(input)
        Assertions.assertEquals(12, result)
    }

    @Test
    fun `should return the the gear ratio for multiple gears over multiple lines`() {
        val input = listOf("..3....3...", "..*....*...", "..2....2...")
        val result = SchematicReader.readGears(input)
        Assertions.assertEquals(12, result)
    }

    @Test
    fun `should not calc gear ratio for gears with only 1 part`() {
        val input = listOf("..3........", "..*....*...", ".......2...")
        val result = SchematicReader.readGears(input)
        Assertions.assertEquals(0, result)
    }
}