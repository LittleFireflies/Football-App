package com.littlefireflies.footballclub

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_club_list.view.*

/**
 * Created by widyarso.purnomo on 31/08/2018.
 */
class RecyclerViewAdapter(private val context: Context, private val items: List<Club>, private val listener: (Club) -> Unit)
    : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_club_list, parent, false))

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(items[position], listener)
    }

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView), LayoutContainer {

        fun bindItem(item: Club, listener: (Club) -> Unit) {
            itemView.name.text = item.name
            Glide.with(itemView.context).load(item.image).into(itemView.image)
            itemView.setOnClickListener { listener(item) }
        }

    }

}