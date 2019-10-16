package com.example.mininytimes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mininytimes.network.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var news : List<Result> = ArrayList()

    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // Called when a new empty view is created.
        val view = inflater.inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)

    }

    override fun getItemCount(): Int {
        // Tell the recycler view how many items to display
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        // Called right before a new item is scrolled onto the screen
        holder.itemView.headline.text = news[position].title
        holder.itemView.summary.text = news[position].abstract

        val imageUrl = news[position].multimedia.firstOrNull { it.format == "mediumThreeByTwo210" }?.url
        Picasso.get().load(imageUrl).into(holder.itemView.image)
    }

    fun updateNews(news: List<Result>) {
        this.news = news
        notifyDataSetChanged()
    }

    class NewsViewHolder(val view : View) : RecyclerView.ViewHolder(view)

}