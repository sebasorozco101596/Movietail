package com.sebasorozcob.www.movietail.view.fragments.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.AbsListView
import android.widget.ListView
import androidx.core.view.ViewCompat
import androidx.core.view.doOnNextLayout
import androidx.core.widget.ListViewAutoScrollHelper
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.facebook.shimmer.Shimmer
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.adapter.MoviesAdapter
import com.sebasorozcob.www.movietail.databinding.FragmentMoviesBinding
import com.sebasorozcob.www.movietail.viewmodel.MainViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MoviesFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    //private val creditsViewModel: CreditsViewModel by activityViewModels()
    private val moviesAdapter by lazy { MoviesAdapter() }

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    private var listMovies: ArrayList<Movie> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater,container,false)
        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            delay(1500)
            searchApiData()
        }

        binding.moviesFloating.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_recipesBottomSheet)
        }


        binding.swipe.setOnRefreshListener {
            lifecycleScope.launch {
                showShimmerEffect()
                listMovies.clear()
                mainViewModel.restartPage()
                delay(1300)
                searchApiData()

            }
        }

        binding.titleTextView.setOnClickListener {
            lifecycleScope.launch {
                showShimmerEffect()
                delay(1000)
                searchApiData()
            }
        }

        binding.rightPageImageView.setOnClickListener {

            lifecycleScope.launch {
                mainViewModel.moveRight()
                showShimmerEffect()
                delay(1000)
                searchApiData()
            }
        }

        mainViewModel.state.observe(viewLifecycleOwner) {
            if (!it.isLoading) {
                hideShimmerEffect()
                if (it.error.isNotBlank()) {
                    hideShimmerEffect()
                } else {
                    hideShimmerEffect()
                    binding.swipe.isRefreshing = false
                    if (binding.moviesRecyclerView.visibility == View.INVISIBLE) {
                        binding.moviesRecyclerView.visibility = View.VISIBLE
                    }
                    //Log.d("HERE","" + it.movies?.results!!)
                    //listMovies.clear()
                    it.movies?.let { movies -> listMovies.addAll(movies.results) }
                    //
                    Log.d("SIZEEE", listMovies.size.toString())
                    moviesAdapter.setData(listMovies)
                }
            }
        }

        return binding.root
    }


    private fun searchApiData() {
        showShimmerEffect()
        mainViewModel.getMovies()
    }

    private fun setupRecyclerView() {
        binding.moviesRecyclerView.adapter = moviesAdapter
        //binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.shimmerFrameLayout.visibility = View.VISIBLE
        binding.shimmerFrameLayout.startShimmer()
        binding.moviesRecyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.moviesRecyclerView.visibility = View.VISIBLE
        binding.rightPageImageView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}