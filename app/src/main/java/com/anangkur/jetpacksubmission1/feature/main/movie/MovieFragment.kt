package com.anangkur.jetpacksubmission1.feature.main.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.ViewModelFactory
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.feature.detail.DetailActivity
import com.anangkur.jetpacksubmission1.feature.main.MainActivity
import com.anangkur.jetpacksubmission1.feature.main.MainViewModel
import com.anangkur.jetpacksubmission1.utils.Const
import kotlinx.android.synthetic.main.fragment_movie.*

class MovieFragment: Fragment(), MovieItemListener{

    private lateinit var viewModel: MainViewModel
    private lateinit var movieVerticalAdapter: MovieVerticalAdapter
    private lateinit var movieHorizontalAdapter: MovieHorizontalAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupHorizontalAdapter()
        setupVerticalAdapter()
        setupViewModel()
    }

    fun obtainViewModel() = ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(MainViewModel::class.java)

    private fun setupViewModel(){
        viewModel = obtainViewModel().apply {
            getMoviePopular(1).observe(this@MovieFragment, Observer {
                pb_movie.visibility = View.VISIBLE
                movieHorizontalAdapter.setRecyclerData(it.results)
                movieVerticalAdapter.setRecyclerData(it.results)
                pb_movie.visibility = View.GONE
            })
        }
    }

    private fun setupVerticalAdapter(){
        movieVerticalAdapter = MovieVerticalAdapter(this)
        recycler_vertical.apply {
            adapter = movieVerticalAdapter
            layoutManager = LinearLayoutManager(requireContext())
            itemAnimator = DefaultItemAnimator()
        }
    }

    private fun setupHorizontalAdapter(){
        movieHorizontalAdapter = MovieHorizontalAdapter(this)
        recycler_horizontal.apply {
            adapter = movieHorizontalAdapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            itemAnimator = DefaultItemAnimator()
        }
    }

    override fun onClickItem(data: Result) {
        DetailActivity.startActivity(requireContext(), data)
    }
}