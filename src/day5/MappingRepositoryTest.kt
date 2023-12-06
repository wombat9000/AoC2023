package day5

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MappingRepositoryTest {

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