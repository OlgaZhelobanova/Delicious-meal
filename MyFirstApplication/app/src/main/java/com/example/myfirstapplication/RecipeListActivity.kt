package com.example.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

data class RecipeList(val img: String?, val name: String?)

class RecipeListActivity : AppCompatActivity() {

    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recipe_list)

        /*val text: TextView
        text = findViewById(R.id.txtv)

        text.text = intent.getStringExtra("catName")
*/
        database = Firebase.database.reference

        val recipelists = mutableListOf(
            RecipeList(null, "Яйцо пашот"),
            RecipeList(null, "Карбонара"),
            RecipeList(null, "Крабовый салат"),
            RecipeList(null, "Борщ")
        )


        database.child("Recipes").get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                val snapshot = task.result

                val recipelistfdb = mutableListOf<RecipeList>()
                snapshot.children.forEach{ index ->
                    if(snapshot.child(index.key!!).child("category").getValue(String::class.java)==
                        intent.getStringExtra("catName")) {
                        val name =
                            snapshot.child(index.key!!).child("name").getValue(String::class.java)
                        val image =
                            snapshot.child(index.key!!).child("img").getValue(String::class.java)
                        recipelistfdb.add(RecipeList(image, name))
                    }
                }

                recyclerView = findViewById(R.id.rec3)
                recyclerView.layoutManager = GridLayoutManager(this,2)
                recyclerView.adapter = CustomRecyclerAdapter2(recipelistfdb)
            }
        }

    }
}

class CustomRecyclerAdapter2(private val names: List<RecipeList>) : RecyclerView
.Adapter<CustomRecyclerAdapter2.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val largeTextView: TextView = itemView.findViewById(R.id.nameOfCat)
        val img: ImageView = itemView.findViewById(R.id.img)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.category, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.largeTextView.text = names[position].name
        Picasso.get().load( names[position].img).into(holder.img)
        holder.itemView.setOnClickListener { view ->
            view.context.startActivity(Intent(view.context, RecipeActivity::class.java).apply{
                /*putExtra("catName", names[position].name)*/
            })
        }
    }

    override fun getItemCount() = names.size
}