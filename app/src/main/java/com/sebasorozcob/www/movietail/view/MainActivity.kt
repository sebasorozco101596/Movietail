package com.sebasorozcob.www.movietail.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.sebasorozcob.www.movietail.adapter.MoviesAdapter
import com.sebasorozcob.www.movietail.databinding.ActivityMainBinding
import com.sebasorozcob.www.movietail.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavView.background = null
        binding.bottomNavView.menu.getItem(2).isEnabled = false

        viewModel.state.observe(this) {
            if (!it.isLoading) {
                binding.progressBar.visibility = View.INVISIBLE
                if (it.error.isNotBlank()) {
                    binding.errorText.visibility = View.VISIBLE
                    binding.errorText.text = it.error
                } else {
                    binding.recyclerView.visibility = View.VISIBLE
                    moviesAdapter = MoviesAdapter(this@MainActivity, it.movies?.results!!)
                    binding.recyclerView.adapter = moviesAdapter
                }
                //Log.d("dataaaaaaaaaaaa", "" + it.movies?.results!!)
            }
        }
    }
}

