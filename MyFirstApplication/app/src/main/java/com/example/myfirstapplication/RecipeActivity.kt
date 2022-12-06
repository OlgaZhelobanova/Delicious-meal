package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.core.view.View
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

data class Recipes(val img: String?, val name: String?, val kkal: String?, val protein: String?,
val carb: String?, val fat: String?, val time: String?, val portion: String?, val reyting: String?,
val ingredients: String?, val text: String?, val category: String?)

class RecipeActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe)

        database = Firebase.database.reference
        val image: ImageView = findViewById(R.id.imageView4)
        val name: TextView = findViewById(R.id.tw1)
        val kkal: TextView = findViewById(R.id.tw2)
        val protein: TextView = findViewById(R.id.tw3)
        val carb: TextView = findViewById(R.id.tw4)
        val fat: TextView = findViewById(R.id.tw5)
        val time: TextView = findViewById(R.id.twTime)
        val portion: TextView = findViewById(R.id.twPortion)
        val reyting: TextView = findViewById(R.id.twReyting)
        val ingredients: TextView = findViewById(R.id.twIngredients)
        val text: TextView = findViewById(R.id.twText)


//        val recipes = mutableListOf(Recipes(null, "Тост с вареным яйцом", "250", "5,9",
//            "24", "16", "3 мин", "1 порция", "4,6", "2 вареных яйца\n" +
//                    "2 авокадо\n" +
//                    "1/2 лайма\n" +
//                    "1/2 чайные ложки красного перца\n" +
//                    "Соль&перец\n" +
//                    "2 куска зернового хлеба", "Поджарьте 2 ломтика цельнозернового хлеба в тостере до золотистой корочки.\n" +
//                    "В небольшой миске смешайте и разомните авокадо, лайм и соль + перец по вкусу. Намажьте половину смеси на каждый ломтик поджаренного хлеба. По желанию сверху выложите жареное яйцо, яичницу-болтунью или яйцо-пашот."))

//        database.child("Recipes").setValue(recipes)

        database.child("Recipes").get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                val snapshot = task.result

                //val recipesfdb = mutableListOf<Recipes>()
                //snapshot.children.forEach{
                       // index ->
                name.text = snapshot.child("0").child("name").getValue(String::class.java)
                kkal.text = snapshot.child("0").child("kkal").getValue(String::class.java)+"\nккал"
                protein.text = snapshot.child("0").child("protein").getValue(String::class.java)+" г"+"\nбелков"
                carb.text = snapshot.child("0").child("carb").getValue(String::class.java)+" г"+"\nуглеводов"
                fat.text = snapshot.child("0").child("fat").getValue(String::class.java)+" г"+"\nжиров"
                time.text = snapshot.child("0").child("time").getValue(String::class.java)
                portion.text = snapshot.child("0").child("portion").getValue(String::class.java)
                reyting.text = snapshot.child("0").child("reyting").getValue(String::class.java)
                ingredients.text = snapshot.child("0").child("ingredients").getValue(String::class.java)
                text.text = snapshot.child("0").child("text").getValue(String::class.java)
                Picasso.get().load(snapshot.child("0").child("img").getValue(String::class.java)).into(image)
                    //categoriesfdb.add(Categories(image, name))
               // }
            }
        }
    }
}