package day5

data class MappingRange(val sourceRange: LongRange, val diff: Long)


class MappingRepository(input: List<String>) {

    private val mappings: List<MappingRange> = input.map { line ->
        val (dest, source, range) = line.split(" ").map { it.toLong() }
        MappingRange(source..(source + range), source - dest)
    }


    fun getDestinationMappingForSource(source: Long): Long {
        mappings.find { source in it.sourceRange }
            ?.let { return source - it.diff }

        return source
    }

    fun getDestinationRangeForSourceRange(sourceRange: LongRange): List<LongRange> {
        return listOf(0L..1L)
    }
}