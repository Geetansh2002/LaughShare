package com.example.laughshare

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener

class MainActivity : AppCompatActivity() {
    var curl:String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        memecall()


        val next=findViewById<Button>(R.id.next)
        next.setOnClickListener{
            memecall()
        }
        val share=findViewById<Button>(R.id.share)
        share.setOnClickListener{
            val intent= Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"Hey check this amazing meme $curl")
            val chooser=Intent.createChooser(intent,"share using...")
            startActivity(chooser)

        }
    }
    private fun memecall(){
        val progressBar=findViewById<ProgressBar>(R.id.p)
        progressBar.visibility=View.VISIBLE
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                    curl=response.getString("url")
                val image=findViewById<ImageView>(R.id.image)
                Glide.with(this).load(curl).into(image)
                progressBar.visibility=View.GONE
            },
            { error ->
                progressBar.visibility=View.GONE
            })
        queue.add(request)
    }
}




