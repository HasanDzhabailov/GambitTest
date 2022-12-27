package com.example.gambittest.presentation.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.gambittest.R
import com.example.gambittest.databinding.DishItemBinding
import com.example.gambittest.domain.model.Dish

class Adapter(private val context: Context, private val listener: SwipeFavoritesListener) :
	ListAdapter<Dish, Adapter.MyViewHolder>(DiffUtilProductList()) {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
		MyViewHolder(
			DishItemBinding.inflate(
				LayoutInflater.from(context), parent, false
			), listener)

	override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
		holder.bind(getItem(position))
	}

	class MyViewHolder(
		private val binding: DishItemBinding,
		private val listener: SwipeFavoritesListener,
	) :
		RecyclerView.ViewHolder(binding.root), View.OnClickListener {
		private lateinit var product: Dish

		init {
			binding.Favorites.setOnClickListener(this)
		}

		private var statusClick = false
		override fun onClick(v: View?) {
			statusClick = !statusClick
			checkClickFavorites(statusClick)
			listener.onClick(product, statusClick)
		}

		@SuppressLint("SetTextI18n")
		fun bind(item: Dish) {
			this.product = item
			var count: Int = 0
			binding.apply {
				DishesImage.load(product.image)
				DishesName.text = product.name
				DishesPrice.text = product.price.toString()

				checkClickFavorites(product.isFavorite)

				AddToCart.setOnClickListener {
					++count
					AddToCart.visibility = View.GONE
					quantity.text = count.toString()
				}
				ButtonMinus.setOnClickListener {

					--count
					if (count == 0) {
						AddToCart.visibility = View.VISIBLE
					}
					quantity.text = count.toString()
				}
				ButtonPlus.setOnClickListener {
					++count
					quantity.text = count.toString()
				}
			}
		}

		private fun checkClickFavorites(favorite: Boolean) {
			if (favorite) binding.Favorites.setImageResource(R.drawable.ic_baseline_favorite_24)
			else binding.Favorites.setImageResource(R.drawable.ic_baseline_favorite_border_24)
		}
	}

	class DiffUtilProductList : DiffUtil.ItemCallback<Dish>() {
		override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean =
			oldItem.id == newItem.id

		override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean =
			areItemsTheSame(oldItem, newItem)
	}

	interface SwipeFavoritesListener {
		fun onClick(dish: Dish, statusClick: Boolean)

	}
}