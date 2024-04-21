package com.alexisdev.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder

interface DelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    fun onBindViewHolder(holder: ViewHolder, items: List<Any>, position: Int)
    fun onRecycled(holder: ViewHolder)

    fun isForViewType(items: List<Any>, position: Int): Boolean

    fun itemId(item: Any): Any

    fun itemContent(item: Any): Any

    fun onAttachedToWindow(holder: ViewHolder)

    fun onDetachedFromWindow(holder: ViewHolder)
}