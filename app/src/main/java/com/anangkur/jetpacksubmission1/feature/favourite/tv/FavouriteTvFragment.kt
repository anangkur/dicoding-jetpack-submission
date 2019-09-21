package com.anangkur.jetpacksubmission1.feature.favourite.tv

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.ViewModelFactory
import com.anangkur.jetpacksubmission1.data.model.Result
import com.anangkur.jetpacksubmission1.feature.detail.DetailActivity
import com.anangkur.jetpacksubmission1.feature.favourite.FavouritePagedAdapter
import com.anangkur.jetpacksubmission1.feature.favourite.FavouriteViewModel
import com.anangkur.jetpacksubmission1.feature.main.movie.MovieItemListener
import com.anangkur.jetpacksubmission1.utils.Const
import kotlinx.android.synthetic.main.fragment_favourite_tv.*

class FavouriteTvFragment: Fragment(), MovieItemListener{

    private lateinit var viewModel: FavouriteViewModel
    private lateinit var favMovieAdapter: FavouritePagedAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourite_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupViewModel()
        setupAdapter()
    }

    private fun obtainViewModel() = ViewModelProviders.of(this, ViewModelFactory.getInstance(requireContext())).get(FavouriteViewModel::class.java)

    private fun setupViewModel(){
        viewModel = obtainViewModel()
        viewModel.apply {
            getAllDataTvPaged().observe(this@FavouriteTvFragment, Observer {
                favMovieAdapter.submitList(it)
            })
        }
    }

    private fun setupAdapter(){
        favMovieAdapter = FavouritePagedAdapter(this)
        recycler_fav.apply {
            adapter = favMovieAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun onClickItem(data: Result) {
        DetailActivity.startActivity(requireActivity(), data, Const.TYPE_TV, Const.REQUEST_CODE_FAVOURITE_TV)
    }
}