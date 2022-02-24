package jp.ac.it_college.std.s20001.muscletraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.ListView
import android.widget.SimpleAdapter
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMenuBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding


    private lateinit var _list: MutableList<MutableMap<String, String>>
    private lateinit var menuEntityList: ArrayList<MenuEntity>
    private val helper = DatabaseHelper(this@MenuActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SelectActivityからのintent
        val level = intent!!.getStringExtra("level").toString()

        //Menuクラスからデータを受け取る
        val menu = Menu(level, applicationContext)
        _list = menu.getMenuList()
        menuEntityList = menu.getMenuEntityList()

        //ツールバーの設定
        setSupportActionBar(binding.menuToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //リストビューの設定・表示
        val muscleList = findViewById<ListView>(R.id.muscleList)
        val from = arrayOf("name")
        val to = intArrayOf(android.R.id.text1)
        val adapter = SimpleAdapter(this@MenuActivity, _list, android.R.layout.simple_list_item_1, from, to)
        muscleList.adapter = adapter
        muscleList.setOnItemClickListener { _,_, position, _ ->

            val name = _list[position]["name"].toString()
            val entity = arrayListOf<MenuEntity>(menuEntityList[position])

            //押された項目をデータベースに記録
            insertSpl(name)
            Log.d("Main3", "name = $name")


            //MenuDescriptionActivityにデータを送る
            val intent = Intent(this@MenuActivity, MenuDescriptionActivity::class.java)
            intent.putParcelableArrayListExtra("Entity", entity)
            startActivity(intent)

        }

    }

    //ツールバーの設定
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true

        if(item.itemId == android.R.id.home){
            finish()
        } else {
            returnVal = super.onOptionsItemSelected(item)
        }

        return returnVal
    }

    //データベースに追加する処理
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

    //今日の日付を取得
    private fun getDate():String{
        val date = Date()
        val df = SimpleDateFormat("yyyy-MM-dd")
        return df.format(date).toString()
    }

}