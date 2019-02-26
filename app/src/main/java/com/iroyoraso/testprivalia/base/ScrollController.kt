package com.iroyoraso.testprivalia.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by iroyo on 26/2/19.
 * Mail: iroyoraso@gmail.com
 */
class ScrollController(private val listener: ScrollListener, private val layoutManager: LinearLayoutManager, private val threshold: Int = 6) :
    RecyclerView.OnScrollListener() {

    private var loading = true
    private var previousTotal = 0

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = recyclerView.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + threshold) {
            listener.onScrollToBottomAchieved()
            loading = true
        }
    }

    interface ScrollListener {

        fun onScrollToBottomAchieved()

    }
}