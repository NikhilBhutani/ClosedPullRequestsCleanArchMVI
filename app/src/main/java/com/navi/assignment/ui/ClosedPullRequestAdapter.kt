package com.navi.assignment.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.navi.assignment.R
import com.navi.assignment.databinding.RvItemPullRequestBinding
import com.navi.assignment.presentation.ClosedPullRequestViewData
import com.navi.assignment.ui.customviews.BallBeatIndicator
import kotlinx.android.synthetic.main.loader_layout.view.*

class ClosedPullRequestAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var closedPullRequestListModel = mutableListOf<ClosedPullRequestViewData>()

    private var isLoaderVisible: Boolean = false
    private val viewTypeNormal = 1
    private val viewTypeLoader = 0


    fun setData(it: MutableList<ClosedPullRequestViewData>) {
        closedPullRequestListModel.clear()
        closedPullRequestListModel.addAll(it)
        notifyDataSetChanged()
    }

    fun addData(it: List<ClosedPullRequestViewData>) {
        closedPullRequestListModel.addAll(it)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (isLoaderVisible) {
            return if (position == closedPullRequestListModel.size - 1) {
                viewTypeLoader
            } else {
                viewTypeNormal
            }
        }
        return 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var binding: RvItemPullRequestBinding? = null
        when (viewType) {

            viewTypeNormal -> {
                val inflater = LayoutInflater.from(parent.context)
                binding = RvItemPullRequestBinding
                    .inflate(inflater, parent, false)
                return ListViewHolder(binding)
            }

            viewTypeLoader -> {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.loader_layout, parent, false)
                return FooterHolder(itemView)
            }
        }

        return ListViewHolder(binding!!)
    }

    override fun getItemCount(): Int {
        return closedPullRequestListModel.size
    }

    override fun onBindViewHolder(parentHolder: RecyclerView.ViewHolder, position: Int) {
        when (parentHolder.itemViewType) {

            viewTypeNormal -> {
                val holder = parentHolder as ListViewHolder
                holder.bind(closedPullRequestListModel[position])
            }

            viewTypeLoader -> {
                val holder = parentHolder as FooterHolder
                holder.bind()
            }
        }
    }

    inner class ListViewHolder(val binding: RvItemPullRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pullRequestListModel: ClosedPullRequestViewData) {
            binding.apply {
                requestListModel = pullRequestListModel
                executePendingBindings()
            }
        }
    }

    inner class FooterHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind() {
            with(itemView) {
                indicator.indicator = BallBeatIndicator()
                indicator.show()
            }
        }
    }

    fun addLoader() {
        isLoaderVisible = true
        closedPullRequestListModel.add(ClosedPullRequestViewData("", "", "", "", ""))
        notifyItemInserted(closedPullRequestListModel.size - 1)

    }

    private fun getItem(position: Int): ClosedPullRequestViewData {
        return closedPullRequestListModel[position]
    }

    fun removeLoader() {
        isLoaderVisible = false

        if (closedPullRequestListModel.size > 0) {
            val position = closedPullRequestListModel.size - 1
            val mStore = getItem(position)
            mStore.let {
                closedPullRequestListModel.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }
}