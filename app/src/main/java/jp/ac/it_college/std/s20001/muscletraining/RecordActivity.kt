package jp.ac.it_college.std.s20001.muscletraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityRecordBinding

class RecordActivity : AppCompatActivity() {
    private val helper = DatabaseHelper(this@RecordActivity)
    private lateinit var binding: ActivityRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("RecordActivity", "onCreate RecordActivity")
    }
}