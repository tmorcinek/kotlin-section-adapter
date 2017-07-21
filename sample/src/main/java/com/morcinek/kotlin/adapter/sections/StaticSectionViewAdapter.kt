package com.morcinek.kotlin.adapter.sections

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.morcinek.kotlin.adapter.SectionRecyclerViewAdapter

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
abstract class StaticSectionViewAdapter<T : Any> : SectionRecyclerViewAdapter.SectionViewAdapter<T, RecyclerView.ViewHolder> {

    protected abstract val layoutId: Int

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: T, position: Int) {}

    override fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int)
            = ViewHolder(layoutInflater.inflate(layoutId, parent, false))

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
