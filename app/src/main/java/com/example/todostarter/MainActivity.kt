package com.example.todostarter

import android.content.Context
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray

class MainActivity : AppCompatActivity() {

    private lateinit var listView: ListView
    private lateinit var addButton: Button
    private lateinit var input: EditText
    private val PREFS = "todo_prefs"
    private val KEY = "todo_items"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById(R.id.todo_list)
        addButton = findViewById(R.id.add_button)
        input = findViewById(R.id.input_text)

        val items = loadItems().toMutableList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter

        addButton.setOnClickListener {
            val text = input.text.toString().trim()
            if (text.isNotEmpty()) {
                items.add(text)
                adapter.notifyDataSetChanged()
                input.text.clear()
                saveItems(items)
            } else {
                Toast.makeText(this, "Enter a task", Toast.LENGTH_SHORT).show()
            }
        }

        listView.setOnItemLongClickListener { _, _, position, _ ->
            items.removeAt(position)
            adapter.notifyDataSetChanged()
            saveItems(items)
            true
        }
    }

    private fun loadItems(): List<String> {
        val prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        val json = prefs.getString(KEY, null) ?: return listOf()
        val arr = JSONArray(json)
        val list = mutableListOf<String>()
        for (i in 0 until arr.length()) list.add(arr.getString(i))
        return list
    }

    private fun saveItems(items: List<String>) {
        val arr = JSONArray()
        items.forEach { arr.put(it) }
        val prefs = getSharedPreferences(PREFS, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY, arr.toString()).apply()
    }
}
