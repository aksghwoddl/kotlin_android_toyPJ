package com.example.tutorial

class Book private constructor(val id : Int , val name : String){
    companion object{
        val myBook = "New name"
        fun create() = Book(0 , myBook)
    }
}

// object는 싱글톤 패턴을 적용할때 사용 한번 메모리가 생성되면 더이상 불필요한 메모리를 생성하지 않는다.
object CarFactory {
    val cars  = mutableListOf<Car>()
    fun makeCar( horsePower: Int) : Car{
        val car = Car(horsePower)
        cars.add(car)
        return car
    }
}
data class Car(val horsePower : Int)

fun main() {
    val book = Book.create()
    println("${book.id} ${book.name}")
    val car1 = CarFactory.makeCar(10)
    val car2 = CarFactory.makeCar(200)

    println(car1)
    println(car2)
}