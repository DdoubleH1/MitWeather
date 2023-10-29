package com.example.mitweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mitweather.data.model.Location
import com.example.mitweather.databinding.LayoutLocationItemBinding
import com.example.mitweather.utils.Standardize


interface OnItemClickListener {
    fun onItemClicked(item: Location, view: LayoutLocationItemBinding)
}

class LocationAdapter(
    private val locationItemList: MutableList<Location>,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {


    inner class LocationViewHolder(
        itemView: LayoutLocationItemBinding,
        onItemClickListener: OnItemClickListener
    ) : RecyclerView.ViewHolder(itemView.root) {
        var locationName = itemView.tvLocationName
        var locationTemp = itemView.tvCurrentTemp
        var locationWeather = itemView.tvWeatherStatus

        init {
            itemView.apply {
                locationItem.setOnClickListener {
                    onItemClickListener.onItemClicked(
                        locationItemList[adapterPosition],
                        itemView
                    )
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val mLocationItem: LayoutLocationItemBinding =
            LayoutLocationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(mLocationItem, onItemClickListener)
    }

    override fun getItemCount(): Int {
        return locationItemList.size
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val location = locationItemList[position]
        holder.locationName.text = location.cityName
        holder.locationTemp.text =
            Standardize.standardizeDegreeCelsius(location.currentWeather!!.main.temp)
        holder.locationWeather.text = location.currentWeather!!.weather[0].description
    }


}