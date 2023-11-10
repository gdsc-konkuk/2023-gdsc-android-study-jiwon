package com.example.week1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week1.databinding.ItemTodoBinding

class ToDoAdapter(private val itemList: ArrayList<ToDoData>): RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {


    inner class ToDoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ToDoData) {
            binding.tvContent.text = item.content
            binding.ivCheck.isSelected = item.done
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding: ItemTodoBinding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(itemList[position])
        val binding = holder.binding
        val data = itemList[position]

        binding.ivCheck.setOnClickListener {
            data.done = !data.done
        }
    }
}