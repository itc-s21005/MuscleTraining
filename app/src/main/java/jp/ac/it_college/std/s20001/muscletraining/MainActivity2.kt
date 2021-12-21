package jp.ac.it_college.std.s20001.muscletraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.firstButton.setOnClickListener {
            val lv = "elementary"
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("level", lv)
            startActivity(intent)
            finish()
        }

        binding.secondButton.setOnClickListener {
            val lv = "intermediate"
            val intent = Intent(this, MainActivity3::class.java)
            intent.putExtra("level", lv)
            startActivity(intent)
            finish()

        }
    }

}