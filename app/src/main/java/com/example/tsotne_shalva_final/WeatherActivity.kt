package com.example.tsotne_shalva_final

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.shualeduri.DataLoader
import com.example.shualeduri.WeathersModel
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_weather.*
import java.lang.Exception

class WeatherActivity : AppCompatActivity() {
    val apiKey = "faf85372fe7d106d1743a6cf6f67fcd8"

    private lateinit var adapter: WeathersRecyclerViewAdapter
    private var weathers = ArrayList<WeathersModel.Main>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        logOut()
        getWeather()
    }

    fun logOut(){
        logoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun getWeather(){
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = WeathersRecyclerViewAdapter(weathers, this)
        recyclerView.adapter = adapter

        DataLoader.getRequest("Tbilisi", apiKey, object :CustomCallback{
            override fun onSuccess(result: String) {
                Log.d("asd", result)
                try {
                    val model = Gson().fromJson(result, WeathersModel::class.java)
                    weathers.addAll(model.main)
                    adapter.notifyDataSetChanged()
                }catch (e: Exception){}
            }
        })
    }
}
