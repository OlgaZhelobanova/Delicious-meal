package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.content.Intent

class StartPageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.start_page)
    }

    fun NextActivity (view: View)
    {
        val randomIntent = Intent(this,LoginActivity::class.java)
        startActivity(randomIntent);
    }
}
