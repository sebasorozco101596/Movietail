package com.sebasorozcob.www.movietail.view.fragments.credits

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.sebasorozcob.www.domain.common.Constants
import com.sebasorozcob.www.domain.common.Constants.IMAGE_BASE_URL
import com.sebasorozcob.www.domain.common.Constants.MOVIE_RESULT_KEY
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.adapter.GenresAdapter
import com.sebasorozcob.www.movietail.databinding.FragmentMoviesBinding
import com.sebasorozcob.www.movietail.databinding.FragmentOverviewBinding


class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!
    private lateinit var mAdapter: GenresAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentOverviewBinding.inflate(inflater,container,false)

        val args = arguments
        val myBundle: Movie = args!!.getParcelable<Movie>(MOVIE_RESULT_KEY) as Movie

        with(binding) {
            Glide.with(requireContext())
                .load(IMAGE_BASE_URL + myBundle.posterImage)
                .placeholder(R.drawable.ic_movie)
                .into(movieImageView)

            releaseDateTextView.text = myBundle.releaseDate
            titleTextView.text = myBundle.title
            overviewTextView.text = myBundle.overview
            languageTextView.text = myBundle.originalLanguage
            overallTextView.text = myBundle.voteAverage.toString()

            mAdapter = GenresAdapter(myBundle.genreIds)
            genresRecyclerView.adapter = mAdapter
            return root
        }

    }

}