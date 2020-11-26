package com.nochita.meliApp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nochita.meliApp.R
import com.nochita.meliApp.domain.SearchResult

/**
 * [RecyclerView.Adapter] that can display a [SearchResult].
 */
class ListAdapter(
    private val values: List<SearchResult>,
    private val listener : OnSearchResultClicked
) : RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.apply {
            container.tag = item
            title.text = item.title
            price.text = "$ " + item.price
            Glide.with(holder.itemView).load(item.thumbnail).into(image)

            container.setOnClickListener { listener.onClicked(container.tag as SearchResult) }
        }
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.title)
        val price: TextView = view.findViewById(R.id.price)
        val image: ImageView = view.findViewById(R.id.image)
        val container : View = view.findViewById(R.id.container)
    }

    interface OnSearchResultClicked {
        fun onClicked(searchResult: SearchResult)
    }
}