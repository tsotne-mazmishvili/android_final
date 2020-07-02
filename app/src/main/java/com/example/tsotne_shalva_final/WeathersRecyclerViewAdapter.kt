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
        private lateinit var model2: WeathersModel.Main

        fun onBind(){
            model = weathers[adapterPosition]
            model2 = weathers[adapterPosition].main
            itemView.temperatureTextView.setText(model2.temp.toString())
        }

    }

}
