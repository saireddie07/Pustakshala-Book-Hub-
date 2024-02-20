package com.example.bookinfohub

import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerFavorite:RecyclerView
    lateinit var layoutmanager:RecyclerView.LayoutManager
    lateinit var recyclerAdapter: FavoriteRecyclerAdapter

    var dbBookList= listOf<Entitties>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_favorites, container, false)

        recyclerFavorite=view.findViewById(R.id.favRecyclerView)
        layoutmanager=GridLayoutManager(activity as Context,2)
        dbBookList=RetriveFavorites(activity as Context).execute().get()

        println("data base is $dbBookList")
            recyclerAdapter= FavoriteRecyclerAdapter(activity as Context,dbBookList)
            recyclerFavorite.adapter=recyclerAdapter
            recyclerFavorite.layoutManager=layoutmanager


        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    class RetriveFavorites(val context:Context):AsyncTask<Void,Void,List<Entitties>>(){
        val db=Room.databaseBuilder(context,BookDatabase::class.java,"book_db").build()
        override fun doInBackground(vararg params: Void?): List<Entitties> {
            println("books: ${db.bookDao().getAllBooks()}")
            return db.bookDao().getAllBooks()
        }

    }
}