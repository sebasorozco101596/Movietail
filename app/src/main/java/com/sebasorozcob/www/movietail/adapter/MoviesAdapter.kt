package com.sebasorozcob.www.movietail.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebasorozcob.www.domain.common.Constants.IMAGE_BASE_URL
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.databinding.MoviesRowBinding
import java.util.*

class MoviesAdapter(
    private var context: Context,
    private val moviesList: List<Movie>
    ): RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    class MoviesViewHolder(val binding: MoviesRowBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = MoviesRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {

        val movie = moviesList[position]
        val imageURL = IMAGE_BASE_URL + movie.mainImage
        val releaseDate = "Release date: " + movie.releaseDate
        val voteAverage = " " + movie.voteAverage.toString()
        val originalLanguage = " " + movie.originalLanguage

        Glide.with(context)
            .load(imageURL)
            .placeholder(R.drawable.ic_movie)
            .into(holder.binding.movieImageView)

        holder.binding.movieReleaseDate.text = releaseDate
        holder.binding.movieName.text = movie.title
        holder.binding.movieOverview.text = movie.overview
        holder.binding.movieVoteAverage.text = voteAverage
        holder.binding.movieOriginalLanguage.text = originalLanguage

    }

    override fun getItemCount(): Int = moviesList.size
}