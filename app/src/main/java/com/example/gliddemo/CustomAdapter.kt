package com.example.gliddemo

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class CustomAdapter(private val context:Context,private val mList: List<String>) : RecyclerView.Adapter<CustomAdapter.ViewHolder>() {
    class ViewHolder(iteaView: View):RecyclerView.ViewHolder(iteaView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomAdapter.ViewHolder {
       val view= LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_row,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomAdapter.ViewHolder, position: Int) {
        val ItemsViewModel = mList.get(position)
        Glide.with(context)
            .load(ItemsViewModel)
            .error(R.drawable.baseline_image_not_supported_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
       return mList.size
    }
}