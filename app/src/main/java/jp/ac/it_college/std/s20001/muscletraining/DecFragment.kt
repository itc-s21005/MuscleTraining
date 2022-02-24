package jp.ac.it_college.std.s20001.muscletraining

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment

class DecFragment: Fragment() {

    companion object{

        @SuppressLint("StaticFieldLeak")
        lateinit var activity: Context

        fun newInstance(menuList: ArrayList<MenuEntity>, app:Context): DecFragment{
            val fragment = DecFragment()
            val bundle = Bundle()
            bundle.putParcelableArrayList("Entity", menuList)
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
        return inflater.inflate(R.layout.fragment_dec, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = view.findViewById<ImageView>(R.id.training_image)
        val text = view.findViewById<TextView>(R.id.fragmentContentsText)

        val bundle = arguments
        val dec = bundle?.getParcelableArrayList<MenuEntity>("Entity")
        if(bundle != null){
            val resource = activity?.resources
            //画層のリソースIDを取得
            val resourceId = resource?.getIdentifier(
                dec!![0].images,
                "drawable",
                activity?.packageName
            )

            //画像をImageViewにセット
            if (resourceId != null) {
                image.setImageResource(resourceId)
            }

            //テキストを表示
            text.text = dec!![0].description
            Log.d("DecFragment", "text = ${dec[0].name}")

        }
    }
}