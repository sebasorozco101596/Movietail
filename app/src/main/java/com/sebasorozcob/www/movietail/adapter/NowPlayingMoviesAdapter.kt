package com.sebasorozcob.www.movietail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebasorozcob.www.domain.common.Constants.IMAGE_BASE_URL
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.databinding.MoviesRowBinding
import com.sebasorozcob.www.movietail.util.MoviesDiffUtil
import com.sebasorozcob.www.movietail.view.fragments.movies.MoviesFragmentDirections
import com.sebasorozcob.www.movietail.view.fragments.search.SearchMoviesFragmentDirections

class NowPlayingMoviesAdapter: RecyclerView.Adapter<NowPlayingMoviesAdapter.MoviesViewHolder>(){

    private var moviesList = emptyList<Movie>()

    class MoviesViewHolder(private val binding: MoviesRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            val imageURL = if (movie.mainImage != null) {
                IMAGE_BASE_URL + movie.mainImage
            } else {
                IMAGE_BASE_URL + movie.mainImage
            }

            val releaseDate = movie.releaseDate
            val voteAverage = " " + movie.voteAverage.toString()
            val originalLanguage = " " + movie.originalLanguage

            Glide.with(itemView.context)
                .load(imageURL)
                .placeholder(R.drawable.ic_movie)
                .into(binding.movieImageView)

            binding.movieReleaseDate.text = releaseDate
            binding.movieName.text = movie.title
            //binding.movieOverview.text = movie.overview
            binding.movieVoteAverage.text = voteAverage
            binding.movieOriginalLanguage.text = originalLanguage
            binding.movieCardView.setOnClickListener {
                //Toast.makeText(itemView.context,"touching" + movie.title,Toast.LENGTH_LONG).show()

                try {
                    val action = MoviesFragmentDirections.actionMoviesFragmentToCreditsActivity(movie)
                    it.findNavController()
                        .navigate(action)
                } catch (e: Exception) {
                    it.findNavController()
                        .navigate(R.id.action_searchMoviesFragment_to_creditsActivity)

                    val action = SearchMoviesFragmentDirections.actionSearchMoviesFragmentToCreditsActivity(movie)
                    it.findNavController()
                        .navigate(action)
                }

            }
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

    fun setData(newData: List<Movie>) {
        val recipesDiffUtil = MoviesDiffUtil(moviesList, newData)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        moviesList = newData
        diffUtilResult.dispatchUpdatesTo(this)
    }
}