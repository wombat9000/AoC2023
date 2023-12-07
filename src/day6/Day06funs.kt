package day6

fun getWinningButtonPresses(race: Race): Long {
    val distanceToBeat = race.distance

    return (1L..race.duration)
        .map {pressDuration -> pressDuration * (race.duration - pressDuration) }
        .filter { distance -> distance > distanceToBeat }
        .size
        .toLong()
}