package com.rangga.mvvmlearn

import android.content.Context
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rangga.mvvmlearn.databinding.ItemBinding

class RecyclerViewAdapter(
    val viewModel: MainViewModel,
    val arrayList: ArrayList<Blog>,
    val context: Context
) : RecyclerView.Adapter<RecyclerViewAdapter.NotesViewHolder>() {

    inner class NotesViewHolder(private val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(blog: Blog) {
            binding.title.text = blog.title
            binding.delete.setOnClickListener {
                viewModel.remove(blog)
                notifyItemRemoved(arrayList.indexOf(blog))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
//        var root = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
//        return NotesViewHolder(root)
        return NotesViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(arrayList.get(position))
    }

    override fun getItemCount(): Int {
        if (arrayList.size == 0) {
            Toast.makeText(context, "List is empty", Toast.LENGTH_LONG).show()
        } else {

        }
        return arrayList.size
    }

}