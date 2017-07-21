package com.morcinek.kotlin.adapter.sections

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.kotlin.adapter.R
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter
import kotlinx.android.synthetic.main.budget_header.view.*

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class HeaderSectionViewAdapter : SectionRecyclerViewAdapter.SectionViewAdapter<HeaderViewModel, HeaderSectionViewAdapter.ViewHolder> {

    override fun onBindViewHolder(holder: ViewHolder, item: HeaderViewModel, position: Int) {
        holder.title.text = item.title
        holder.value.text = item.value
    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int)
            = ViewHolder(layoutInflater.inflate(R.layout.budget_header, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
            get() = itemView.title
        val value: TextView
            get() = itemView.value
    }
}

class HeaderViewModel(val title: String, val value: String)