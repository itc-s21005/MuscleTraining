package jp.ac.it_college.std.s20001.muscletraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    private lateinit var binding: ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMain4Binding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.contentsText.text = """
        1.背筋を伸ばしてまっすぐ立つ。
        
        2.片足を1.5〜2歩分ほど前に出す。
        
        3.膝が90度になるまで、上体を下げる。
         
        4.前足のかかとで地面を蹴るようにして、元の位置に戻る。
        
        5.左右各10回X3セットが目安""".trimIndent()
    }
    
}