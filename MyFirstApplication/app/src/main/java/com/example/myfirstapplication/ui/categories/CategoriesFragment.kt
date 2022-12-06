package com.example.myfirstapplication

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso


data class Categories(val img: String?, val name: String?)

class CategoriesFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*        val imageView: ImageView = view.findViewById(R.id.imageView3)
        imageView.setOnClickListener{
            startActivity(Intent(view.context, RecipeActivity::class.java))
        }*/

        database = Firebase.database.reference
/*
        val categories = mutableListOf(
            Categories(null, "Первые блюда"),
            Categories(null, "Вторые блюда"),
            Categories(null, "Мясо/птица"),
            Categories(null, "Рыбные блюда"),
            Categories(null, "Закуски"),
            Categories(null, "Салаты"),
            Categories(null, "Напитки"),
            Categories(null, "Десерты"),
            Categories(null, "Заготовки"),
            Categories(null, "Выпечка"),
            Categories(null, "Торты"),
            Categories(null, "Торты")
        )

        database.child("Categories").setValue(categories)


 */
        database.child("Categories").get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                val snapshot = task.result

                val categoriesfdb = mutableListOf<Categories>()
                snapshot.children.forEach{
                    index ->
                    val name = snapshot.child(index.key!!).child("name").getValue(String::class.java)
                    val image = snapshot.child(index.key!!).child("img").getValue(String::class.java)
                    categoriesfdb.add(Categories(image, name))
                }

                recyclerView = view.findViewById(R.id.rec)
                recyclerView.layoutManager = GridLayoutManager(view.context, 2)
                recyclerView.adapter = CustomRecyclerAdapter(categoriesfdb)
            }
        }
    }
}


class CustomRecyclerAdapter(private val names: List<Categories>) : RecyclerView
.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

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
            view.context.startActivity(Intent(view.context, RecipeListActivity::class.java).apply{
                putExtra("catName", names[position].name)
            })
        }
    }

    override fun getItemCount() = names.size
}
