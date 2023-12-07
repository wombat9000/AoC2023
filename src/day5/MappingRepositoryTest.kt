package day5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MappingRepositoryTest {

    @Nested
    inner class Part1 {
        @Test
        fun `should map seed to same soil number, if it is not mapped`() {
            val testee = MappingRepository(listOf())

            val soilNumber = testee.getDestinationMappingForSource(15)

            assertEquals(15, soilNumber)
        }

        @Test
        fun `should map seed to soil number if it is mapped`() {
            val testee = MappingRepository(listOf("50 98 2"))

            val soilNumber = testee.getDestinationMappingForSource(98)

            assertEquals(50, soilNumber)
        }

        @Test
        fun `should map seed to soil number if it is mapped and source is smaller than dest`() {
            val testee = MappingRepository(listOf("98 50 2"))

            val soilNumber = testee.getDestinationMappingForSource(50)

            assertEquals(98, soilNumber)
        }

        @Test
        fun `should map seed to soil number if it is mapped and has offset`() {
            val testee = MappingRepository(listOf("98 50 2"))

            val soilNumber = testee.getDestinationMappingForSource(51)

            assertEquals(99, soilNumber)
        }
    }

    @Nested
    inner class Part2 {
        @Test
        fun `should map seed to same soil number, if it is not mapped`() {
            val testee = MappingRepository(listOf("98 50 2"))

            val destinationRange = testee.getDestinationRangeForSourceRange(1L..2L)

            assertEquals(listOf(1L..2L), destinationRange)
        }

        @Test
        fun `should map single item range, if it is not mapped`() {
            val testee = MappingRepository(listOf("98 50 2"))

            val destinationRange = testee.getDestinationRangeForSourceRange(97L..97L )

            assertEquals(listOf(97L..97L), destinationRange)
        }

        @Test
        fun `should return mapping if it is completely covered`() {
            val testee = MappingRepository(listOf("98 50 3"))

            val destinationRange = testee.getDestinationRangeForSourceRange(50L..52L )

            assertEquals(listOf(98L..100L), destinationRange)
        }

        @Test
        fun `should return two ranges if range is partially mapped`() {
            val testee = MappingRepository(listOf("50 98 2"))

            val destinationRange = testee.getDestinationRangeForSourceRange(97L..98L)

            assertEquals(listOf(97L..97L, 50L..50L), destinationRange)
        }

        @Test
        fun `should handle unmapped ranges at the end`() {
            val testee = MappingRepository(listOf("50 98 2"))

            val destinationRange = testee.getDestinationRangeForSourceRange(99L..100L)

            assertEquals(listOf(51L..51L, 100L..100L), destinationRange)
        }

        @Test
        fun `should handle ranges that are fully included in sourceRange`() {
            val testee = MappingRepository(listOf("50 98 2"))

            val destinationRange = testee.getDestinationRangeForSourceRange(95L..100L)

            assertEquals(listOf(95L..97L, 50L..51L, 100L..100L), destinationRange)
        }

        @Test
        fun `should handle ranges that include map to zero`() {
            val testee = MappingRepository(listOf("0 100 2"))

            val destinationRange = testee.getDestinationRangeForSourceRange(99L..105L)

            assertEquals(listOf(99L..99L, 0L..1L, 102L..105L), destinationRange)
        }

        @Test
        fun `should handle ranges that map from zero`() {
            val testee = MappingRepository(listOf("100 0 2"))

            val destinationRange = testee.getDestinationRangeForSourceRange(0L..15)

            assertEquals(listOf(100L..101L, 2L..15L), destinationRange)
        }
    }

}