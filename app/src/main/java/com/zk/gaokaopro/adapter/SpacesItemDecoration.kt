package com.zk.gaokaopro.adapter

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView


class SpacesItemDecoration(private var space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        outRect.left = space / 2
        outRect.right = space / 2
//        outRect.bottom = space
//        outRect.top = space

        // Add top margin only for the first item to avoid double space between items
        if (parent.getChildPosition(view) == 0)
            outRect.left = (space * 1.6).toInt()

        if (parent.getChildPosition(view) == (parent.adapter!!.itemCount - 1))
            outRect.right = (space * 1.6).toInt()

    }
}