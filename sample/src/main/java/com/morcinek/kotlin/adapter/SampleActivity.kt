package com.morcinek.kotlin.adapter

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import com.morcinek.kotlin.adapter.adapters.IntegerSectionAdapter
import com.morcinek.kotlin.adapter.adapters.CharSectionAdapter
import kotlinx.android.synthetic.main.main.*


/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

        setupRecyclerView()
        setupAdapter()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(createLayoutAnimation())
    }

    private fun setupAdapter() {
        val adapter = SectionRecyclerViewAdapter()
        adapter.addSectionViewAdapter(IntegerSectionAdapter())
        adapter.addSectionViewAdapter(CharSectionAdapter())

        adapter.setList(mutableListOf<Any>().apply {
            addAll((1..6).toList())
            addAll(('a'..'g').toList())
            addAll((11..14).toList())
        })

        recyclerView.adapter = adapter
    }

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
}
