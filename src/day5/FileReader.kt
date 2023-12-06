package day5

import readInput

enum class MappingType(val mapping: String) {
    SEED_TO_SOIL("seed-to-soil"),
    SOIL_TO_FERTILIZER("soil-to-fertilizer"),
    FERTILIZER_TO_WATER("fertilizer-to-water"),
    WATER_TO_LIGHT("water-to-light"),
    LIGHT_TO_TEMP("light-to-temperature"),
    TEMP_TO_HUMID("temperature-to-humidity"),
    HUMID_TO_LOC("humidity-to-location")
}

class FileReader(fileName: String) {

    val input: List<String> = readInput(fileName)

    val seeds: String = input.first()
        .split(":")
        .last().trim()


    // build pairs from seeds string:
    val seedsP2: List<LongRange> = seeds.split(" ").chunked(2)
        .map { (start, end) -> start.toLong()..(start.toLong() + end.toLong()) }



    fun readMapping(type: MappingType): List<String> {
       return input.asSequence().dropWhile { it != "${type.mapping} map:" }
            .drop(1)
            .takeWhile { it != "" }
            .toList()
    }
}