package com.example.findissues.models

data class Issues(
    val total_count: Int,
    val incomplete_results: Boolean,
    val items: List<IssuesList>
)
