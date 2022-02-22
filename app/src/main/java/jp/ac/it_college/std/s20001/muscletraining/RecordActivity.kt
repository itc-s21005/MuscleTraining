package jp.ac.it_college.std.s20001.muscletraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private val helper = DatabaseHelper(this@RecordActivity)
    private lateinit var binding: ActivityRecordBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("RecordActivity", "onCreate RecordActivity")

        Log.d("RecordActivity", "records = ${selectDb()}")
        val adapter = RecyclerAdapter(selectDb())
        val manager = LinearLayoutManager(this@RecordActivity)
        binding.records.let {
            it.adapter = adapter
            manager.orientation = LinearLayoutManager.VERTICAL
            it.layoutManager = manager
        }
    }

    private fun selectDb(): MutableList<MutableMap<String, String>>{

        val records: MutableList<MutableMap<String, String>> = mutableListOf()
        val db = helper.readableDatabase
        val sqlCount = "SELECT COUNT(*) AS count FROM record"
        val sqlSelect = "SELECT *  FROM record"
        val cursor = db.rawQuery(sqlSelect, null)


        /*if(cursor.moveToFirst()){
            cursor.let {
                val count = it.getColumnIndex("count").toInt()
                Log.d("RecordActivity", "count = ${count}")
            }
            cursor.close()
        }*/

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