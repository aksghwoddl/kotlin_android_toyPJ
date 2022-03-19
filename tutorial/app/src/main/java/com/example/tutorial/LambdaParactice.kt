package com.example.tutorial

//1. Lambda
//람다식은 ㄴ우리가 마치 value 처럼 다룰 수 있는 익명함수이다.
// 1) 메소드의 파라미터로 념겨줄수가 있다.
// 2) return 값으로 사용할 수가 있다.

// 람다의 기본정의
// val landaName : Type = {argumentList -> codeBody}

val square: (Int) -> (Int) = {number -> number*number}

val nameAge ={name : String , age : Int ->
    "my name is ${name} , I'm ${age}"
}

// 람다의 Return
val calculteGrade : (Int) -> String = {
    when(it){
        in 0..40 -> "fail"
        in 41..70 -> "pass"
        in 71..100 -> "perfect"
        else -> "Error!!"
    }
}

// 람다를 표현하는 여러가지 방법
fun invokeLambda(lambda : (Double) -> Boolean) : Boolean {
    return lambda(5.2343)
}

//2. 확장함수
val pizzaGreat : String.() -> String = {
    this + "Pizza is the best!!"
}

fun extendString(name : String , age : Int) : String {
    val introduceMyself : String.(Int) -> String = {"I am ${this} and ${it} years old"}
    return name.introduceMyself(age)
}

fun main() {
    println(square(12))
    println(nameAge("Yohan" , 28))
    println(calculteGrade(99))

    val lambda = {number : Double ->
        number == 4.3213
    }
    println(invokeLambda(lambda))
    println(invokeLambda { it > 3.22 })

    val a = "Yohan said "
    println(a.pizzaGreat())
    println(extendString("Lee" , 28))
}