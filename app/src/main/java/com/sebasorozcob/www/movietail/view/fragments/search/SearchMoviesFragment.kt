package com.sebasorozcob.www.movietail.view.fragments.search

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.movietail.adapter.MoviesAdapter
import com.sebasorozcob.www.movietail.databinding.FragmentSearchMoviesBinding
import com.sebasorozcob.www.movietail.viewmodel.SearchMoviesViewModel

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

        hideShimmerEffect()

        binding.searchViewMovies.setOnQueryTextListener(object: SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(text: String?): Boolean {
                text?.let { query -> searchApiData(query) }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { query -> searchApiData(query) }
                return true
            }

        })

        return binding.root
    }

    private fun searchApiData(query: String) {
        showShimmerEffect()
        searchMoviesViewModel.searchMovies(query)
        searchMoviesViewModel.state.observe(viewLifecycleOwner) {
            if (!it.isLoading) {
                hideShimmerEffect()
                if (it.error.isNotBlank()) {
                    hideShimmerEffect()
                } else {
                    hideShimmerEffect()
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                    //Log.d("HEREE","" + it.movies?.results!!)
                    it.movies?.let { movies -> moviesAdapter.setData(movies) }
                    //moviesAdapter = MoviesAdapter(this, it.movies?.results!!)
                    //binding.recyclerView.adapter = moviesAdapter
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.moviesRecyclerView.adapter = moviesAdapter
        binding.moviesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.shimmerFrameLayout.startShimmer()
        binding.moviesRecyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerFrameLayout.stopShimmer()
        binding.shimmerFrameLayout.visibility = View.GONE
        binding.moviesRecyclerView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}