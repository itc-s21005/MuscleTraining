package jp.ac.it_college.std.s20001.muscletraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMain3Binding

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding : ActivityMain3Binding
    private var _list: MutableList<MutableMap<String, String>> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        _list = createList()

        val muscleList = findViewById<ListView>(R.id.muscleList)
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleAdapter(this@MainActivity3, _list, android.R.layout.simple_list_item_1, from, to)
        muscleList.adapter = adapter
        //muscleList.onItemClickListener = ListItemClickListener()
        muscleList.setOnItemClickListener { parent,view, position, id ->
            Toast.makeText(this, "${_list[position]}", Toast.LENGTH_LONG).show()
            //startActivity(Intent(this@MainActivity3, MainActivity2:: class.java))
        }

    }
    private fun createList(): MutableList<MutableMap<String, String>> {
        var list: MutableList<MutableMap<String, String>> = mutableListOf()

        var menu = mutableMapOf("name" to "フロントランジ", "q" to "フロントランジ")
        list.add(menu)
        menu = mutableMapOf("name" to "プッシュアップ", "q" to "プッシュアップ")
        list.add(menu)
        menu = mutableMapOf("name" to "クランチ", "q" to "クランチ")
        list.add(menu)
        menu = mutableMapOf("name" to "ヒップリフト", "q" to "ヒップリフト")
        list.add(menu)

        return list
    }

    private inner class ListItemClickListener: AdapterView.OnItemClickListener {
        override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
            if (position == 0) {
                val a1 = startActivity(Intent(this@MainActivity3, MainActivity2:: class.java))
            } else if (position == 1) {
                val a2 = startActivity(Intent(this@MainActivity3, MainActivity2:: class.java))
            } else if (position == 2) {
                val a3 = startActivity(Intent(this@MainActivity3, MainActivity2:: class.java))
            } else if (position == 3) {
                val a4 = startActivity(Intent(this@MainActivity3, MainActivity2:: class.java))
            }
        }
    }
}