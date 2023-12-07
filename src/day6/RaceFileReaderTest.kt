package day6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RaceFileReaderTest {

    @Test
    fun `should read races`() {
        val reader = RaceFileReader("day6/Day06.test")

        val result = reader.readRaces()

        assertEquals(
            listOf(
                Race(7, 9),
                Race(15, 40),
                Race(30, 200)
            ),
            result
        )
    }

    @Test
    fun `should read single race`() {
        val reader = RaceFileReader("day6/Day06.test")

        val result = reader.readSingleRace()

        assertEquals(Race(71530, 940200), result)
    }
}