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
    private var parkingSlots = emptyArray<Car?>()

    fun executeCommand(command: String) {
        if (command.isEmpty() || command.isBlank()) {
            return
        }

        val commandParts = command.split(" ")
        when (commandParts[0]) {
            "create" -> createParking(commandParts[1].toInt())
            "park" -> parkCar(commandParts[1], commandParts[2])
            "leave" -> freeSpot(commandParts[1].toInt())
            "status" -> displayParkingStatus()
        }
    }

    private fun createParking(numberOfParkingSpots: Int) {
        parkingSlots = Array<Car?>(numberOfParkingSpots) { null }
        println("Created a parking lot with $numberOfParkingSpots spots.")
    }

    private fun parkCar(carNumber: String, carColor: String) {
        if (isParkingIsNotCreated()) {
            return
        }

        val emptySlotIndex = parkingSlots.indexOfFirst { it == null }
        if (emptySlotIndex == -1) {
            println("Sorry, the parking lot is full.")
            return
        }
        parkingSlots[emptySlotIndex] = Car(carNumber, carColor)
        println("$carColor car parked in spot ${emptySlotIndex + 1}.")
    }

    private fun freeSpot(spotNumber: Int) {
        if (isParkingIsNotCreated()) {
            return
        }

        if (spotNumber !in 1..parkingSlots.size) {
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

    private fun displayParkingStatus() {
        if (isParkingIsNotCreated()) {
            return
        }

        if (parkingSlots.all { it == null }) {
            println("Parking lot is empty.")
            return
        }

        for (i in parkingSlots.indices) {
            if (parkingSlots[i] == null){
                continue
            }
            println("${i + 1} ${parkingSlots[i]?.number} ${parkingSlots[i]?.color}")
        }
    }

    private fun isParkingIsNotCreated(): Boolean {
        return if (parkingSlots.isEmpty()) {
            println("Sorry, a parking lot has not been created.")
            true
        } else {
            false
        }
    }
}