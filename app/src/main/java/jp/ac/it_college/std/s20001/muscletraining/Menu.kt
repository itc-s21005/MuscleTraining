package jp.ac.it_college.std.s20001.muscletraining

import org.json.JSONArray
import org.json.JSONObject
import kotlin.collections.ArrayList
import android.content.Context
import java.lang.Appendable


class Menu(level: String, app: Context) {
    private val _menuList: MutableList<MutableMap<String, String>> = mutableListOf()
    private val _descriptions: ArrayList<JSONArray> = arrayListOf()
    private val _images: ArrayList<String> =arrayListOf()
    private val menuEntityList: ArrayList<MenuEntity> = arrayListOf()

    init{
        val assetManager = app.resources.assets
        val inputStream = assetManager.open("menu.json")
        val jsonString = inputStream.bufferedReader().use { it.readText() }
        val rootJson = JSONObject(jsonString)
        val menu = rootJson.getJSONArray(level)

        for(i in 0 until menu.length()){
            val obj = menu.getJSONObject(i)

            _menuList.add(mutableMapOf(
                "name" to obj.getString("name")
            ))

            val descriptions = obj.getJSONArray("description")
            _descriptions.add(descriptions)
            var text = ""
            for(i in 0 until descriptions!!.length()){
                text += when(i){
                    descriptions.length() - 1 -> "${i + 1}.${descriptions[i]}"
                    else -> "${i + 1}.${descriptions[i]}\n\n"
                }

            }
            menuEntityList.add(
                MenuEntity(
                    name = obj.getString("name"), description = text, images = obj.getString("image")
                )
            )

            _images.add(obj.getString("image"))

        }
    }

    fun getMenuList(): MutableList<MutableMap<String, String>> {
        return this._menuList
    }

    fun getMenuEntityList(): ArrayList<MenuEntity>{
        return this.menuEntityList
    }

    fun getDescriptions(): ArrayList<JSONArray>{
        return this._descriptions
    }

    fun getImages(): ArrayList<String>{
        return this._images
    }

}