import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day01KtTest {
    @Test
    fun `should work as before if actual digits are still the relevant coordinates`() {
        val result = part2(listOf("ah2ninelsr2as"))

        assertEquals(22, result)
    }

    @Test
    fun `should work if there is a number word at the end`() {
        val result = part2(listOf("ah2gagafive"))

        assertEquals(25, result)
    }

    @Test
    fun `should work if there is a number word at the front`() {
        val result = part2(listOf("aoneh2gagafive"))

        assertEquals(15, result)
    }

    @Test
    fun `should work if there is no number word`() {
        val result = part2(listOf("4rlqzthlhkxvzhcm6"))

        assertEquals(46, result)
    }
}