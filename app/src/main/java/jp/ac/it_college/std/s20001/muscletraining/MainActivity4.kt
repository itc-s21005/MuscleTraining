package jp.ac.it_college.std.s20001.muscletraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain4Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val description = intent.getStringArrayListExtra("description")
        var text = ""

        Log.d("Main4", "description = $description")

        for(i in 0 until description!!.size){
            text += when(i){
                description.size - 1 -> "${i + 1}.${description[i]}"
                else -> "${i + 1}.${description[i]}\n\n"
            }

        }

        binding.contentsText.text = text
    }
    
}