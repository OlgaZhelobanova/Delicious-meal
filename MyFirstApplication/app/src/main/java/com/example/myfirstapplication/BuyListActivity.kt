package com.example.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class BuyListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.buy_list)
    }

    fun ToHomePage (view: View)
    {
        val randomIntent = Intent(this,HomePageActivity::class.java)
        startActivity(randomIntent);
    }
    fun ToIngredients (view: View)
    {
        val randomIntent = Intent(this,IngredientsActivity::class.java)
        startActivity(randomIntent);
    }
    fun ToCategories (view: View)
    {
        val randomIntent = Intent(this,CategoriesActivity::class.java)
        startActivity(randomIntent);
    }
    fun ToMyProfile (view: View)
    {
        val randomIntent = Intent(this,MyProfileActivity::class.java)
        startActivity(randomIntent);
    }

}