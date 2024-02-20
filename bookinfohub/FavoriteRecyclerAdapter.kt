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

class FavoriteRecyclerAdapter(val context: Context,var bookList: List<Entitties>):RecyclerView.Adapter<FavoriteRecyclerAdapter.FavoriteViewHolder>() {
    class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val book_Name: TextView = view.findViewById(R.id.txtFavBookTitle)
        val author_Name: TextView = view.findViewById(R.id.txtFavBookAuthor)
        val book_Image: ImageView = view.findViewById(R.id.imgFavBookImage)
        val fav_Layout: LinearLayout = view.findViewById(R.id.llFavContent)
        val book_Price:TextView=view.findViewById(R.id.txtFavBookPrice)
        val book_Rating:TextView=view.findViewById(R.id.txtFavBookRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_favorite_single_row, parent, false)
        return FavoriteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        val book = bookList[position]
        holder.book_Name.text = book.bookName
        holder.author_Name.text = book.bookAuthor
        Picasso.get().load(book.bookImage).into(holder.book_Image)
        holder.book_Price.text=book.bookPrice
        holder.book_Rating.text=book.bookRating

    }
}