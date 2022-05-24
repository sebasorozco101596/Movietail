package com.sebasorozcob.www.movietail.view.fragments.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sebasorozcob.www.domain.common.Constants
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.domain.model.NowPlayingMovies
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.adapter.MoviesAdapter
import com.sebasorozcob.www.movietail.adapter.NowPlayingMoviesAdapter
import com.sebasorozcob.www.movietail.databinding.FragmentMoviesBinding
import com.sebasorozcob.www.movietail.viewmodel.MainViewModel
import com.sebasorozcob.www.movietail.viewmodel.NowPlayingMoviesViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MoviesFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val nowInCineViewModel: NowPlayingMoviesViewModel by activityViewModels()
    //private val creditsViewModel: CreditsViewModel by activityViewModels()
    private val moviesAdapter by lazy { MoviesAdapter() }
    private val nowInCineAdapter by lazy { NowPlayingMoviesAdapter() }

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var listMovies: ArrayList<Movie> = ArrayList()
    private var listNowInCineMovies: ArrayList<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater,container,false)
        setupRecyclerView()

        binding.moviesFloating.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_recipesBottomSheet)
        }

        binding.swipe.setOnRefreshListener {
            lifecycleScope.launch {
                showTrendingShimmerEffect()
                showNowInCineShimmerEffect()
                listMovies.clear()
                listNowInCineMovies.clear()
                mainViewModel.restartPage()
                nowInCineViewModel.restartPage()
                delay(1300)
                searchApiData()
                searchApiNowInCine()
            }
        }

        binding.titleTextView.setOnClickListener {
            lifecycleScope.launch {
                showTrendingShimmerEffect()
                delay(1000)
                searchApiData()
            }
        }

        binding.rightPageImageView.setOnClickListener {

            lifecycleScope.launch {
                mainViewModel.moveRight()
                showTrendingShimmerEffect()
                delay(1000)
                searchApiData()
            }
        }

        binding.rightPageCineImageView.setOnClickListener {

            lifecycleScope.launch {
                nowInCineViewModel.moveRight()
                showNowInCineShimmerEffect()
                delay(1000)
                searchApiNowInCine()
            }
        }

        mainViewModel.state.observe(viewLifecycleOwner) {
            if (!it.isLoading) {
                hideTrendingShimmerEffect()
                if (it.error.isNotBlank()) {
                    hideTrendingShimmerEffect()
                } else {
                    hideTrendingShimmerEffect()
                    binding.swipe.isRefreshing = false
                    it.movies?.let { movies -> addMovies(movies.results,Constants.Recyclers.POPULAR)}
                    moviesAdapter.setData(listMovies)
                    if (listMovies.size > 21) {
                        swipeItem(
                            listMovies.size - 19,
                            Constants.Recyclers.POPULAR)
                    }
                }
            }
        }

        nowInCineViewModel.state.observe(viewLifecycleOwner) {
            if (!it.isLoading) {
                hideNowInCineShimmerEffect()
                if (it.error.isNotBlank()) {
                    hideNowInCineShimmerEffect()
                } else {
                    hideNowInCineShimmerEffect()
                    binding.swipe.isRefreshing = false
                    it.movies?.let { movies -> addMovies(movies.results,Constants.Recyclers.NOW_CINE)}
                    nowInCineAdapter.setData(listNowInCineMovies)
                    if (listNowInCineMovies.size > 21) {
                        swipeItem(
                            listNowInCineMovies.size - 19,
                            Constants.Recyclers.NOW_CINE)
                    }
                }
            }
        }

        return binding.root
    }

    private fun addMovies(movies: List<Movie>, list: Constants.Recyclers) {
        if (list == Constants.Recyclers.POPULAR) {
            movies.forEachIndexed { index, movie ->
                if (!listMovies.contains(movie)) {
                    listMovies.add(movie)
                    moviesAdapter.notifyItemInserted(listMovies.size + index)
                }
            }
        } else if (list == Constants.Recyclers.NOW_CINE) {
            movies.forEachIndexed { index, movie ->
                if (!listNowInCineMovies.contains(movie)) {
                    listNowInCineMovies.add(movie)
                    nowInCineAdapter.notifyItemInserted(listNowInCineMovies.size + index)
                }
            }
        }
    }

    private fun swipeItem(toPosition: Int, recycler: Constants.Recyclers) {

        if (recycler == Constants.Recyclers.POPULAR) {
            binding.moviesRecyclerView.scrollToPosition(toPosition)
        } else if (recycler == Constants.Recyclers.NOW_CINE) {
            binding.nowInCineRecyclerView.scrollToPosition(toPosition)
        }

    }

    private fun searchApiData() {
        Log.d("SIZEE","CALLED")
        showTrendingShimmerEffect()
        mainViewModel.getMovies()
    }

    private fun searchApiNowInCine() {
        showNowInCineShimmerEffect()
        nowInCineViewModel.getMovies()
    }

    private fun setupRecyclerView() {
        binding.moviesRecyclerView.adapter = moviesAdapter
        binding.nowInCineRecyclerView.adapter = nowInCineAdapter
        showTrendingShimmerEffect()
        showNowInCineShimmerEffect()
    }

    private fun showTrendingShimmerEffect() {
        binding.titleTextView.visibility = View.INVISIBLE
        binding.shimmerTrendingMovies.visibility = View.VISIBLE
        binding.shimmerTrendingMovies.startShimmer()
        binding.moviesRecyclerView.visibility = View.INVISIBLE
    }

    private fun hideTrendingShimmerEffect() {
        binding.titleTextView.visibility = View.VISIBLE
        binding.shimmerTrendingMovies.stopShimmer()
        binding.shimmerTrendingMovies.visibility = View.INVISIBLE
        binding.moviesRecyclerView.visibility = View.VISIBLE
        binding.rightPageImageView.visibility = View.VISIBLE
    }

    private fun showNowInCineShimmerEffect() {
        binding.nowInCineTextView.visibility = View.INVISIBLE
        binding.shimmerNowInCine.visibility = View.VISIBLE
        binding.shimmerNowInCine.startShimmer()
        binding.nowInCineRecyclerView.visibility = View.INVISIBLE
    }

    private fun hideNowInCineShimmerEffect() {
        binding.nowInCineTextView.visibility = View.VISIBLE
        binding.shimmerNowInCine.stopShimmer()
        binding.shimmerNowInCine.visibility = View.INVISIBLE
        binding.nowInCineRecyclerView.visibility = View.VISIBLE
        binding.rightPageCineImageView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        Log.d("SIZEE","DESTROY")
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
        Log.d("SIZEE","HERE WE GO")
    }

    override fun onStart() {
        super.onStart()
        Log.d("SIZEE","HERE WE GO" + "${listMovies.size} " + "${listNowInCineMovies.size}")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (listMovies.size == 0 && listNowInCineMovies.size == 0) {
            MainScope().launch {
                delay(1500)
                searchApiNowInCine()
                searchApiData()
            }
        }
    }
}