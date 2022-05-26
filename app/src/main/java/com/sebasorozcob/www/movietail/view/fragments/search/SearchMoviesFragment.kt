package com.sebasorozcob.www.movietail.view.fragments.search

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.movietail.adapter.MoviesAdapter
import com.sebasorozcob.www.movietail.databinding.FragmentSearchMoviesBinding
import com.sebasorozcob.www.movietail.viewmodel.SearchMoviesViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SearchMoviesFragment : Fragment() {

    private val searchMoviesViewModel: SearchMoviesViewModel by activityViewModels()
    private val moviesAdapter by lazy { MoviesAdapter() }

    private var _binding: FragmentSearchMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        _binding = FragmentSearchMoviesBinding.inflate(inflater,container,false)
        //binding.lifecycleOwner = this
        setupRecyclerView()

        //hideShimmerEffect()

        lifecycleScope.launchWhenStarted {
            delay(1500)
            searchApiData("")
        }

        binding.searchViewMovies.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(text: String?): Boolean {
                text?.let { query -> searchApiData(query) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                showShimmerEffect()
                lifecycleScope.launch {
                    delay(1000)
                    newText?.let {
                            query -> searchApiData(query)
                    }
                }
                return true
            }
        })

        searchMoviesViewModel.state.observe(viewLifecycleOwner) {
            if (!it.isLoading) {
                hideShimmerEffect()
                if (it.error.isNotBlank()) {
                    hideShimmerEffect()
                } else {
                    hideShimmerEffect()
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                    it.movies?.let { movies -> moviesAdapter.setData(movies.results) }
                }
            }
        }

        return binding.root
    }

    private fun searchApiData(query: String) {
        showShimmerEffect()
        searchMoviesViewModel.searchMovies(query)
    }

    private fun setupRecyclerView() {
        binding.moviesRecyclerView.adapter = moviesAdapter
        binding.moviesRecyclerView.layoutManager = GridLayoutManager(requireContext(),3)
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.shimmerTrendingMovies.visibility = View.VISIBLE
        binding.shimmerTrendingMovies.startShimmer()
        binding.moviesRecyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerTrendingMovies.stopShimmer()
        binding.shimmerTrendingMovies.visibility = View.GONE
        binding.moviesRecyclerView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}