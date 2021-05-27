package com.example.place.Adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.place.Data.DataModel
import com.example.place.databinding.EachRowBinding
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import javax.inject.Inject


class PlaceDataAdapter @Inject constructor(): PagingDataAdapter<DataModel.DataModelItem,PlaceDataAdapter.DogsViewHolder>(Diff()) {


    override fun onBindViewHolder(holder: DogsViewHolder, position: Int) {
        val data = getItem(position)
        if(data!= null){
            holder.binds(data)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogsViewHolder  =
        DogsViewHolder(EachRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))


    class DogsViewHolder(private val binding:EachRowBinding) : RecyclerView.ViewHolder(binding.root){
        fun binds(data:DataModel.DataModelItem){
            binding.apply {
                image.load(data.downloadUrl)


            }
        }
    }

    class Diff : DiffUtil.ItemCallback<DataModel.DataModelItem>(){
        override fun areItemsTheSame(oldItem: DataModel.DataModelItem, newItem: DataModel.DataModelItem): Boolean  =
            oldItem.url == newItem.url

        override fun areContentsTheSame(oldItem: DataModel.DataModelItem, newItem: DataModel.DataModelItem): Boolean =
            oldItem == newItem
    }
}