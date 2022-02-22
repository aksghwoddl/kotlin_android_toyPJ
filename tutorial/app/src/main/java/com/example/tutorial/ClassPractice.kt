package com.example.tutorial

open class Human (val name : String = "Anonymous"){ //constructor를 사용하여 생성자 만들수 있으며 생략 가능
    init {
        println("${name} has been born!!!!") //생성자 생성시에 실행할 코드 블럭 (주생성자)
    }
    constructor(name : String , age : Int) : this(name){
        println("my name is ${name}. I'm $age years old") // 부생성자 블록 , 부생성자는 무조건 주 생성자의 값을 상속 받아야 한다.
    }
    fun eatingCake(){
        println("This is so Yummy!!!")
    }

    open fun singASong(){
        println("LaLaLa")
    }
}

class Korean : Human() { //Kotlin에서 class는 기본적으로 final로 지정되어있음 그렇기에 상속을 받기 위해서는 상속받을 class를 open해주어야
    override fun singASong() {
        super.singASong()
        println("라랄라라라")
    }
}

fun main(){
    val human = Human("yohan")
    human.eatingCake()

    val stranger = Human() // 위 클래스와 같이 기본값을 지정 가
    val mom = Human("KyungSook" , 52)
    println("This human's name is ${human.name}")
    println("This human's name is ${stranger.name}") // 기본값 출력

    val korean = Korean()
    korean.singASong()
}