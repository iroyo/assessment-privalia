package com.iroyoraso.testprivalia.features.base

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by iroyo on 26/2/19.
 * Mail: iroyoraso@gmail.com
 */
class ScrollController(private val listener: ScrollListener, private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = layoutManager.itemCount
        if (totalItemCount == 0) return

        if (layoutManager.findLastCompletelyVisibleItemPosition() == totalItemCount - 1) {
            listener.onScrollToBottom()
        }
    }

    interface ScrollListener {

        fun onScrollToBottom()

    }
}