package com.elkhami.nutritionanalysis.view.totalnutritionfacts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.elkhami.nutritionanalysis.data.model.Nutrient
import com.elkhami.nutritionanalysis.databinding.TotalNutritionFactsItemBinding

/**
 * Created by A.Elkhami on 02,October,2021
 */
class TotalNutritionFactsAdapter :
    ListAdapter<Nutrient, TotalNutritionFactsAdapter.ViewHolder>(DiffCallback()) {

    class ViewHolder private constructor(private val binding: TotalNutritionFactsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Nutrient) {
            binding.nutrient = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = TotalNutritionFactsItemBinding.inflate(
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

class DiffCallback : DiffUtil.ItemCallback<Nutrient>() {

    override fun areItemsTheSame(
        oldItem: Nutrient,
        newItem: Nutrient
    ): Boolean {
        return oldItem.label == newItem.label
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(
        oldItem: Nutrient,
        newItem: Nutrient
    ): Boolean {
        return oldItem == newItem
    }

}