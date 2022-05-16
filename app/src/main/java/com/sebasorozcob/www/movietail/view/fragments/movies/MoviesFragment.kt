package com.sebasorozcob.www.movietail.view.fragments.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.adapter.MoviesAdapter
import com.sebasorozcob.www.movietail.databinding.FragmentMoviesBinding
import com.sebasorozcob.www.movietail.viewmodel.MainViewModel

class MoviesFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val moviesAdapter by lazy { MoviesAdapter() }

    private var _binding: FragmentMoviesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoviesBinding.inflate(inflater,container,false)
        //binding.lifecycleOwner = this
        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            searchApiData()
        }

        binding.moviesFloating.setOnClickListener {
            findNavController().navigate(R.id.action_moviesFragment_to_recipesBottomSheet)
        }

        return binding.root
    }

    private fun searchApiData() {
        showShimmerEffect()
        mainViewModel.state.observe(viewLifecycleOwner) {
            if (!it.isLoading) {
                hideShimmerEffect()
                if (it.error.isNotBlank()) {
                    hideShimmerEffect()
                } else {
                    hideShimmerEffect()
                    binding.moviesRecyclerView.visibility = View.VISIBLE
                    Log.d("HEREE","" + it.movies?.results!!)
                    moviesAdapter.setData(it.movies!!)
                    //moviesAdapter = MoviesAdapter(this, it.movies?.results!!)
                    //binding.recyclerView.adapter = moviesAdapter
                }
                //Log.d("dataaaaaaaaaaaa", "" + it.movies?.results!!)
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