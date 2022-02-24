package jp.ac.it_college.std.s20001.muscletraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //SelectActivityに遷移
        binding.startButton.setOnClickListener {
            val intent = Intent(this, SelectActivity::class.java)
            startActivity(intent)
            //finish()
        }

        //RecordActivityに遷移
        binding.managementButton.setOnClickListener {
            val recordActivity = Intent(this, RecordActivity::class.java)
            startActivity(recordActivity)
            //finish()
        }

        //アクションバーを非表示にする処理
        val actionBar = supportActionBar
        actionBar?.hide()

    }
}