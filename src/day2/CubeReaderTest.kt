package day2

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CubeReaderTest {

    @Test
    fun `should read line with one only red cubes`() {
        val result = CubeReader.readCubes("3 red")
        assertEquals(CubeComposition(3, 0, 0), result)
    }

    @Test
    fun `should read line with one only green cubes`() {
        val result = CubeReader.readCubes("3 green")
        assertEquals(CubeComposition(0, 3, 0), result)
    }

    @Test
    fun `should read line with one only blue cubes`() {
        val result = CubeReader.readCubes("3 blue")
        assertEquals(CubeComposition(0, 0, 3), result)
    }

    @Test
    fun `should read line with all colors blue cubes`() {
        val result = CubeReader.readCubes("3 blue, 2 red, 5 green")
        assertEquals(CubeComposition(2, 5, 3), result)
    }
}