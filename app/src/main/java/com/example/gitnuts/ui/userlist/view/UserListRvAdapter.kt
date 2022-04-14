package com.example.gitnuts.ui.userlist.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gitnuts.databinding.UserItemCellBinding
import com.example.gitnuts.ui.userlist.model.UserItemUIModel

class UserListRvAdapter(private var dataSource: List<UserItemUIModel>, private val clickEvents: ClickEvents): RecyclerView.Adapter<UserListRvAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemCellBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, clickEvents)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(dataSource[position])
    }

    override fun getItemCount(): Int = dataSource.count()


    fun updateData(dataSource: List<UserItemUIModel>) {
        //Use diffUtil to enhance UI/UX.
        this.dataSource = dataSource
        this.notifyItemRangeChanged(0, this.dataSource.count())
    }



    class ViewHolder(private val binding: UserItemCellBinding, private val events: ClickEvents): RecyclerView.ViewHolder(binding.root) {

        fun setData(uiModel: UserItemUIModel) {
            binding.cell = uiModel
            binding.events = events
//            binding.executePendingBindings()
        }
    }

    interface ClickEvents {
        fun onContainerClicked(v: View, item: UserItemUIModel)
    }
}