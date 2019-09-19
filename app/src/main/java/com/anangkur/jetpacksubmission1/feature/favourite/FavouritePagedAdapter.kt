package com.anangkur.jetpacksubmission1.feature.favourite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.feature.main.movie.MovieItemListener
import com.anangkur.jetpacksubmission1.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_main_horizontal_landscape.view.*

class FavouritePagedAdapter(private val mainItemListener: MovieItemListener): PagedListAdapter<Result, FavouritePagedAdapter.ViewHolder>(ResultDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_main_horizontal_landscape, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(data: Result){
            itemView.tv_title.text = data.original_name?:data.original_title
            Glide.with(itemView.context)
                .load("${BuildConfig.baseImageUrl}${data.backdrop_path?:data.poster_path}")
                .apply(RequestOptions().centerCrop())
                .apply(RequestOptions().transform(RoundedCorners(48)))
                .apply(RequestOptions().placeholder(Utils.createCircularProgressDrawable(itemView.context)))
                .apply(RequestOptions().error(R.drawable.ic_broken_image))
                .into(itemView.iv_item)
            itemView.rating.rating = Utils.nomalizeRating(data.vote_average)
            itemView.setOnClickListener { mainItemListener.onClickItem(data) }
        }
    }

    companion object {
        val ResultDiffCallback = object : DiffUtil.ItemCallback<Result>() {
            override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
                return oldItem == newItem
            }
        }
    }

}