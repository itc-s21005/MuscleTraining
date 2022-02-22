package jp.ac.it_college.std.s20001.muscletraining


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivitySelectBinding

class SelectActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySelectBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.selectToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.firstButton.setOnClickListener {
            val lv = "elementary"
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("level", lv)
            startActivity(intent)
            //finish()
        }

        binding.secondButton.setOnClickListener {
            val lv = "intermediate"
            val intent = Intent(this, MenuActivity::class.java)
            intent.putExtra("level", lv)
            startActivity(intent)
            //finish()

        }


    }

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