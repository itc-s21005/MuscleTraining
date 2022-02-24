package jp.ac.it_college.std.s20001.muscletraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private val helper = DatabaseHelper(this@RecordActivity)
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("RecordActivity", "onCreate RecordActivity")
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ツールバーの表示
        setSupportActionBar(binding.recordToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val records = selectDb()

        //フラグメントの表示
        if(savedInstanceState == null){
            if(records.size == 0){  //データベースにデータがなかったらフラグメントを表示
                val fragmentManager = supportFragmentManager
                val fragmentTransaction = fragmentManager.beginTransaction()

                fragmentTransaction.addToBackStack(null)

                fragmentTransaction.replace(R.id.dec_container,
                    MessageFragment.newInstance(),
                )
                fragmentTransaction.commit()
            } else {
                Log.d("RecordActivity", "records = ${selectDb()}")
                val adapter = RecyclerAdapter(records)
                val manager = LinearLayoutManager(this@RecordActivity)
                binding.records.let {
                    it.adapter = adapter
                    manager.orientation = LinearLayoutManager.VERTICAL
                    it.layoutManager = manager
                }
            }
        }

    }

    //ツールバーの設定
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true

        if(item.itemId == android.R.id.home){
            finish()
        }else{
            returnVal = super.onOptionsItemSelected(item)
        }

        return returnVal
    }

    //データベースからデータを取得
    private fun selectDb(): MutableList<MutableMap<String, String>>{

        val records: MutableList<MutableMap<String, String>> = mutableListOf()
        val db = helper.readableDatabase
        val sqlSelect = "SELECT menu, date(date) AS date  FROM record ORDER BY date DESC"
        val cursor = db.rawQuery(sqlSelect, null)

        while(cursor.moveToNext()) {
            cursor.let {
                val menuIndex = it.getColumnIndex("menu")
                val dateIndex = it.getColumnIndex("date")
                records.add(
                    mutableMapOf(
                        "menu" to it.getString(menuIndex),
                        "date" to it.getString(dateIndex)
                    )
                )
            }

        }

        cursor.close()

        return records

    }
}