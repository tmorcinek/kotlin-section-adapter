package com.morcinek.kotlin.adapter

import android.os.Bundle
import android.os.Handler
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.TypedValue
import android.view.Gravity
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
        setupFab()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.layoutAnimation = LayoutAnimationController(AnimationUtils.loadAnimation(this, R.anim.abc_fade_in))
    }

    private fun setupAdapter() {
        recyclerView.adapter = SectionRecyclerViewAdapter().apply {
            addSectionViewAdapter(EmptySectionViewAdapter())
            addSectionViewAdapter(LoadingSectionViewAdapter())
            addSectionViewAdapter(HeaderSectionViewAdapter())
            addSectionViewAdapter(ProgressSectionViewAdapter())
            addSectionViewAdapter(SpendingSectionViewAdapter())
            addSectionViewAdapter(LogoSectionViewAdapter())
            addSectionViewAdapter(TextSectionViewAdapter())
        }
    }

    private fun setupFab() {
        fab.setOnClickListener { if (adapter.itemCount == 1) setupBudgetData() else setupEmptyData() }
    }

    private fun setupData(data: List<Any>) {
        adapter.setList(listOf(LoadingViewModel()))
        fab.isEnabled = false
        Handler().postDelayed({
            fab.isEnabled = true
            adapter.setList(data)
            recyclerView.startLayoutAnimation()
        }, 1000)
    }

    private fun setupEmptyData() {
        setupData(listOf(EmptyViewModel()))
    }

    private fun setupBudgetData() {
        setupData(listOf(
                TextViewModel("This is a presentation of SectionRecyclerViewAdapter.") {
                    it.gravity = Gravity.CENTER
                    it.setTextSize(TypedValue.COMPLEX_UNIT_PX, resources.getDimension(R.dimen.item_value_text_size))
                    it.setTextColor(resources.getColor(R.color.text_white_secondary))
                },
                TextViewModel("Each Item is represented by its 'ViewModel'"),
                TextViewModel("'ViewModel' is then rendered by specific 'SectionViewAdapter' registered to a specific 'ViewModel' type."),
                HeaderViewModel("Monthly Budget", "$5217"),
                ProgressViewModel(R.string.budget_money_title, "Already spent 4783 out of 10000", 48, resources.getColor(R.color.budget)),
                ProgressViewModel(R.string.budget_time_title, "This is 21 out of 31 days.", 77, resources.getColor(R.color.value)),
                TextViewModel("Daily spending") {
                    it.gravity = Gravity.CENTER
                },
                SpendingViewModel("$521", "$227"),
                LogoViewModel()
        ))
    }
}
