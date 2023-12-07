package day7

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class CamelCardReaderTest {

    @Test
    fun `should read hands`() {
        val reader = CamelCardReader("day7/Day07.test")

        val result = reader.readHands()

        assertEquals(5, result.size)
        assertEquals(listOf(CamelCard.`3`, CamelCard.`2`, CamelCard.T, CamelCard.`3`, CamelCard.K), result.first().cards)
        assertEquals(listOf(CamelCard.Q, CamelCard.Q, CamelCard.Q, CamelCard.J, CamelCard.A), result.last().cards)
    }
}