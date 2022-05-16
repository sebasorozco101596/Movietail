package com.sebasorozcob.www.movietail.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebasorozcob.www.domain.common.Constants.IMAGE_BASE_URL
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.databinding.MoviesRowBinding
import com.sebasorozcob.www.movietail.util.MoviesDiffUtil
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.*

class MoviesAdapter: RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>(){

    private var moviesList = emptyList<Movie>()

    class MoviesViewHolder(private val binding: MoviesRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            val imageURL = if (movie.mainImage != null) {
                IMAGE_BASE_URL + movie.mainImage
            } else {
                IMAGE_BASE_URL + movie.mainImage
            }

            val releaseDate = "Release date: " + movie.releaseDate
            val voteAverage = " " + movie.voteAverage.toString()
            val originalLanguage = " " + movie.originalLanguage

            Glide.with(itemView.context)
                .load(imageURL)
                .placeholder(R.drawable.ic_movie)
                .into(binding.movieImageView)

            binding.movieReleaseDate.text = releaseDate
            binding.movieName.text = movie.title
            binding.movieOverview.text = movie.overview
            binding.movieVoteAverage.text = voteAverage
            binding.movieOriginalLanguage.text = originalLanguage
        }

        companion object {
            fun from(parent: ViewGroup): MoviesViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = MoviesRowBinding.inflate(layoutInflater, parent, false)
                return MoviesViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        return MoviesViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val currentMovie = moviesList[position]
        holder.bind(currentMovie)
    }

    override fun getItemCount(): Int = moviesList.size

    fun setData(newData: Movies) {
        val recipesDiffUtil = MoviesDiffUtil(moviesList, newData.results)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        moviesList = newData.results
        diffUtilResult.dispatchUpdatesTo(this)
    }
}