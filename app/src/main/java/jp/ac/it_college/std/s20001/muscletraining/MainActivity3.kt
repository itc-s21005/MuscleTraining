package jp.ac.it_college.std.s20001.muscletraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ListView
import android.widget.SimpleAdapter
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMain3Binding
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity3 : AppCompatActivity() {
    private lateinit var binding : ActivityMain3Binding
    private val _list: MutableList<MutableMap<String, String>> = mutableListOf()
    private val descriptions: ArrayList<JSONArray> = arrayListOf()
    private val images: ArrayList<String> = arrayListOf()
    private val helper = DatabaseHelper(this@MainActivity3)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val level = intent!!.getStringExtra("level").toString()

        createMenuList(level)

        val muscleList = findViewById<ListView>(R.id.muscleList)
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleAdapter(this@MainActivity3, _list, android.R.layout.simple_list_item_1, from, to)
        muscleList.adapter = adapter
        muscleList.setOnItemClickListener { _,_, position, _ ->

            val name = _list[position]["name"].toString()
            val description = arrayListOf<String>()
            val image = images[position]
            for(i in 0 until descriptions[position].length()) {
                description.add(descriptions[position][i].toString())
            }

            insertSpl(name)

            Log.d("Main3", "name = $name")
            Log.d("Main3", "description = $description")
            Log.d("Main3", "image = $image")
            val MainActivity4 = Intent(this@MainActivity3, MainActivity4::class.java)
            MainActivity4.putStringArrayListExtra("description", description)
            MainActivity4.putExtra("image", image)
            startActivity(MainActivity4)

        }

    }


    private fun createMenuList(level: String) {
        val assetManager = resources.assets
        val inputStream = assetManager.open("menu.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }

        val rootJson = JSONObject(jsonString)
        val menu = rootJson.getJSONArray(level)

        Log.d("MainActivity3", "menuSize = ${menu.length()}")

        for(i in 0 until menu.length()){
            val obj = menu.getJSONObject(i)

            _list.add(mutableMapOf(
                "name" to obj.getString("name")
            ))

            val description = obj.getJSONArray("description")

            descriptions.add(description)
            images.add(obj.getString("image"))
            Log.d("MainActivity3", "description = ${description[0]}")

        }
    }

    private fun insertSpl(menu: String){
        Log.d("MainActivity3", "start insertSpl")
        val db = helper.writableDatabase
        val insert = """INSERT INTO record (
            menu, date
            ) VALUES (?,?)""".trimIndent()
        val stmt = db.compileStatement(insert)
        stmt.bindString(1, menu)
        stmt.bindString(2, getDate())
        stmt.executeInsert()
    }

    private fun getDate():String{
        val date = Date()
        val df = SimpleDateFormat("yyyy/MM/dd")
        return df.format(date).toString()
    }
}