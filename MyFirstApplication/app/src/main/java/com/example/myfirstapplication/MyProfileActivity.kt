package com.example.myfirstapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MyProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.my_profile)
    }
    fun ToHomePage (view: View)
    {
        val randomIntent = Intent(this,HomePageActivity::class.java)
        startActivity(randomIntent);
    }
    fun ToBuyList (view: View)
    {
        val randomIntent = Intent(this,BuyListActivity::class.java)
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
}