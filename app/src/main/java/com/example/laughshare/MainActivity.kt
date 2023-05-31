package com.example.laughshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        memecall()


    }


    private fun memecall(){
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
            val url=response.getString("url")
                val image=findViewById<ImageView>(R.id.image)
                Glide.with(this).load(url).into(image)
            },
            { error ->

            })
        queue.add(request)






    }
}




