package jp.ac.it_college.std.s20001.muscletraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import android.widget.SimpleAdapter
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMenuBinding
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    private val _list: MutableList<MutableMap<String, String>> = mutableListOf()
    private val descriptions: ArrayList<JSONArray> = arrayListOf()
    private val images: ArrayList<String> = arrayListOf()
    private val helper = DatabaseHelper(this@MenuActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val level = intent!!.getStringExtra("level").toString()

        createMenuList(level)

        setSupportActionBar(binding.menuToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val muscleList = findViewById<ListView>(R.id.muscleList)
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleAdapter(this@MenuActivity, _list, android.R.layout.simple_list_item_1, from, to)
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
            val intent = Intent(this@MenuActivity, MenuDescriptionActivity::class.java)
            intent.putStringArrayListExtra("description", description)
            intent.putExtra("image", image)
            startActivity(intent)

        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true

        if(item.itemId == android.R.id.home){
            finish()
        } else {
            returnVal = super.onOptionsItemSelected(item)
        }

        return returnVal
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
        val df = SimpleDateFormat("yyyy-MM-dd")
        return df.format(date).toString()
    }

}