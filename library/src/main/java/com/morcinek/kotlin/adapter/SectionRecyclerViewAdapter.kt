package com.morcinek.kotlin.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlin.reflect.KClass

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class SectionRecyclerViewAdapter : AbstractRecyclerViewAdapter<Any, RecyclerView.ViewHolder>() {

    interface OnSectionItemClickListener {

        fun onSectionItemClicked(itemView: View, view: View, item: Any, position: Int)
    }

    interface SectionViewAdapter<in T : Any, H : RecyclerView.ViewHolder> {

        fun onCreateViewHolder(layoutInflater: LayoutInflater, parent: ViewGroup, viewType: Int): H

        fun onBindViewHolder(holder: H, item: T, position: Int)

        fun clickableViews(holder: H): List<View> = listOf()
    }

    private val sectionViewAdapters = mutableMapOf<Int, SectionViewAdapter<Any, RecyclerView.ViewHolder>>()

    var onSectionItemClickListener: OnSectionItemClickListener? = null

    fun <T : Any> addSectionViewAdapter(type: Class<T>, sectionViewAdapter: SectionViewAdapter<T, out RecyclerView.ViewHolder>) =
            sectionViewAdapters.put(getItemViewTypeForClass(type), sectionViewAdapter as SectionViewAdapter<Any, RecyclerView.ViewHolder>)

    fun <T : Any> addSectionViewAdapter(type: KClass<T>, sectionViewAdapter: SectionViewAdapter<T, out RecyclerView.ViewHolder>) =
            addSectionViewAdapter(type.java, sectionViewAdapter)

    inline fun <reified T : Any> addSectionViewAdapter(sectionViewAdapter: SectionViewAdapter<T, out RecyclerView.ViewHolder>) =
            addSectionViewAdapter(T::class, sectionViewAdapter)

    override fun getItemViewType(position: Int) = getItemViewTypeForClass(getItem(position).javaClass)

    fun isItemOfType(position: Int, type: Class<Any>): Boolean {
        return getItemViewType(position) == getItemViewTypeForClass(type)
    }

    private fun getItemViewTypeForClass(type: Class<*>) = type.hashCode()

    private fun getSectionViewAdapter(viewType: Int) = sectionViewAdapters[viewType]!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return getSectionViewAdapter(viewType).onCreateViewHolder(LayoutInflater.from(parent.context), parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val sectionViewAdapter = getSectionViewAdapter(getItemViewType(position))
        val item = getItem(position)
        sectionViewAdapter.onBindViewHolder(holder, item, position)
        initializeOnClickListener(sectionViewAdapter.clickableViews(holder), holder.itemView, item, position)
    }

    private fun initializeOnClickListener(views: List<View>, itemView: View, item: Any, position: Int) {
        for (view in views) {
            if (onSectionItemClickListener != null) {
                initializeOnSectionItemClickListener(itemView, view, item, position)
            } else {
                initializeOnClickListener(view, item, position)
            }
        }
    }

    private fun initializeOnSectionItemClickListener(itemView: View, view: View, item: Any, position: Int) {
        view.setOnClickListener { onSectionItemClickListener!!.onSectionItemClicked(itemView, view, item, position) }
    }
}
