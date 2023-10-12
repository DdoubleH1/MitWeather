package com.example.mitweather.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mitweather.data.model.Location
import com.example.mitweather.databinding.LayoutLocationItemBinding


interface OnItemClickListener {
    fun onItemClicked(item: Location, view: LayoutLocationItemBinding)
}

class LocationAdapter(
    private val locationItemList: List<Location>,
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
        holder.locationTemp.text = "38\u00B0"
        holder.locationWeather.text = "Sunny"
    }


}