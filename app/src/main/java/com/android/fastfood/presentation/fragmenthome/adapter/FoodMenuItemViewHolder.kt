package com.android.fastfood.presentation.fragmenthome.adapter

import android.view.Menu
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.android.fastfood.core.ViewHolderBinder
import com.android.fastfood.databinding.ItemGridFoodBinding
import com.android.fastfood.databinding.ItemLinearFoodBinding
import com.android.fastfood.model.FoodMenu


private fun Double.formatCurrency(currencySymbol: String): String {
    val formattedAmount = String.format("%, .0f",this).replace(",",".")
    return "$currencySymbol $formattedAmount"
}

class LinearViewHolder(
    private val binding : ItemLinearFoodBinding,
    private val onClickListener : (FoodMenu) -> Unit
) : RecyclerView.ViewHolder(binding.root), ViewHolderBinder<FoodMenu> {

    override fun bind(item: FoodMenu) {
        binding.menuImage.load(item.imgUrl){
            crossfade(true)
        }
        binding.menuName.text = item.name
        binding.menuPrice.text = item.price.formatCurrency("Rp. ")
        binding.root.setOnClickListener{
            onClickListener.invoke(item)
        }
    }
}


class GridViewHolder(
    private val binding : ItemGridFoodBinding,
    private val onClickListener : (FoodMenu) -> Unit
) : RecyclerView.ViewHolder(binding.root),ViewHolderBinder<FoodMenu>{

    override fun bind(item: FoodMenu) {
        binding.ivMenu.load(item.imgUrl){
            crossfade(true)
        }
        binding.tvMenuName.text = item.name
        binding.tvPriceMenu.text = item.price.formatCurrency("Rp. ")
        binding.root.setOnClickListener{
            onClickListener.invoke(item)
        }
    }
}