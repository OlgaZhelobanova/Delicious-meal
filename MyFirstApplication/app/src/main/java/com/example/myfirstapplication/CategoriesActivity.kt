package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent

class CategoriesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.categories)
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
    fun ToMyProfile (view: View)
    {
        val randomIntent = Intent(this,MyProfileActivity::class.java)
        startActivity(randomIntent);
    }
}