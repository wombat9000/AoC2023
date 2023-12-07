package day6

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Day6FunsTest {

    @Test
    fun `should find all solutions for first example race`() {
        val result = getWinningButtonPresses(Race(7, 9))

        assertEquals(4, result)
    }

    @Test
    fun `should find all solutions for second example race`() {
        val result = getWinningButtonPresses(Race(15, 40))

        assertEquals(8, result)
    }

    @Test
    fun `should find all solutions for third example race`() {
        val result = getWinningButtonPresses(Race(30, 200))

        assertEquals(9, result)
    }
}