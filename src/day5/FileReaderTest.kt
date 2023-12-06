package day5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FileReaderTest {

    @Test
    fun `should read seeds from file`() {
        val reader = FileReader("day5/Day05test")

        assertEquals(reader.seeds, "79 14 55 13")
    }

    @Test
    fun `should read seeds from file as p2 format`() {
        val reader = FileReader("day5/Day05test")

        val ranges: List<LongRange> = listOf(79L..(79L + 14L), 55L..(55L + 13L))
        assertEquals(ranges, reader.seedsP2)
    }

    @Test
    fun `should read soil mappings from file`() {
        val reader = FileReader("day5/Day05test")

        assertEquals(
            listOf("50 98 2", "52 50 48"),
            reader.readMapping(MappingType.SEED_TO_SOIL)
        )
    }

    @Test
    fun `should read fertilizer mappings from file`() {
        val reader = FileReader("day5/Day05test")

        assertEquals(
            listOf(
                "0 15 37",
                "37 52 2",
                "39 0 15",
            ), reader.readMapping(MappingType.SOIL_TO_FERTILIZER)
        )
    }
}