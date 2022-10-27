package com.example.myfirstapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.gson.Gson
import android.widget.TextView

/*import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.encodeToString
import kotlinx.serialization.decodeFromString*/


/*@Serializable*/
data class Ingredient(val Name: ArrayList<String>)


class IngredientsActivity : AppCompatActivity() {

    lateinit var TextViewForIngr: TextView
    @SuppressLint("MissingInflatedId")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ingredients)

        val arrayAdapter: ArrayAdapter<*>
        val jsonString = """{"Name": ["Курица", "Яйцо", "Огурец", "Молоко", "Сыр", "Макароны", "Картофель"
            |, "Морковь", "Чеснок"  ]}""".trimMargin()

        val gson = Gson()
        val ingredient: Ingredient? = gson.fromJson(jsonString, Ingredient::class.java)

        var mListView = findViewById<ListView>(R.id.ingrslist)
        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, ingredient!!.Name)
        mListView.adapter = arrayAdapter

/*        val dataList = listOf(Ingredient("str")*//*, Ingredient("test")*//*)
        val jsonList = Json.encodeToString(dataList)*/

        //TextViewForIngr = findViewById(Ingr)

/*        val obj = Json.decodeFromString<Ingredient>(jsonList)*/
        //TextViewForIngr.text = ingredient.Name

    }
}