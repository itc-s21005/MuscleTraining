package jp.ac.it_college.std.s20001.muscletraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import jp.ac.it_college.std.s20001.muscletraining.databinding.ActivityMenuDescriptionBinding

class MenuDescriptionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuDescriptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMenuDescriptionBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.descToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val description = intent.getStringArrayListExtra("description")
        val image = intent.getStringExtra("image").toString()
        var text = ""
        val entity = intent
            .getParcelableArrayListExtra<MenuEntity>("Entity") as ArrayList<MenuEntity>

        for(i in 0 until description!!.size){
            text += when(i){
                description.size - 1 -> "${i + 1}.${description[i]}"
                else -> "${i + 1}.${description[i]}\n\n"
            }

        }

        /*val resource = this@MenuDescriptionActivity.resources
        val resourceId = resource.getIdentifier(
            image,
            "drawable",
            this@MenuDescriptionActivity.packageName
        )*/

        //Log.d("Main4", "description = $description")

        if(savedInstanceState == null){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            fragmentTransaction.replace(R.id.dec_container, DecFragment.newInstance(
                entity,
                applicationContext
            ))
            fragmentTransaction.commit()
        }

        /*

        binding.contentsText.text = text
        binding.trainingImage.setImageResource(resourceId)*/
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var returnVal = true

        if(item.itemId == android.R.id.home){
            finish()
        }else {
            returnVal = super.onOptionsItemSelected(item)
        }

        return returnVal
    }

}