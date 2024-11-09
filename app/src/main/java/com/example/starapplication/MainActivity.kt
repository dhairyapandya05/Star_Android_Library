package com.example.starapplication

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.ratinglibrary.RatingView

class MainActivity : AppCompatActivity() {

    private lateinit var ratingView: RatingView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ratingView=findViewById(R.id.ratingv)
    }
}