package jp.ac.it_college.std.s20001.muscletraining

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class AllDecFragment: Fragment() {

    private var count = 0
    companion object{
        val test = arrayListOf<MenuEntity>(
            MenuEntity(
                name = "aaaaaa", description = "bbbbb", images = "llkajds"
            )
        )

        @SuppressLint("StaticFieldLeak")
        lateinit var activity: Context

        fun newInstance(menuList: ArrayList<MenuEntity>, app: Context): DecFragment{
            val fragment = DecFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("Test", menuList)
            fragment.arguments = bundle

            activity = app
            return fragment
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dec_all, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        startTraining(view)
    }

    private fun startTraining(view: View){
        val image = view.findViewById<ImageView>(R.id.all_training_image)
        val text = view.findViewById<TextView>(R.id.fragmentAllContentsText)
        val next = view.findViewById<Button>(R.id.next_button)
        val finish = view.findViewById<Button>(R.id.finish_button)



        val bundle = arguments
        val dec = bundle?.getParcelableArrayList<MenuEntity>("Test")
        if(bundle != null){
            val resource = activity?.resources
            val resourceId = resource?.getIdentifier(
                dec!![count].images,
                "drawable",
                activity?.packageName
            )

            if (resourceId != null) {
                image.setImageResource(resourceId)
            }
            text.text = dec!![count].description
            Log.d("DecFragment", "text = ${dec!![count].name}")

        }

        next.setOnClickListener {
            count++
            startTraining(view)
        }

        if(count == dec!!.size - 1){
            next.isEnabled = false
        }

        finish.setOnClickListener {  }


    }
}