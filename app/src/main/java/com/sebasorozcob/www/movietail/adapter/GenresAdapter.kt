package com.sebasorozcob.www.movietail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sebasorozcob.www.movietail.databinding.GenresRowBinding


class GenresAdapter(private val genresList: ArrayList<Int>): RecyclerView.Adapter<GenresAdapter.GenresViewHolder>() {

    private val hashMap: HashMap<Int, String> = HashMap()

    class GenresViewHolder(val binding: GenresRowBinding):
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): GenresViewHolder {
        hashMap[28] = "Action"
        hashMap[12] = "Adventure"
        hashMap[16] = "Animation"
        hashMap[35] = "Comedy"
        hashMap[80] = "Crime"
        hashMap[99] = "Documentary"
        hashMap[18] = "Drama"
        hashMap[10751] = "Family"
        hashMap[14] = "Fantasy"
        hashMap[36] = "History"
        hashMap[27] = "Horror"
        hashMap[10402] = "Music"
        hashMap[9648] = "Mystery"
        hashMap[10749] = "Romance"
        hashMap[878] = "Science Fiction"
        hashMap[10770] = "TV Movie"
        hashMap[53] = "Thriller"
        hashMap[10752] = "War"
        hashMap[37] = "Western"

        val binding = GenresRowBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return GenresViewHolder(binding)

    }

    override fun onBindViewHolder(holder: GenresViewHolder, position: Int) {
        val genre = genresList[position]
        holder.binding.genreTextView.text = hashMap[genre]
    }

    override fun getItemCount(): Int = genresList.size

}