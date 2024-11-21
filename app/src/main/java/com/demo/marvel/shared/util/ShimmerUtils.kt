package com.demo.marvel.shared.util

import android.view.View
import com.facebook.shimmer.ShimmerFrameLayout

fun ShimmerFrameLayout.enableShimmer() {
    this.startShimmer()
    this.visibility = View.VISIBLE
}

fun ShimmerFrameLayout.disableShimmer() {
    this.stopShimmer()
    this.visibility = View.GONE
}