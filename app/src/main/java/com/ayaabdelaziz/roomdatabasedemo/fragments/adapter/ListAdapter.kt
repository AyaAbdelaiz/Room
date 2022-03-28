package com.ayaabdelaziz.roomdatabasedemo.fragments.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ayaabdelaziz.roomdatabasedemo.R
import com.ayaabdelaziz.roomdatabasedemo.fragments.list.ListFragment
import com.ayaabdelaziz.roomdatabasedemo.fragments.list.ListFragmentDirections
import com.ayaabdelaziz.roomdatabasedemo.model.User
import kotlinx.android.synthetic.main.list_item.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListViewHolder>() {

    private var list = emptyList<User>()

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.itemView.userFirstName.text = list[position].firstName
        holder.itemView.userSecondName.text = list[position].secondName
        holder.itemView.userAge.text = list[position].age.toString()
        holder.itemView.userId.text = list[position].id.toString()
        holder.itemView.row.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(list[position])
            holder.itemView.findNavController().navigate(action)
        }
    }
    override fun getItemCount(): Int {
        return list.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<User>) {
        this.list = list
        notifyDataSetChanged()
    }
}