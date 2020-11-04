fun main() {
    val productType = readLine()!!
    val price = readLine()!!
    val product = when (productType) {
        "headphones" -> Headphones(price = price.toInt())
        "smartphone" -> Smartphone(price = price.toInt())
        "tv" -> TV(price = price.toInt())
        "laptop" -> Laptop(price = price.toInt())
        else -> Product(price = price.toInt())
    }
    println(totalPrice(product))
}

open class Product(val price: Int, val tax: Double = 0.0)
class Headphones(price: Int, tax: Double = 0.11) : Product(price, tax)
class Smartphone(price: Int, tax: Double = 0.15) : Product(price, tax)
class TV(price: Int, tax: Double = 0.17) : Product(price, tax)
class Laptop(price: Int, tax: Double = 0.19) : Product(price, tax)

fun totalPrice(product: Product): Int {
    return (product.price + product.price * product.tax).toInt()
}