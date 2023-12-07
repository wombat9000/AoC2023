package day6

import readInput

data class Race(val duration: Long, val distance: Long)

class RaceFileReader(val fileName: String) {
    fun readRaces(): List<Race> {

        val lines = readInput(fileName)

        val durations = lines.first().split(":").last().trim().split(" ").filter { it.isNotEmpty() }
        val distances = lines.last().split(":").last().trim().split(" ").filter { it.isNotEmpty() }

        return durations.zip(distances) { duration: String, distance: String ->
            Race(duration.toLong(), distance.toLong())
        }
    }

}