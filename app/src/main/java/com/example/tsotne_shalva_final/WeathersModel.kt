package com.example.shualeduri

import java.util.*
import kotlin.collections.ArrayList

class WeathersModel() {
    var name = ""
    lateinit var weather:ArrayList<Weather>
    lateinit var main: Main
    lateinit var wind: Wind

    class Weather{
        var id = 0
        var main = ""
        var description = ""
        var icon = ""
    }

    class Main{
        var temp:Double = 0.0
        var pressure:Double = 0.0
        var humidity:Double = 0.0
    }

    class Wind{
        var speed:Double =  0.0
        var deg:Double = 0.0
    }

}