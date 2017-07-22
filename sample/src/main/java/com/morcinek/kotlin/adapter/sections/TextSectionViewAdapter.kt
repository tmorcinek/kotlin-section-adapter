package com.morcinek.kotlin.adapter.sections

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
class TextSectionViewAdapter : SectionRecyclerViewAdapter.SectionViewAdapter<TextViewModel, TextSectionViewAdapter.ViewHolder> {

    override fun onBindViewHolder(holder: ViewHolder, item: TextViewModel, position: Int) {
        holder.text.text = item.text
        item.func(holder.text)
    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int)
            = ViewHolder(layoutInflater.inflate(R.layout.budget_text, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val text: TextView
            get() = itemView as TextView
    }
}

class TextViewModel(val text: String, inline val func: (TextView) -> Unit = {})