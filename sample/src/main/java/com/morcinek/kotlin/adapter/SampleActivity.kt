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
        setupBudgetData()
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
            addSectionViewAdapter(LogoSectionViewAdapter())
        }
    }

    private fun setupData(data: List<Any>) {
        adapter.setList(listOf(LoadingViewModel()))
        Handler().postDelayed({
            adapter.setList(data)
            recyclerView.startLayoutAnimation()
        }, 1500)
    }

    private fun setupEmptyData() {
        setupData(listOf(LoadingViewModel()))
    }

    private fun setupBudgetData() {
        setupData(budgetData())
    }

    fun budgetData() = listOf(
            HeaderViewModel("Monthly Budget", "$5217"),
            ProgressViewModel(R.string.budget_money_title, "Already spent 4783 out of 10000", 48, resources.getColor(R.color.budget)),
            ProgressViewModel(R.string.budget_time_title, "This is 21 out of 31 days.", 77, resources.getColor(R.color.value)),
            SpendingViewModel("$521", "$227"),
            LogoViewModel()
    )

    private fun createLayoutAnimation() = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
}
