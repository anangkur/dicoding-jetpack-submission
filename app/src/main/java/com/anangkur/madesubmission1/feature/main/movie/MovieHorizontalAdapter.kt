package com.anangkur.madesubmission1.feature.main.movie

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.anangkur.madesubmission1.R
import com.anangkur.madesubmission1.data.model.Result
import com.anangkur.madesubmission1.utils.Const
import com.anangkur.madesubmission1.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_main_horizontal.view.*

class MovieHorizontalAdapter(val itemListener: MovieItemListener): RecyclerView.Adapter<MovieHorizontalAdapter.ViewHolder>() {

    private val items = ArrayList<Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_horizontal, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun setRecyclerData(data: List<Result>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(data: Result){
            val newRating = Utils.nomalizeRating(data.vote_average)
            Glide.with(itemView.context)
                .load("${Const.baseImageUrl}${data.poster_path}")
                .apply(RequestOptions().centerCrop())
                .apply(RequestOptions().transforms(RoundedCorners(64)))
                .apply(RequestOptions().placeholder(Utils.createCircularProgressDrawable(itemView.context)))
                .apply(RequestOptions().error(R.drawable.ic_broken_image))
                .into(itemView.iv_item)
            itemView.tv_title.text = data.original_title
            itemView.rating.rating = newRating
            itemView.setOnClickListener { itemListener.onClickItem(data) }
        }
    }
}