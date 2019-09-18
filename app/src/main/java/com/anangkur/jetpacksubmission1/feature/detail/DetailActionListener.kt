package com.anangkur.jetpacksubmission1.feature.detail

import com.anangkur.jetpacksubmission1.data.model.Result

interface DetailActionListener {
    fun onClickFavourite(data: Result, state: String)
}