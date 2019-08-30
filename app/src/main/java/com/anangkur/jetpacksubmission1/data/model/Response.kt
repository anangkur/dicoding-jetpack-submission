package com.anangkur.jetpacksubmission1.data.model

data class Response(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)