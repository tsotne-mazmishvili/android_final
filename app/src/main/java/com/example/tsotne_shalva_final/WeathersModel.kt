package com.example.shualeduri

class WeathersModel() {
    val name = ""
    lateinit var weather:MutableList<Weather>
    lateinit var main:MutableList<Main>
    lateinit var wind:MutableList<Wind>

    class Weather{
        val id = 0
        val main = ""
        val description = ""
        val icon = ""
    }

    class Main{
        val temp:Double = 0.0
        val pressure:Double = 0.0
        val humidity:Double = 0.0
    }

    class Wind{
        val speed = ""
    }
}