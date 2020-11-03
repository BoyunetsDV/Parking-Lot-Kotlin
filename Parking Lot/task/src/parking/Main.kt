package parking

import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    do {
        val command = scanner.nextLine()
        Parking.executeCommand(command)
    } while (command != "exit")
}

class Car(val number: String, val color: String)

object Parking {
    private const val numberOfParkingSpots = 20
    private val parkingSlots = Array<Car?>(numberOfParkingSpots) { null }

    fun executeCommand(command: String) {
        if (command.isEmpty() || command.isBlank()) {
            return
        }

        val commandParts = command.split(" ")
        when (commandParts[0]) {
            "park" -> parkCar(commandParts[1], commandParts[2])
            "leave" -> freeSpot(commandParts[1].toInt())
        }
    }

    private fun parkCar(carNumber: String, carColor: String) {
        val emptySlotIndex = parkingSlots.indexOfFirst { it == null }
        if (emptySlotIndex == -1) {
            println("Sorry, the parking lot is full.")
            return
        }
        parkingSlots[emptySlotIndex] = Car(carNumber, carColor)
        println("$carColor car parked in spot ${emptySlotIndex + 1}.")
    }

    private fun freeSpot(spotNumber: Int) {
        if (spotNumber <= 0 || spotNumber > numberOfParkingSpots) {
            return
        }

        val parkedCar = parkingSlots[spotNumber - 1]
        if (parkedCar != null) {
            parkingSlots[spotNumber - 1] = null
            println("Spot $spotNumber is free.")
        } else {
            println("There is no car in spot $spotNumber.")
        }
    }
}