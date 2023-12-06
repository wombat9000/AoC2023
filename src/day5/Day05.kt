package day5

import readInput

fun main() {

    val reader = FileReader("day5/Day05")



    println("Day 05, Part 1: ${part1(reader)}")
    println("Day 05, Part 2: ${part2(reader)}")
}


fun part1(reader: FileReader) {
    val soilsRepo = MappingRepository(reader.readMapping(MappingType.SEED_TO_SOIL))
    val fertilizerRepo = MappingRepository(reader.readMapping(MappingType.SOIL_TO_FERTILIZER))
    val waterRepo = MappingRepository(reader.readMapping(MappingType.FERTILIZER_TO_WATER))
    val lightRepo = MappingRepository(reader.readMapping(MappingType.WATER_TO_LIGHT))
    val tempRepo = MappingRepository(reader.readMapping(MappingType.LIGHT_TO_TEMP))
    val humidRepo = MappingRepository(reader.readMapping(MappingType.TEMP_TO_HUMID))
    val locationRepo = MappingRepository(reader.readMapping(MappingType.HUMID_TO_LOC))

    val locations = reader.seeds.split(" ")
        .asSequence()
        .map { it.toLong() }
        .map { soilsRepo.getDestinationMappingForSource(it) }
        .map { fertilizerRepo.getDestinationMappingForSource(it) }
        .map { waterRepo.getDestinationMappingForSource(it) }
        .map { lightRepo.getDestinationMappingForSource(it) }
        .map { tempRepo.getDestinationMappingForSource(it) }
        .map { humidRepo.getDestinationMappingForSource(it) }
        .map { locationRepo.getDestinationMappingForSource(it) }
        .toList()

    println(locations.min())
}

fun part2(reader: FileReader) {
    val soilsRepo = MappingRepository(reader.readMapping(MappingType.SEED_TO_SOIL))
    val fertilizerRepo = MappingRepository(reader.readMapping(MappingType.SOIL_TO_FERTILIZER))
    val waterRepo = MappingRepository(reader.readMapping(MappingType.FERTILIZER_TO_WATER))
    val lightRepo = MappingRepository(reader.readMapping(MappingType.WATER_TO_LIGHT))
    val tempRepo = MappingRepository(reader.readMapping(MappingType.LIGHT_TO_TEMP))
    val humidRepo = MappingRepository(reader.readMapping(MappingType.TEMP_TO_HUMID))
    val locationRepo = MappingRepository(reader.readMapping(MappingType.HUMID_TO_LOC))

    val ranges = reader.seedsP2

    println(ranges)

    val locations = ranges
        .asSequence()
        .flatMap { it.toList().asSequence() }
        .map { soilsRepo.getDestinationMappingForSource(it) }
        .map { fertilizerRepo.getDestinationMappingForSource(it) }
        .map { waterRepo.getDestinationMappingForSource(it) }
        .map { lightRepo.getDestinationMappingForSource(it) }
        .map { tempRepo.getDestinationMappingForSource(it) }
        .map { humidRepo.getDestinationMappingForSource(it) }
        .map { locationRepo.getDestinationMappingForSource(it) }
        .toList()

    println(locations.min())
}