package com.example.tsotne_shalva_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.shualeduri.DataLoader
import com.example.shualeduri.WeathersModel
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_weather.*
import java.lang.Exception

class WeatherActivity : AppCompatActivity() {
    val apiKey = "faf85372fe7d106d1743a6cf6f67fcd8"
    val unitMetric = "metric"

    private lateinit var adapter: WeathersRecyclerViewAdapter
    private var weathers = ArrayList<WeathersModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        logOut()
        getWeather("Tbilisi")
        addCityButton.setOnClickListener{
            getWeather(cityEditText.text.toString())
        }
    }

    fun logOut(){
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getWeather(city:String){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WeathersRecyclerViewAdapter(weathers, this)
        recyclerView.adapter = adapter

        DataLoader.getRequest(city, apiKey, unitMetric,  object :CustomCallback{

            override fun onSuccess(result: String) {
                try {
                    val model = Gson().fromJson(result, WeathersModel::class.java)
                    if (result != "null"){
                        weathers.addAll(listOf(model))
                        adapter.notifyDataSetChanged()
                    }
                }catch (e: Exception){}
            }
            override fun onFailure(errMessage: String) {
                super.onFailure(errMessage)
            }
        })
    }
}

