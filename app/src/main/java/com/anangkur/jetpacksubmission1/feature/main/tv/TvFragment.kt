package com.anangkur.jetpacksubmission1.feature.main.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.ViewModelFactory
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.feature.detail.DetailActivity
import com.anangkur.jetpacksubmission1.feature.main.MainViewModel
import com.anangkur.jetpacksubmission1.feature.main.movie.MovieItemListener
import com.anangkur.jetpacksubmission1.utils.Const
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment: Fragment(), MovieItemListener{

    private lateinit var adapterPopular: TvAdapter
    private lateinit var adapterNew: TvAdapter
    private lateinit var adapterRating: TvAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupAdapterPopular()
        setupAdapterNew()
        setupAdapterRating()
        setupViewModel()
    }

    private fun setupAdapterPopular(){
        adapterPopular = TvAdapter(this)
        recycler_tv_popular.apply {
            adapter = adapterPopular
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setupAdapterNew(){
        adapterNew = TvAdapter(this)
        recycler_tv_new.apply {
            adapter = adapterNew
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun setupAdapterRating(){
        adapterRating = TvAdapter(this)
        recycler_tv_rating.apply {
            adapter = adapterRating
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        }
    }

    private fun obtainViewModel() = ViewModelProviders.of(this, ViewModelFactory.getInstance(requireContext())).get(MainViewModel::class.java)

    private fun setupViewModel(){
        viewModel = obtainViewModel().apply {
            getTvPopular(1).observe(this@TvFragment, Observer {
                pb_tv.visibility = View.VISIBLE
                adapterPopular.setRecyclerData(it.results)
                adapterNew.setRecyclerData(it.results)
                adapterRating.setRecyclerData(it.results)
                pb_tv.visibility = View.GONE
            })
        }
    }

    override fun onClickItem(data: Result) {
        DetailActivity.startActivity(requireContext(), data, Const.TYPE_TV)
    }

    companion object{
        fun getInstance(): TvFragment = TvFragment()
    }
}