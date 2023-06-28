package com.sanmobi.kotlin_retrofit_template.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sanmobi.kotlin_retrofit_template.R
import com.sanmobi.kotlin_retrofit_template.model.Photo

class PhotoAdapter (val context: Context) : RecyclerView.Adapter<PhotoAdapter.MyViewHolder>() {

    var photoList : List<Photo> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.listitem_photo,parent,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.itemView.setOnClickListener {
            var photo = photoList.get(position)

            Toast.makeText(context,photo.title,Toast.LENGTH_SHORT).show();

        }
        holder.tvName.text = photoList.get(position).title
        Glide.with(context).load(photoList.get(position).thumbnailUrl)
            .apply(RequestOptions().centerCrop())
            .into(holder.image)
    }

    fun setMovieListItems(photoList: List<Photo>){
        this.photoList = photoList;
        notifyDataSetChanged()
    }

    class MyViewHolder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val tvName: TextView = itemView!!.findViewById(R.id.title)
        val image: ImageView = itemView!!.findViewById(R.id.image)

    }
}