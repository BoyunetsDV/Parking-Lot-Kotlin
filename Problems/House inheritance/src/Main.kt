fun main() {
    var rooms = readLine()!!.toInt()
    var price = readLine()!!.toInt()
    rooms = if (rooms < 1) {
        1
    } else {
        rooms
    }
    price = when {
        price in 0..1_000_000 -> {
            price
        }
        price < 0 -> {
            0
        }
        else -> {
            1_000_000
        }
    }

    val house = when (rooms) {
        1 -> Cabin(rooms, price)
        in 2..3 -> Bungalow(rooms, price)
        4 -> Cottage(rooms, price)
        in 5..7 -> Mansion(rooms, price)
        else -> Palace(rooms, price)
    }
    println(totalPrice(house))
}

open class House(val rooms: Int, val price: Int, val coefficient: Double = 0.0)

class Cabin(rooms: Int, price: Int, coefficient: Double = 1.0) : House(rooms, price, coefficient)
class Bungalow(rooms: Int, price: Int, coefficient: Double = 1.2) : House(rooms, price, coefficient)
class Cottage(rooms: Int, price: Int, coefficient: Double = 1.25) : House(rooms, price, coefficient)
class Mansion(rooms: Int, price: Int, coefficient: Double = 1.4) : House(rooms, price, coefficient)
class Palace(rooms: Int, price: Int, coefficient: Double = 1.6) : House(rooms, price, coefficient)

fun totalPrice(house: House): Int {
    return (house.price * house.coefficient).toInt()
}