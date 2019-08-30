package com.anangkur.jetpacksubmission1.feature.main.movie

import com.anangkur.jetpacksubmission1.data.model.Result

interface MovieView {
    fun onVerticalDataReady(data: List<Result>)
    fun onHorizontalDataReady(data: List<Result>)
}