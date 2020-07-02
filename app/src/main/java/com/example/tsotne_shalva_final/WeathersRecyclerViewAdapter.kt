package com.example.tsotne_shalva_final

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.shualeduri.WeathersModel
import kotlinx.android.synthetic.main.weather_recyclerview_layout.view.*

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
        holder.onBind()
    }
    inner class ViewHolder(weatherView: View):RecyclerView.ViewHolder(weatherView){
        private lateinit var model: WeathersModel
        private lateinit var modeltemp: WeathersModel.Main
//        @SuppressLint("SetTextI18n")
        fun onBind(){
            model = weathers[adapterPosition]
            itemView.temperatureTextView.setText(modeltemp.temp.toString())

//აქ უნდა დავუსეტო და ვერ ვუსეტავ recyclerview layout - ს შემოსული რაღაცები. need help here, ასევე
//უნდა იპარსებოდეს მთლიანი WeathersModel ხოლო აქ იპარსება WeathersModel.Main. ამაზე რას იტყვი?
        }

    }

//
//    class WeatherViewAdapter(
//        private val items: ArrayList<CityModel>,
//        contentTableActivity: ContentTableActivity
//    ): RecyclerView.Adapter<WeatherViewAdapter.ViewHolder>() {
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewAdapter.ViewHolder {
//            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.weather_recyclerview_layout,parent,false))
//        }
}
