package com.rangga.mvvmlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rangga.mvvmlearn.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var viewManager = LinearLayoutManager(this)
    private lateinit var viewModel: MainViewModel
    private lateinit var mainrecycler: RecyclerView
    private lateinit var but: Button
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val application = requireNotNull(this).application
        val factory = MainViewModelFactory()
        viewModel = ViewModelProvider(this, factory).get(MainViewModel::class.java)
        but = binding.button
        mainrecycler = binding.recycler
        but.setOnClickListener {
            addData()
        }
        initialiseAdapater()
    }

    private fun initialiseAdapater() {
        mainrecycler.layoutManager = viewManager
        observeData()
    }

    private fun observeData() {
        viewModel.lst.observe(this, Observer {
            mainrecycler.adapter = RecyclerViewAdapter(viewModel, it, this)
        })
    }

    private fun addData() {
        var txtplace = binding.titletxt
        var title = txtplace.text.toString()
        if (title.isNullOrBlank()) {
            Toast.makeText(this, "ENTER TEXT", Toast.LENGTH_LONG).show()
        } else {
            var blog = Blog(title)
            viewModel.add(blog)
            txtplace.text.clear()
            mainrecycler.adapter?.notifyDataSetChanged()
        }
    }
}