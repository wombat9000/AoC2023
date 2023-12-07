package day5

data class MappingRange(val sourceRange: LongRange, val diff: Long) {
    fun hasOverlap(sourceRange: LongRange): Boolean {
        return !hasNoOverlap(sourceRange)
    }

    private fun hasNoOverlap(range: LongRange): Boolean {
        return range.last < sourceRange.first || range.first > sourceRange.last
    }

    fun determineOverlap(other: LongRange): Overlap {
        val before: LongRange? = if (other.first < sourceRange.first) {
            other.first..minOf(sourceRange.first - 1, other.last)
        } else {
            null
        }

        val after: LongRange? = if (other.last > sourceRange.last) {
            sourceRange.last + 1..other.last
        } else {
            null
        }

        val overlap: LongRange? = if (hasOverlap(other)) {
            val start = maxOf(sourceRange.first, other.first)
            val end = minOf(sourceRange.last, other.last)
            start..end
        } else {
            null

        }

        return Overlap(before = before, overlap = overlap, after = after)
    }
}

data class Overlap(val before: LongRange? = null, val overlap: LongRange? = null, val after: LongRange? = null)

class MappingRepository(input: List<String>) {

    private val mappings: List<MappingRange> = input.map { line ->
        val (dest, source, range) = line.split(" ").map { it.toLong() }
        MappingRange(source..<source + range, source - dest)
    }.sortedBy { it.sourceRange.first }


    fun getDestinationMappingForSource(source: Long): Long {
        mappings.find { source in it.sourceRange }
            ?.let { return source - it.diff }

        return source
    }

    fun getDestinationRangeForSourceRange(sourceRange: LongRange): List<LongRange> {
        val relevantMappings = mappings.filter { it.hasOverlap(sourceRange) }

        return accumulateMappings(sourceRange, relevantMappings, listOf())
    }

    private tailrec fun accumulateMappings(
        sourceRange: LongRange,
        remainingMappings: List<MappingRange>,
        accResult: List<LongRange>
    ): List<LongRange> {
        if (remainingMappings.isEmpty()) {
            return accResult + listOf(sourceRange)
        }

        val nextMappingRange = remainingMappings.first()
        val overlap = nextMappingRange.determineOverlap(sourceRange)

        val accumulation = accResult + listOfNotNull(
            overlap.before,
            overlap.overlap?.let { it.first - nextMappingRange.diff..it.last - nextMappingRange.diff }
        )

        if (overlap.after == null) return accumulation

        return accumulateMappings(
            overlap.after,
            remainingMappings.drop(1),
            accumulation
        )
    }
}