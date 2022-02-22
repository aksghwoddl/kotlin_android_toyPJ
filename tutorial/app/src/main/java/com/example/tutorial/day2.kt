package com.example.tutorial

fun main() {
    val str : String = "aksghwoddl@naver.com"
    println("Hello World!")
    forAndWhile()
    nullCheck()
    ignoreNulls(str)
}

// 배

fun array(){
    val array = arrayOf(1,2,3)
    val list = listOf(1,2,3)
    //array는 값이 변경 가능
    //list는 mutable list VS list로 나뉜다
    val array2 = arrayOf(1,"d",3.4f)

    val list2 = listOf(1,"d" , 11L)

    array[0] = 3
    var result = list.get(0)
    // list[2] = 2 는 불가하다.
    // arrayList는 mutable List로 수정이 가능하다.
    val arrayList = arrayListOf<Int>()
    arrayList.add(10)
    arrayList.add(20)
}

// 반복문 For / While

fun forAndWhile() {
    val student  = arrayListOf("joyce" , "james" , "jenny" , "jennifer")

    for( name in student){
        println("${name}")
    }

    for ((index , name) in student.withIndex()){
        println("${index +1}번쩨 학생은 ${name}입니다.")
    }
    var sum : Int = 0
    for(i in 1..10){
        sum += i
    }
    println(sum)
    sum = 0
    //step
    for(i in 1..20 step 2){
        sum += i
    }
    println(sum)
    sum = 0
    //downTo
    for(i in 10 downTo 1){
        sum += 1
    }
    println(sum)

    var index = 0
    while(index < 10){
        println("Current index is $index")
        index++
    }
}

// Nullable / NonNull

fun  nullCheck(){
    var name : String = "Yohan"

    var nullName : String? = null //Nullable 타입으로 지정 해주기

    var nameInUpperCase = name.toUpperCase()

    var nullNameInUpperCase = nullName?.toUpperCase() //해당 값이 null 이면 null반환 값이 있으면 toUpperCase 실행

    // ?: Null 일 경우 default값을 지정해준다.
    val lastName : String? = null
    val fullName : String = name + " " + (lastName?: "No last Name")
    println(fullName)
}

fun ignoreNulls(str : String?){
    // !! 컴파일러에게 절대로 null값이 아니라는 것을 알려주는 연산자 최대한 사용 지양
    val mNotNull : String = str!!
    val upper = mNotNull.toUpperCase()

    val email = str

    //let 함수는 해당 변수를 람다식 내부로 옮겨주는 역할을 한다. 아래와 같은 경우
    //if(email != null){
    //  println("My email is " + email);
    // }
    email?.let {
        println("my email is ${email}")
    }
}
