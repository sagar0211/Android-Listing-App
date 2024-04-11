package com.example.mylistingapp

import ListAdapter
import ListItem
import android.annotation.SuppressLint

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

class MainActivity : AppCompatActivity(), ItemClickListner {

    private lateinit var recyclerView: RecyclerView
    private lateinit var listAdapter: ListAdapter

     private lateinit var listItems: List<ListItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        listItems = loadListItems()
        listAdapter = ListAdapter(listItems, this)
        recyclerView.adapter = listAdapter
    }

    private fun loadListItems(): List<ListItem> {
        val inputStream = assets.open("data.json")
        val jsonString = BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
        val listItemsArray = JSONArray(jsonString)

        val listItems = mutableListOf<ListItem>()
        for (i in 0 until listItemsArray.length()) {
            val itemObject = listItemsArray.getJSONObject(i)
            val title = itemObject.getString("title")
            val subtitle = itemObject.getString("subtitle")
            listItems.add(ListItem(title, subtitle))
        }

        return listItems
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onClick(data: ListItem, pos: Int) {
        data.isSelected = !data.isSelected
        listItems.filter {
            it != data
        }.map {
            it.isSelected = false
        }
        listAdapter.notifyDataSetChanged()
    }
}


