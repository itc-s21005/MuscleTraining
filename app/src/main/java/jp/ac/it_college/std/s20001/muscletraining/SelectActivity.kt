package jp.ac.it_college.std.s20001.muscletraining


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ツールバーの表示
        setSupportActionBar(binding.selectToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //MenuActivityで初級のメニューが表示される
        binding.firstButton.setOnClickListener {
            val lv = "elementary"
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("level", lv)
            startActivity(intent)
        }

        //MenuActivityで中級のメニューが表示される
        binding.secondButton.setOnClickListener {
            val lv = "intermediate"
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("level", lv)
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
}