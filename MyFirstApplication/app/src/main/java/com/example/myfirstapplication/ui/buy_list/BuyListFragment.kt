package com.example.myfirstapplication.ui.buy_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myfirstapplication.Categories
import com.example.myfirstapplication.CustomRecyclerAdapter
import com.example.myfirstapplication.R
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.squareup.picasso.Picasso

data class BuyList(val name: String?, val quantity: Int?, val measure: String?)

class BuyListFragment : Fragment() {

    private lateinit var database: DatabaseReference
    private lateinit var checkBox: CheckBox
    private lateinit var sourcelist: List<String>
    private lateinit var selectedlist: List<String>
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_buy_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = Firebase.database.reference

       /* val list = mutableListOf(
            BuyList("картофель", 1, "кг"),
            BuyList("молоко", 1, "л"),
            BuyList("яйца", 10, "шт."),
            BuyList("белый хлеб", null, null),
            BuyList("помидоры", 5, "шт."),
        )

        database.child("BuyList").setValue(list)*/

        database.child("BuyList").get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                val snapshot = task.result

                val listfdb = mutableListOf<BuyList>()
                snapshot.children.forEach{
                        index ->
                    val name = snapshot.child(index.key!!).child("name").getValue(String::class.java)
                    val quantity = snapshot.child(index.key!!).child("quantity").getValue(Int::class.java)
                    val measure = snapshot.child(index.key!!).child("measure").getValue(String::class.java)
                    listfdb.add(BuyList(name, quantity, measure))
                }

                recyclerView = view.findViewById(R.id.rec2)
                recyclerView.layoutManager = GridLayoutManager(view.context, 1)
                /*recyclerView.adapter = BuyListRecyclerAdapter(listfdb)*/

            }
        }
    }
}

/*class BuyListRecyclerAdapter(private val names: List<BuyList>) : RecyclerView
.Adapter<BuyListRecyclerAdapter.MyViewHolder>() {

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
    }

    override fun getItemCount() = names.size
}*/
