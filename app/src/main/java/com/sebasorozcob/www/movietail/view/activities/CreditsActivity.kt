package com.sebasorozcob.www.movietail.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import com.sebasorozcob.www.domain.common.Constants.MOVIE_RESULT_KEY
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.adapter.PagerAdapter
import com.sebasorozcob.www.movietail.databinding.ActivityCreditsBinding
import com.sebasorozcob.www.movietail.view.fragments.credits.CreditsFragment
import com.sebasorozcob.www.movietail.view.fragments.credits.OverviewFragment
import com.sebasorozcob.www.movietail.viewmodel.CreditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreditsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreditsBinding

    private val args by navArgs<CreditsActivityArgs>()
    private val creditsViewModel: CreditsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreditsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenStarted {
            creditsViewModel.getCredits(args.movie.id)
        }

        setSupportActionBar(binding.toolBar)
        binding.toolBar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = arrayListOf<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(CreditsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Credits")

        val resultBundle = Bundle()
        resultBundle.putParcelable(MOVIE_RESULT_KEY, args.movie)

        val pagerAdapter = PagerAdapter(
            resultBundle,
            fragments,
            this
        )

        binding.viewPager2.isUserInputEnabled = false
        binding.viewPager2.apply {
            adapter = pagerAdapter
        }

        TabLayoutMediator(binding.tabLayout, binding.viewPager2) {tab, position ->
            tab.text = titles[position]
        }.attach()

        //Toast.makeText(this,"" + args.movie.id,Toast.LENGTH_LONG).show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}