package com.example.tsotne_shalva_final

import android.annotation.SuppressLint
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shualeduri.WeathersModel
import kotlinx.android.synthetic.main.weather_recyclerview_layout.view.*
import java.lang.Exception

class WeathersRecyclerViewAdapter(
    private val weathers: ArrayList<WeathersModel>,
    weatherActivity: WeatherActivity
    ):RecyclerView.Adapter<WeathersRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WeathersRecyclerViewAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_recyclerview_layout,parent,false))
    }

    override fun getItemCount() = weathers.size
    override fun onBindViewHolder(holder: WeathersRecyclerViewAdapter.ViewHolder, position: Int) {
        try{holder.onBind()}catch (e: Exception){

        }

    }
    inner class ViewHolder(weatherView: View):RecyclerView.ViewHolder(weatherView){
        private lateinit var model: WeathersModel
        private lateinit var modelmain: WeathersModel.Main
        private lateinit var modelweather: ArrayList<WeathersModel.Weather>
        private lateinit var modelwind: WeathersModel.Wind
        @SuppressLint("SetTextI18n")
        fun onBind(){
            model = weathers[adapterPosition]
            modelmain = weathers[adapterPosition].main
            modelweather = weathers[adapterPosition].weather
            modelwind = weathers[adapterPosition].wind
            d("weather",modelweather[0].main)
            var modelweather1:String = modelweather[0].toString()
            itemView.temperatureTextView.setText("Temperature: "+modelmain.temp.toString()+" C")
            itemView.humidityTextView.setText("Humidity: "+modelmain.humidity)
            itemView.cityTextView.setText(model.name)
            itemView.weatherTextView.setText("Sky: "+modelweather[0].main)
            itemView.windTextView.setText("Wind Speed: "+modelwind.speed+" Km/h")

            if(modelweather[0].main.equals("Rain")){
                d("weather","Rainy Sky")
                itemView.mainLinearLayout.setBackgroundResource(R.drawable.rainy_background)
            }
            if(modelweather[0].main.equals("Clouds")){
                d("weather","Cloudy Sky")
                itemView.mainLinearLayout.setBackgroundResource(R.drawable.cloudy_background)
            }
        }

    }

}
