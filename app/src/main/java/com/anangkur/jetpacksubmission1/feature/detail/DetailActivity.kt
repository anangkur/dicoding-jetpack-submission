package com.anangkur.jetpacksubmission1.feature.detail

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.ViewModelFactory
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.Const
import com.anangkur.jetpacksubmission1.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity(), DetailActionListener {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

    private fun obtainViewModel() = ViewModelProviders.of(this, ViewModelFactory.getInstance(this)).get(DetailViewModel::class.java)

    override fun onStart() {
        super.onStart()
        setupToolbar()
        setupViewModel()
    }

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
        toolbar.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun setupViewModel(){
        viewModel = obtainViewModel()
        viewModel.result = getResultIntent(intent)
        viewModel.type = getTypeIntent(intent)
        viewModel.apply {
            getResultById(result?.id?:0).observe(this@DetailActivity, Observer {
                if (it != null){
                    setupDataToView(it)
                }else{
                    setupDataToView(result)
                }
            })
        }
    }

    private fun setupDataToView(data: Result?){
        if (data != null){
            Glide.with(this)
                .load("${BuildConfig.baseImageUrl}${data.backdrop_path}")
                .apply(RequestOptions().centerCrop())
                .apply(RequestOptions().placeholder(Utils.createCircularProgressDrawable(this)))
                .apply(RequestOptions().error(R.drawable.ic_broken_image))
                .into(iv_movie)
            tv_title.text = data.original_title?:data.original_name
            rating.rating = Utils.nomalizeRating(data.vote_average)
            tv_release_date.text = data.release_date?:"-"
            tv_language.text = data.original_language
            tv_popularity.text = data.popularity.toString()
            tv_overview.text = data.overview
            setupFavourite(data)
        }else{
            Toast.makeText(this, getString(R.string.error_null), Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getResultIntent(intent: Intent): Result?{
        return if (intent.hasExtra(Const.EXTRA_DETAIL)){
            intent.getParcelableExtra(Const.EXTRA_DETAIL)
        }else{
            null
        }
    }

    private fun getTypeIntent(intent: Intent): Int{
        return if (intent.hasExtra(Const.EXTRA_TYPE)){
            intent.getIntExtra(Const.EXTRA_TYPE, 0)
        }else{
            0
        }
    }

    private fun setupFavourite(data: Result){
        if (data.favourite == Const.FAVOURITE_TRUE){
            fab_fav.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
            fab_fav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favourite_red_24dp))
            fab_fav.setOnClickListener {this.onClickFavourite(data, Const.FAVOURITE_FALSE)}
        }else{
            fab_fav.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.white))
            fab_fav.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favourite_gray_24dp))
            fab_fav.setOnClickListener {this.onClickFavourite(data, Const.FAVOURITE_TRUE)}
        }
    }

    override fun onClickFavourite(data: Result, state: String) {
        if (state == Const.FAVOURITE_TRUE){
            Log.d("DETAIL", "add favourite")
            val newData = data.copy(favourite = Const.FAVOURITE_TRUE, type = viewModel.type)
            viewModel.bulkInsert(newData)
            setupDataToView(newData)
        }else{
            Log.d("DETAIL", "delete favourite")
            val newData = data.copy(favourite = Const.FAVOURITE_FALSE, type = viewModel.type)
            viewModel.deleteResultById(data.id)
            setupDataToView(newData)
        }
    }

    companion object{
        fun startActivity(context: Context, data: Result, type: Int){
            val intent = Intent(context, DetailActivity::class.java)
                .putExtra(Const.EXTRA_DETAIL, data)
                .putExtra(Const.EXTRA_TYPE, type)
            context.startActivity(intent)
        }

        fun startActivity(context: Activity, data: Result, type: Int, requestCode: Int){
            val intent = Intent(context, DetailActivity::class.java)
                .putExtra(Const.EXTRA_DETAIL, data)
                .putExtra(Const.EXTRA_TYPE, type)
            context.startActivityForResult(intent, requestCode)
        }
    }
}
