package com.example.tutorial

data class Ticket(val companyName : String , val name : String , var date : String , var seatName : Int)
class TicketNormal(val companyName : String , val name : String , var date : String , var seatName : Int)

//toString () , hashCode() . equals() , c
//toString () , hashCode() . equals() , copy()가 자동으로 만들어짐
//일반 class 와는 다르게 바로 출력이 가능 Java와 다르게 한 class파일에 여러 data class 를 생성함으로써 관련있는 class들끼리는 모아 관리할 수 있다는 장점이 있음

fun main() {
    val ticketA = Ticket("KoreanAir" , "Yohan" , "2020-02-16" , 14)
    val ticketB = TicketNormal("KoreanAir" , "Yohan" , "2020-02-16" , 14)

    println(ticketA)
    println(ticketB)

}