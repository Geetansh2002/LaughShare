package com.nextlevelprogrammers.laughshare

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.laughshare.R
import kotlin.io.path.fileVisitor

class Adapter: RecyclerView.Adapter<Viewholder>() {
    private val items:ArrayList<data> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)

        return Viewholder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val current=items[position]
        Glide.with(holder.image.context).load(current.url).into(holder.image)
        holder.button.setOnClickListener{
            val intent=Intent(Intent.ACTION_SEND)
            intent.type="text/plain"
            intent.putExtra(Intent.EXTRA_TEXT,"hey! Check this amazing meme \n${current.url}")
            holder.image.context.startActivity(intent)
        }
    }
    fun update(update:ArrayList<data>){
        items.clear()
        items.addAll(update)
        notifyDataSetChanged()
    }
}
class Viewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image:ImageView=itemView.findViewById(R.id.image)
    val bar:ProgressBar=itemView.findViewById(R.id.bar)
    val button:ImageButton=itemView.findViewById(R.id.button)
}