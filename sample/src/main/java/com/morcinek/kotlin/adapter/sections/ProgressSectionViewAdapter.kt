package com.morcinek.kotlin.adapter.sections

import android.graphics.PorterDuff
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import com.morcinek.kotlin.adapter.R
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter
import kotlinx.android.synthetic.main.budget_progress.view.*

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class ProgressSectionViewAdapter : SectionRecyclerViewAdapter.SectionViewAdapter<ProgressViewModel, ProgressSectionViewAdapter.ViewHolder> {

    override fun onBindViewHolder(holder: ViewHolder, item: ProgressViewModel, position: Int) {
        holder.title.setText(item.title)
        holder.text.text = item.text
        holder.progressBar.progress = item.progress
        holder.progressBar.progressDrawable.setColorFilter(item.progressTint, PorterDuff.Mode.SRC_IN)
    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int)
            = ViewHolder(layoutInflater.inflate(R.layout.budget_progress, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView
            get() = itemView.title
        val text: TextView
            get() = itemView.text
        val progressBar: ProgressBar
            get() = itemView.progressBar
    }
}

class ProgressViewModel(val title: Int, val text: String, val progress: Int, val progressTint: Int)