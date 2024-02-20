package com.example.bookinfohub

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.w3c.dom.Text

class DashboardRecyclerAdapter(val context: Context, val list:ArrayList<Book>):RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {
    class DashboardViewHolder(view: View):RecyclerView.ViewHolder(view){
        val bookName:TextView=view.findViewById(R.id.txtViewBookName)
        val authorName:TextView=view.findViewById(R.id.txtViewAuthorName)
        val bookPrice:TextView=view.findViewById(R.id.txtViewBookPrice)
        val bookRating:TextView=view.findViewById(R.id.txtViewRating)
        val bookImage:ImageView=view.findViewById(R.id.imgViewBookImage)
        val listener:LinearLayout=view.findViewById(R.id.bookListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single,parent,false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val item=list[position]
        holder.bookName.text=item.bookName
        holder.authorName.text=item.bookAuthor
        holder.bookPrice.text=item.bookPrice
        holder.bookRating.text=item.bookRating
        Picasso.get().load(item.imageUrl).into(holder.bookImage)
        holder.listener.setOnClickListener {
            val intent=Intent(context,DescriptionActivity::class.java)
            intent.putExtra("book_id",item.bookId)
            context.startActivity(intent)
        }

    }
}