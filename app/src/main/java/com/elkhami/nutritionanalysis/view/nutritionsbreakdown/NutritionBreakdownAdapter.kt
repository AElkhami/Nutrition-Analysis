package com.elkhami.nutritionanalysis.view.nutritionsbreakdown

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkhami.nutritionanalysis.data.model.NutritionalFactsResponse
import com.elkhami.nutritionanalysis.databinding.NutritionBreakdownItemBinding

/**
 * Created by A.Elkhami on 29,September,2021
 */
class NutritionBreakdownAdapter :
    ListAdapter<NutritionalFactsResponse, NutritionBreakdownAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder private constructor(private val binding: NutritionBreakdownItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: NutritionalFactsResponse) {
            binding.nutritionalFacts = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = NutritionBreakdownItemBinding.inflate(
                    layoutInflater, parent, false
                )
                return ViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

}

class DiffCallback : DiffUtil.ItemCallback<NutritionalFactsResponse>() {

    override fun areItemsTheSame(
        oldItem: NutritionalFactsResponse,
        newItem: NutritionalFactsResponse
    ): Boolean {
        return oldItem.uri == newItem.uri
    }


    override fun areContentsTheSame(
        oldItem: NutritionalFactsResponse,
        newItem: NutritionalFactsResponse
    ): Boolean {
        return oldItem == newItem
    }

}