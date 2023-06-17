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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.Request.*
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestListener

class MainActivity : AppCompatActivity() {
    private lateinit var mAdapter:Adapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview=findViewById<RecyclerView>(R.id.recyclerview)
        recyclerview.layoutManager= LinearLayoutManager(this)
        mAdapter=Adapter()
        recyclerview.adapter=mAdapter
        memecall()
    }
    private fun memecall() {
        val queue = Volley.newRequestQueue(this)
        val url = "https://meme-api.com/gimme/20"
        val request = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                val jsonArray=response.getJSONArray("memes")
                val memelist= ArrayList<data>()
                for (i in 0 until jsonArray.length()) {
                    val jsonObject=jsonArray.getJSONObject(i)
                    val url=data(
                        jsonObject.getString("url")
                    )
                    memelist.add(url)
                }
                mAdapter.update(memelist)
            },
            { error ->

            })
        queue.add(request)
    }
}




