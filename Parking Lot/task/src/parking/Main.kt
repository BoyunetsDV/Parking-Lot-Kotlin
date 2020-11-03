package parking

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    val command = scanner.nextLine()
    Parking.executeCommand(command)
}


object Parking {
    private const val numberOfParkingSpots = 2
    private val parkingSlots = Array(numberOfParkingSpots) { Pair("", "") }

    init {
        parkingSlots[0] = Pair("1", "1")
    }

    fun executeCommand(command: String) {
        if (command.isEmpty() || command.isNullOrBlank()) {
            return
        }

        val commandParts = command.split(" ")
        when (commandParts[0]) {
            "park" -> parkCar(commandParts[1], commandParts[2])
            "leave" -> freeSpot(commandParts[1].toInt())
        }
    }

    private fun parkCar(carNumber: String, carColor: String) {
        val emptySlotIndex = parkingSlots.indexOfFirst { it.first.isEmpty() }
        if (emptySlotIndex == -1) {
            println("No empty places")
            return
        }
        parkingSlots[emptySlotIndex] = Pair(carNumber, carColor)
        println("$carColor car parked in spot ${emptySlotIndex + 1}.")
    }

    private fun freeSpot(spotNumber: Int) {
        if (spotNumber <= 0 || spotNumber > numberOfParkingSpots) {
            return
        }

        val parkedCar = parkingSlots[spotNumber - 1]
        if (parkedCar.first.isNotEmpty()) {
            parkingSlots[spotNumber - 1] = Pair("", "")
            println("Spot $spotNumber is free.")
        } else {
            println("There is no car in spot $spotNumber.")
        }
    }
}