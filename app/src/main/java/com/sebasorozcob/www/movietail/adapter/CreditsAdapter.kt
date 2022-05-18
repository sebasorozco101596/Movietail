package com.sebasorozcob.www.movietail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sebasorozcob.www.domain.common.Constants.IMAGE_BASE_URL
import com.sebasorozcob.www.domain.model.Credit
import com.sebasorozcob.www.domain.model.Credits
import com.sebasorozcob.www.domain.model.Movies
import com.sebasorozcob.www.movietail.R
import com.sebasorozcob.www.movietail.databinding.CreditsRowBinding
import com.sebasorozcob.www.movietail.databinding.MoviesRowBinding
import com.sebasorozcob.www.movietail.util.MoviesDiffUtil

class CreditsAdapter: RecyclerView.Adapter<CreditsAdapter.CreditsViewHolder>() {

    private var creditsList = emptyList<Credit>()

    class CreditsViewHolder(private val binding: CreditsRowBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(credit: Credit) {

                val imageURL = IMAGE_BASE_URL + credit.profilePhoto
                val name = "(${credit.name})"

                Glide.with(itemView.context)
                    .load(imageURL)
                    .placeholder(R.drawable.ic_person)
                    .into(binding.creditImageView)

                binding.creditName.text = credit.character
                binding.departmentTextView.text = credit.knownDepartment
                binding.actorNameTextView.text = name
            }

            companion object {
                fun from(parent: ViewGroup): CreditsViewHolder {
                    val layoutInflater = LayoutInflater.from(parent.context)
                    val binding = CreditsRowBinding
                        .inflate(layoutInflater, parent, false)
                    return CreditsViewHolder(binding)
                }
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): CreditsViewHolder {
        return CreditsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CreditsViewHolder, position: Int) {
        val currentCredit = creditsList[position]
        holder.bind(currentCredit)
    }

    override fun getItemCount(): Int = creditsList.size

    fun setData(newData: Credits) {
        val recipesDiffUtil = MoviesDiffUtil(creditsList, newData.cast)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        creditsList = newData.cast
        diffUtilResult.dispatchUpdatesTo(this)
    }
}