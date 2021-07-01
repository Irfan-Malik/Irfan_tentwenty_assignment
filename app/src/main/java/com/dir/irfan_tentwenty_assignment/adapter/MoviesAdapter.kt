package com.dir.irfan_tentwenty_assignment.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.model.data.Result
import com.dir.irfan_tentwenty_assignment.ui.BookTicketsActivity
import com.dir.irfan_tentwenty_assignment.ui.MoviesDetailActivity
import com.dir.irfan_tentwenty_assignment.util.Constants
import kotlinx.android.synthetic.main.item_movies.view.*

class MoviesAdapter(var context: Context,var moviesArray : List<com.dir.irfan_tentwenty_assignment.model.Result> ) : RecyclerView.Adapter<MoviesAdapter.PicsViewHolder>() {

    inner class PicsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PicsViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_movies,
            parent,
            false
        )
    )
    override fun getItemCount() =  moviesArray!!.size

    override fun onBindViewHolder(holder: PicsViewHolder, position: Int) {
        val movieItem = moviesArray!!.get(position)
        holder.itemView.apply {

            title.text = movieItem.title
            tvDate.text  = movieItem.releaseDate
            var boolean : Boolean? = movieItem.adult
            if(boolean!!){
                tvAdult.text = "Adult"
            }
            else{
                tvAdult.text = "Non Adult"
            }
           var  imgUrl : String = Constants.BASE_URL+ movieItem.posterPath
            Log.e("imgURl ","ImageUrl = " + imgUrl)

            Glide.with(context)
                .load(imgUrl)
                .placeholder(R.drawable.img_poster)
                .into(imgPoster);
        }
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context,MoviesDetailActivity::class.java).putExtra("id",moviesArray.get(position).id))
        }
        holder.itemView.btnBookNow.setOnClickListener {
            context.startActivity(Intent(context,BookTicketsActivity::class.java))
        }

    }
}