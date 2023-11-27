package com.example.week1

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.week1.databinding.ItemTodoBinding
import com.example.week1.db.Todo

class ToDoAdapter(private val itemList: List<Todo>, private val todo: TodoInterface) :
    RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Todo) {
            binding.tvContent.text = item.content
            binding.ivCheck.isChecked = item.isDone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding: ItemTodoBinding =
            ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(itemList[position])
        val binding = holder.binding
        val data = itemList[position]

        binding.ivCheck.setOnCheckedChangeListener { buttonView, isChecked ->
            data.isDone = isChecked
            todo.changeIsDone(position)
        }
    }

    interface TodoInterface {
        fun changeIsDone(position: Int)
    }
}