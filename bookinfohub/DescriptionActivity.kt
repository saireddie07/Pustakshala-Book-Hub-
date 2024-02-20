package com.example.bookinfohub


import android.content.Context
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.room.Room
import com.android.volley.Response
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import org.json.JSONObject
import org.w3c.dom.Text

class DescriptionActivity : AppCompatActivity() {
    lateinit var toolBar: Toolbar
    lateinit var progressLayout:RelativeLayout
    lateinit var bookImage: ImageView
    lateinit var bookName: TextView
    lateinit var authorName: TextView
    lateinit var bookPrice: TextView
    lateinit var bookRating: TextView
    lateinit var bookDesc: TextView
    lateinit var favButton: Button
    var bookId: String? = "10"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        toolBar = findViewById(R.id.DescToolBar)
        bookImage = findViewById(R.id.imgViewDescBookImage)
        bookName = findViewById(R.id.txtViewDescBookName)
        authorName = findViewById(R.id.txtViewDescAuthorName)
        bookPrice = findViewById(R.id.txtViewDescBookPrice)
        bookRating = findViewById(R.id.txtViewDescRating)
        bookDesc = findViewById(R.id.bookDescription)
        favButton = findViewById(R.id.btnAddToFav)
        progressLayout=findViewById(R.id.descProgressLayout)

        setSupportActionBar(toolBar)
        supportActionBar?.title = "Book Details"

        progressLayout.visibility=View.VISIBLE

        if (intent != null) {
            bookId = intent.getStringExtra("book_id")
        } else {
            finish()
        }
        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url = "http://13.235.250.119/v1/book/get_book/"
        val jsonParams = JSONObject()
        jsonParams.put("book_id", bookId)
        val jsonRequest = object : JsonObjectRequest(
            Request.Method.POST,
            url,
            jsonParams,
            Response.Listener {
                progressLayout.visibility=View.GONE


                    val bookJsonObject = it.getJSONObject("book_data")
                    Picasso.get().load(bookJsonObject.getString("image")).into(bookImage)
                    bookName.text = bookJsonObject.getString("name")
                    authorName.text = bookJsonObject.getString("author")
                    bookPrice.text = bookJsonObject.getString("price")
                    bookRating.text = bookJsonObject.getString("rating")
                    bookDesc.text = bookJsonObject.getString("description")

                val bookEntity=Entitties(
                    bookId?.toInt() as Int,
                    bookName.text.toString(),
                    bookJsonObject.getString("image"),
                    authorName.text.toString(),
                    bookPrice.text.toString(),
                    bookRating.text.toString()
                )

                val checkFav=DBAsyncTask(applicationContext,bookEntity,1).execute()
                val isFav=checkFav.get()
                if(isFav){
                    favButton.text="REMOVE FROM FAVORITES"
                    val favColor=ContextCompat.getColor(applicationContext,R.color.red)
                    favButton.setBackgroundColor(favColor)
                }
                else{
                    favButton.text="ADD TO FAVORITES"
                }
                favButton.setOnClickListener {
                    if(!DBAsyncTask(applicationContext,bookEntity,1).execute().get()){
                        val async=DBAsyncTask(applicationContext,bookEntity,2).execute()
                        val result=async.get()
                        if(result){
                            Toast.makeText(this@DescriptionActivity,"Added to Favorites!",Toast.LENGTH_SHORT).show()
                            favButton.text="REMOVE FROM FAVORITES"
                            val favColor=ContextCompat.getColor(applicationContext,R.color.red)
                            favButton.setBackgroundColor(favColor)
                        }
                    }
                    else{
                        val async=DBAsyncTask(applicationContext,bookEntity,3).execute()
                        val result=async.get()
                        if(result){
                            Toast.makeText(this@DescriptionActivity,"Removed from Favorites!",Toast.LENGTH_SHORT).show()
                            favButton.text="ADD TO FAVORIES"
                            val favColor=ContextCompat.getColor(applicationContext,R.color.black)
                            favButton.setBackgroundColor(favColor)
                        }
                    }
                }

            },
            Response.ErrorListener { }) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["content-type"] = "application/json"
                headers["token"] = "23fc00ae073728"
                return headers
            }
        }
        queue.add(jsonRequest)
    }
    class DBAsyncTask(val context: Context,val bookEntity:Entitties,val mode:Int): AsyncTask<Void,Void,Boolean>(){
        val db= Room.databaseBuilder(context,BookDatabase::class.java,"book_db").build()

        override fun doInBackground(vararg params: Void?): Boolean {
            when(mode){
                1->{
                    val book:Entitties?=db.bookDao().getBookById(bookEntity.book_id.toString())
                    db.close()
                    println("db is $book")
                    println("check ${db.bookDao().getAllBooks()}")
                    return book!=null
                }
                2->{
                    db.bookDao().insertBook(bookEntity)
                    db.close()
                    return true
                }
                3->{
                    db.bookDao().deleteBook(bookEntity)
                    db.close()
                    return true
                }

            }
            return false

        }

    }
}