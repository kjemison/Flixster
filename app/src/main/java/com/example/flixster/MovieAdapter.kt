package com.example.flixster

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

private const val TAG = "MovieAdapter"

class MovieAdapter(private val context: Context,
                   private val movies: MutableList<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>(),
   View.OnClickListener {

    // EXPENSIVE: creates a view
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.i(TAG, "onCreateViewHolder") // Should stop creating views at 6
        val view = LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    // CHEAP: binds data to an existing viewholder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.i(TAG, "onBindViewHolder position: $position")
        val movie = movies[position]
        holder.bind(movie)
    }

    override fun getItemCount() = movies.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val ivPoster = itemView.findViewById<ImageView>(R.id.ivPoster)
        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvOverview = itemView.findViewById<TextView>(R.id.tvOverview)

        init {
            itemView.setOnClickListener(this)
        }

        // Bind data into view
        fun bind(movie: Movie) {
            tvTitle.text = movie.title
            tvOverview.text = movie.overview
            Glide.with(context).load(movie.posterImageUrl).into(ivPoster)
        }

        override fun onClick(p0: View?) {
            val movie = movies[adapterPosition]
            // Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
            val intent = Intent(context, DetailActivity::class.java)

            // Need to make data parcelable when we pass it in
            intent.putExtra("MOVIE_EXTRA", movie)
            context.startActivity(intent)
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}