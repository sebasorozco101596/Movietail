package com.sebasorozcob.www.movietail.view.fragments.credits

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.domain.common.Constants
import com.sebasorozcob.www.domain.model.Movie
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.adapter.CreditsAdapter
import com.sebasorozcob.www.movietail.databinding.FragmentCreditsBinding
import com.sebasorozcob.www.movietail.viewmodel.CreditsViewModel

class CreditsFragment : Fragment() {

    private val creditsViewModel: CreditsViewModel by activityViewModels()
    private val creditsAdapter by lazy { CreditsAdapter() }

    private var _binding: FragmentCreditsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCreditsBinding.inflate(inflater,container,false)
        setupRecyclerView()

        val args = arguments
        val myBundle: Movie = args!!.getParcelable<Movie>(Constants.MOVIE_RESULT_KEY) as Movie

        lifecycleScope.launchWhenStarted {
            searchApiData(myBundle.id)
        }

        return binding.root
    }

    private fun searchApiData(movieID: Int) {
        showShimmerEffect()
        creditsViewModel.getCredits(movieID)
        creditsViewModel.state.observe(viewLifecycleOwner) {
            if (!it.isLoading) {
                hideShimmerEffect()
                if (it.error.isNotBlank()) {
                    hideShimmerEffect()
                } else {
                    hideShimmerEffect()
                    binding.creditsRecyclerView.visibility = View.VISIBLE
                    Log.d("HERE","" + it.credits.toString())
                    it.credits?.let { credits -> creditsAdapter.setData(credits) }
                }
            }
        }
    }

    private fun setupRecyclerView() {
        binding.creditsRecyclerView.adapter = creditsAdapter
        binding.creditsRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect()
    }

    private fun showShimmerEffect() {
        binding.shimmerTrendingMovies.startShimmer()
        binding.creditsRecyclerView.visibility = View.GONE
    }

    private fun hideShimmerEffect() {
        binding.shimmerTrendingMovies.stopShimmer()
        binding.shimmerTrendingMovies.visibility = View.GONE
        binding.creditsRecyclerView.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}