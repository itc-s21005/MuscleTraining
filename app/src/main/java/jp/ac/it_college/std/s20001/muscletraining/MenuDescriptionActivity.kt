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

        //ツールバー表示
        setSupportActionBar(binding.descToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //MenuActivityからのintentを受け取る
        val entity = intent
            .getParcelableArrayListExtra<MenuEntity>("Entity") as ArrayList<MenuEntity>
        val ms = intent.getIntExtra("ms", 3)
        Log.d("MenuDec", "ms = $ms")


        if(savedInstanceState == null){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()

            when(ms){
                0 -> {
                    fragmentTransaction.replace(R.id.dec_container, AllDecFragment.newInstance(
                        entity,
                        applicationContext))
                    Log.d("AllDec", "0 = StartAllDecFragment")
                }

                1 -> {
                    fragmentTransaction.replace(R.id.dec_container, DecFragment.newInstance(
                        entity,
                        applicationContext))
                    Log.d("Dec", "1 = StartDecFragment")
                }
                else -> {
                    fragmentTransaction.replace(R.id.dec_container, DecFragment.newInstance(
                        entity,
                        applicationContext))
                    Log.d("Dec", "else = StartDecFragment")
                }
            }

            fragmentTransaction.commit()
        }

    }

    //ツールバーの設定
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