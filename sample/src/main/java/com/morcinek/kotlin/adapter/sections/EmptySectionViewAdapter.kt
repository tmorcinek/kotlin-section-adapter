package com.morcinek.kotlin.adapter.sections

import com.morcinek.kotlin.adapter.R

/**
 * Copyright 2017 Tomasz Morcinek. All rights reserved.
 */
class EmptySectionViewAdapter : StaticSectionViewAdapter<EmptyViewModel>() {

    override val layoutId = R.layout.budget_empty
}

class EmptyViewModel
