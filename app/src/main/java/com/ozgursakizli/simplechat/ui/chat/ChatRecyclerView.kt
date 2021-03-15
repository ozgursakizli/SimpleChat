package com.ozgursakizli.simplechat.ui.chat

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.AbsListView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import com.ozgursakizli.simplechat.utilities.KeyboardUtil
import java.util.*
import kotlin.math.max

class ChatRecyclerView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : RecyclerView(context, attrs, defStyleAttr) {

    private var emptyView: View? = null
    private var isAutoHideKeyboardEnabled = true
    private var oldHeight = 0
    private var verticalAutoScrollOnResize = false

    init {
        setScrollListener()
    }

    private fun setScrollListener() {
        addOnScrollListener(object : OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    closeKeyboardOnScroll(this@ChatRecyclerView)
                }
                super.onScrollStateChanged(recyclerView, newState)
            }
        })
    }

    private fun closeKeyboardOnScroll(view: View?) {
        if (view != null && isAutoHideKeyboardEnabled) {
            KeyboardUtil.hideKeyboard(view)
        }
    }

    private val isEmpty: Unit
        get() {
            if (emptyView != null && adapter != null) {
                emptyView!!.visibility = if (adapter!!.itemCount > 0) GONE else VISIBLE
            }
        }

    override fun setAdapter(adapter: Adapter<*>?) {
        val oldAdapter = getAdapter()
        oldAdapter?.unregisterAdapterDataObserver(observer)
        super.setAdapter(adapter)
        adapter?.registerAdapterDataObserver(observer)
        observer.onChanged()
    }

    private val observer: AdapterDataObserver = object : AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            isEmpty
        }
    }

    fun setEmptyView(emptyView: View?) {
        this.emptyView = emptyView
        isEmpty
    }

    fun setSupportsChangeAnimations(value: Boolean) {
        val animator = itemAnimator
        if (animator is SimpleItemAnimator) {
            animator.supportsChangeAnimations = value
        }
    }

    private fun canScrollVertically(): Boolean {
        val offset = computeVerticalScrollOffset() + computeVerticalScrollExtent()
        val range = computeVerticalScrollRange()
        return offset < range
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (verticalAutoScrollOnResize && changed && layoutManager != null) {
            val height = bottom - top
            if (oldHeight > 0 && canScrollVertically()) {
                val delta = oldHeight - height
                scrollBy(0, delta)
            }
            oldHeight = height
        }
    }

    fun setVerticalAutoScrollOnResize(value: Boolean) {
        verticalAutoScrollOnResize = value
    }

    private val count: Int
        get() {
            val adapter = adapter
            return adapter?.itemCount ?: 0
        }

    private fun setSelectionFromTop(position: Int, top: Int) {
        val lm = layoutManager
        if (lm is LinearLayoutManager) {
            lm.scrollToPositionWithOffset(position, top)
        }
    }

    fun scrollToBottom() {
        val position = max(0, count - 1)
        setSelectionFromTop(position, 0)
    }

    fun setKeyboardAutoHide(autoHideKeyboardEnabled: Boolean) {
        isAutoHideKeyboardEnabled = autoHideKeyboardEnabled
    }

}
