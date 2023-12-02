package day2

object CubeReader {
    fun readCubes(input: String): CubeComposition {
        try {
            val parts = input.split(",")

            val redPart = parts.find { it.contains("red") }
            val redCubes = redPart?.substringBefore("red")?.trim()?.toInt() ?: 0

            val greenPart = parts.find { it.contains("green") }
            val greenCubes = greenPart?.substringBefore("green")?.trim()?.toInt() ?: 0

            val bluePart = parts.find { it.contains("blue") }
            val blueCubes = bluePart?.substringBefore("blue")?.trim()?.toInt() ?: 0

            return CubeComposition(redCubes, greenCubes, blueCubes)
        } catch (e: Exception) {
            throw IllegalArgumentException("Could not read cubes from input: $input")
        }

    }
}