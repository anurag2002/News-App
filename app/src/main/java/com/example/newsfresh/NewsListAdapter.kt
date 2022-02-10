package com.example.newsfresh

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

@Suppress("DEPRECATION")
class NewsListAdapter(private val listener: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {

    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder       //Called when view holder is created after that it is recycled
    {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)       //to convert item_news.xml to view
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener { listener.onItemClicked(items[viewHolder.adapterPosition]) }
        return viewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int)        //binds the data within the holder
    {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.pubDate.text = currentItem.pubDate
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    override fun getItemCount(): Int            //tells the no of items. it is called first time only
    {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateNews(updatedNews: ArrayList<News>) {
        items.clear()
        items.addAll(updatedNews)

        notifyDataSetChanged()      //calls all the view holder functions
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)            //this contains the items in which values are to be given later
{
    val titleView: TextView = itemView.findViewById(R.id.title)
    val image: ImageView = itemView.findViewById(R.id.image)
    val pubDate: TextView = itemView.findViewById(R.id.pubDate)

}

interface NewsItemClicked {
    fun onItemClicked(item: News)
}