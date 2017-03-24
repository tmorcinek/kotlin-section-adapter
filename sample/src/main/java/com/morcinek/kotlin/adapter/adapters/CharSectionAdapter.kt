package com.morcinek.kotlin.adapter.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.kotlin.adapter.R
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class CharSectionAdapter : SectionRecyclerViewAdapter.SectionViewAdapter<Char, CharSectionAdapter.ViewHolder> {

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int) =
            ViewHolder(layoutInflater.inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, item: Char, position: Int) {
        holder.textView.text = "${javaClass.simpleName} has rendered char: $item"
        holder.textView.setBackgroundResource(R.color.colorAccent)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView: TextView
            get() = itemView as TextView
    }
}