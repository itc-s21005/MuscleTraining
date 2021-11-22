package jp.ac.it_college.std.s20001.muscletraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding : ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}