package com.example.mininytimes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.mininytimes.network.Result
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class NewsAdapter(val context: Context) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    var news: List<Result> = ArrayList()

    class NewsViewHolder(val view: View) : RecyclerView.ViewHolder(view)

    val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        // Called when a new, empty view is created
        val view = inflater.inflate(R.layout.item_news, parent,  false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        // Tells the recycler view how many items to display
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        // Called when a view is about to be scrolled on to the screen
        holder.itemView.headline.text = news[position].title
        holder.itemView.summary.text = news[position].abstract

        // Find the image url of the "mediumThreeByTwo210" crop
        val imageUrl = news[position].multimedia.firstOrNull { it.format == "mediumThreeByTwo210" }?.url
        // Load it into the ImageView
        Picasso.get().load(imageUrl).into(holder.itemView.imageView)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, news[position].title, Toast.LENGTH_LONG).show()
        }
    }

    fun updateNews(news: List<Result>) {
        this.news = news
        notifyDataSetChanged()
    }
}