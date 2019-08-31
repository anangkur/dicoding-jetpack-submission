package com.anangkur.jetpacksubmission1.feature.detail

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.anangkur.jetpacksubmission1.BuildConfig
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.utils.Const
import com.anangkur.jetpacksubmission1.utils.Utils
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity: AppCompatActivity() {

    private lateinit var viewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
    }

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
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.result = getResultIntent(intent)
        setupDataToView(viewModel.result)
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

    companion object{
        fun startActivity(context: Context, data: Result){
            val intent = Intent(context, DetailActivity::class.java)
                .putExtra(Const.EXTRA_DETAIL, data)
            context.startActivity(intent)
        }
    }
}
