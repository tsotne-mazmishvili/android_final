package com.example.shualeduri

import android.util.Log
import com.example.tsotne_shalva_final.CustomCallback
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.*

object DataLoader {
    var retrofit = Retrofit.Builder()
        .addConverterFactory(ScalarsConverterFactory.create())
        .baseUrl("https://api.openweathermap.org/")
        .build()

    var service = retrofit.create(ApiRetrofit::class.java)

    fun getRequest(city: String, apiKey: String, unitMetric: String, customCallback: CustomCallback){
        val call = service.getRequest(city, apiKey, unitMetric)

        call.enqueue(callback(customCallback))
    }

    private fun callback(customCallback: CustomCallback) = object : Callback<String> {
        override fun onFailure(call: Call<String>, t: Throwable) {
            customCallback.onFailure(t.message.toString())
        }

        override fun onResponse(call: Call<String>, response: Response<String>) {
            customCallback.onSuccess(response.body().toString())
        }
    }
}


interface ApiRetrofit{
    @GET("data/2.5/weather")
    fun getRequest(@Query("q")city: String, @Query("appid")apiKey: String,  @Query("units")unitMetric: String ): Call<String>
}