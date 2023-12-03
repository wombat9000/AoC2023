package day3

import day3.SchematicReader.SYMBOLS
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PartsReaderTest {
    val emptyLine = "......"

    @Nested
    inner class SameLineDetection {
        @Test
        fun `should return the number if there is a symbol before the number`() {
                val input = listOf(emptyLine, ".+13..", emptyLine)
                val result = SchematicReader.readPartsSum(input)
                assertEquals(13, result)
        }

        @Test
        fun `should return the number if there is a symbol after the number`() {
            val input = listOf(emptyLine, "..13+.", emptyLine)
            val result = SchematicReader.readPartsSum(input)
            assertEquals(13, result)
        }

        @Test
        fun `should not return the number if there is no symbol anywhere`() {
            val input = listOf(emptyLine, "..13..", emptyLine)
            val result = SchematicReader.readPartsSum(input)
            assertEquals(0, result)
        }

        @Test
        fun `should not return the number if the symbols are too far away`() {
            val input = listOf(emptyLine, "12.+.+.13.+.+", emptyLine)
            val result = SchematicReader.readPartsSum(input)
            assertEquals(0, result)
        }

        @Test
        fun `should detect multiple numbers in same line`() {
            val input = listOf(emptyLine, "..13+13..", emptyLine)
            val result = SchematicReader.readPartsSum(input)
            assertEquals(26, result)
        }

        @Test
        fun `should detect many numbers numbers in same line`() {
            val input = listOf(emptyLine, "1*.13-13.-1", emptyLine)
            val result = SchematicReader.readPartsSum(input)
            assertEquals(28, result)
        }
    }

    @Nested
    inner class AboveLineDetection {
        @Test
        fun `should return the number if there is a symbol before the number`() {
            val input = listOf(".+....", "..13..", emptyLine)
            val result = SchematicReader.readPartsSum(input)
            assertEquals(13, result)
        }

        @Test
        fun `should return the number if there is a symbol after the number`() {
            val input = listOf(emptyLine, "..13..", ".+....")
            val result = SchematicReader.readPartsSum(input)
            assertEquals(13, result)
        }

        @Test
        fun `should return the number if there is adjancency to a scond number`() {
            val input = listOf(emptyLine, ".24..4..", "....*++.")
            val result = SchematicReader.readPartsSum(input)
            assertEquals(4, result)
        }
    }

    @Test
    fun `should detect all symbols`() {
        SYMBOLS.forEach { symbol ->
            val input = listOf("....", "${symbol}23.", "....")


            val result = SchematicReader.readPartsSum(input)

            assertEquals(23, result)
        }
    }

    @Test
    fun `should return 0 if there are no symbols in the window`() {
        val input = listOf("1234", "1234", "....")

        val result = SchematicReader.readPartsSum(input)

        assertEquals(0, result)
    }

    @Test
    fun `should return 0 if there are no numbers in the window`() {
        val input = listOf("++.%", "+%\\+.", "....")

        val result = SchematicReader.readPartsSum(input)

        assertEquals(0, result)
    }

    @Test
    fun `should return the number if there is a symbol after the number`() {
        val inputs: List<List<String>> = listOf(
            listOf("....", "..3+", "...."),
            listOf("...+", "..3.", "...."),
            listOf("....", "..3.", "...+"),
        )

        inputs.forEach {
            val result = SchematicReader.readPartsSum(it)

            assertEquals(3, result)
        }
    }

    @Test
    fun `should return the number if there is a symbol before the number`() {
        val inputs: List<List<String>> = listOf(
            listOf(".....", ".+13.", "....."),
            listOf(".+...", "..13.", "....."),
            listOf(".....", "..13.", ".+..."),
        )

        inputs.forEach {
            val result = SchematicReader.readPartsSum(it)

            assertEquals(13, result)
        }
    }

    @Test
    fun `should not return the number if its not adjacent to number`() {
        val inputs: List<List<String>> = listOf(
            listOf("+...", "+.3.", "+..."),
            listOf("...+", "12..", "...+"),
        )

        inputs.forEach {
            val result = SchematicReader.readPartsSum(it)

            assertEquals(0, result)
        }
    }

    @Nested
    inner class DiagonalTests {
        @Test
        fun `should return the number if it is diagonally adjacent`() {
            val inputs: List<List<String>> = listOf(
                listOf(".+....", "..12..", "......"),
                listOf("....+.", "..12..", "......"),
                listOf("......", "..12..", ".+...."),
                listOf("......", "..12..", "....+."),
            )

            inputs.forEach {
                val result = SchematicReader.readPartsSum(it)

                assertEquals(12, result)
            }
        }

        @Test
        fun `should detect diagonal symbol in line above - before`() {
            val input = listOf(".+....", "..12..", "......")

            val result = SchematicReader.readPartsSum(input)

            assertEquals(12, result)
        }

        @Test
        fun `should detect diagonal symbol in line above - after`() {
            val input = listOf("......", "..12..", "....+.")

            val result = SchematicReader.readPartsSum(input)

            assertEquals(12, result)
        }
    }

    @Test
    fun `should return a double digit number if it is followed by a symbol`() {
        val input = listOf("....", ".13+", "....")

        val result = SchematicReader.readPartsSum(input)

        assertEquals(13, result)
    }

    @Test
    fun `should consider the row above for adjacency`() {
        val input = listOf("..+.", ".13.", "....")

        val result = SchematicReader.readPartsSum(input)

        assertEquals(13, result)
    }

    @Test
    fun `should consider the row below for adjacency`() {
        val input = listOf("....", ".13.", ".+..")

        val result = SchematicReader.readPartsSum(input)

        assertEquals(13, result)
    }
}