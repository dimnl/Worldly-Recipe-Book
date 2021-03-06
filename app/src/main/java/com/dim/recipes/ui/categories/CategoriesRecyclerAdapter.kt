package com.dim.recipes.ui.categories

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.dim.recipes.R
import com.dim.recipes.data.RecipeRepository
import com.dim.recipes.models.categories.Category
import com.dim.recipes.models.categories.CategoryList
import com.dim.recipes.ui.loadImageIntoImageView
import kotlinx.android.synthetic.main.layout_category_item.view.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesRecyclerAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: List<Category> = ArrayList()

    fun submitList(categoryList: CategoryList) {
        items = categoryList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_category_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CategoryViewHolder -> {
                holder.bind(items[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val categoryName = itemView.category_name
        private val categoryImage = itemView.category_image

        init {
            itemView.setOnClickListener { v: View ->
                val position: Int = adapterPosition
                val item = items.get(position)
                CoroutineScope(Dispatchers.Main).launch{
                    RecipeRepository.filterByCategory(item.name)
                    v.findNavController().navigate(CategoriesFragmentDirections.actionNavigationCategoriesToRecipesByCategory(item.name))
                }
            }
        }

        fun bind(category: Category) {
            loadImageIntoImageView(category.image, categoryImage, itemView.context)
            categoryName.text = category.name
        }
    }
}