package com.morcinek.kotlin.adapter

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.kotlin.adapter.sections.*
import kotlinx.android.synthetic.main.main.*


/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class SampleActivity : AppCompatActivity() {

    private val adapter: SectionRecyclerViewAdapter
        get() = recyclerView.adapter as SectionRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        setupRecyclerView()
        setupAdapter()
        setupData()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
    }

    private fun setupAdapter() {
        recyclerView.adapter = SectionRecyclerViewAdapter().apply {
            addSectionViewAdapter(EmptySectionViewAdapter())
            addSectionViewAdapter(LoadingSectionViewAdapter())
            addSectionViewAdapter(HeaderSectionViewAdapter())
            addSectionViewAdapter(ProgressSectionViewAdapter())
            addSectionViewAdapter(SpendingSectionViewAdapter())
        }
    }

    private fun setupData() {
        adapter.setList(listOf(LoadingViewModel()))
        Handler().postDelayed({ adapter.setList(listOf(EmptyViewModel())) }, 1500)

    }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
}
