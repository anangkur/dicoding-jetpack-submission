package com.anangkur.jetpacksubmission1.feature.main.tv

import com.anangkur.jetpacksubmission1.data.model.TvParent

interface TvView {
    fun onPopularDataReady(data: List<TvParent>)
}