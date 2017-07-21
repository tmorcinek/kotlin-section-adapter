package com.morcinek.kotlin.adapter.sections

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.morcinek.kotlin.adapter.R
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter
import kotlinx.android.synthetic.main.budget_spending.view.*

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class SpendingSectionViewAdapter : SectionRecyclerViewAdapter.SectionViewAdapter<SpendingViewModel, SpendingSectionViewAdapter.ViewHolder> {

    override fun onBindViewHolder(holder: ViewHolder, item: SpendingViewModel, position: Int) {
        holder.recommendedSpending.text = item.recommendedDailySpending
        holder.actualSpending.text = item.actualDailySpending
    }

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int)
            = ViewHolder(layoutInflater.inflate(R.layout.budget_spending, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val actualSpending: TextView
            get() = itemView.actualSpending
        val recommendedSpending: TextView
            get() = itemView.recommendedSpending
    }
}

class SpendingViewModel(val recommendedDailySpending: String, val actualDailySpending: String)