package com.anangkur.jetpacksubmission1.feature.main

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.anangkur.jetpacksubmission1.R
import com.anangkur.jetpacksubmission1.data.ViewModelFactory
import com.anangkur.jetpacksubmission1.feature.custom.ImageSliderFragment
import com.anangkur.jetpacksubmission1.feature.custom.SliderTabAdapter
import com.anangkur.jetpacksubmission1.feature.custom.TabAdapter
import com.anangkur.jetpacksubmission1.feature.main.movie.MovieFragment
import com.anangkur.jetpacksubmission1.feature.main.tv.TvFragment
import com.anangkur.jetpacksubmission1.utils.Const
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.toolbar

class MainActivity: AppCompatActivity(){

    private lateinit var tabAdapter: TabAdapter
    private lateinit var viewModel: MainViewModel

    private lateinit var pagerAdapter: SliderTabAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupToolbar()
        setupViewPagerSlider()

        setupViewModel()

        setupTabAdapter()
        setupViewPager()
        setupCustomTab()
        setupSelectedCustomTab(0)
    }

    fun obtainViewModel() = ViewModelProviders.of(this, ViewModelFactory.getInstance()).get(MainViewModel::class.java)

    private fun setupToolbar(){
        setSupportActionBar(toolbar)
        supportActionBar?.title = ""
    }

    private fun setupViewModel(){
        viewModel = obtainViewModel().apply {
            pb_slider.visibility = View.VISIBLE
            getMoviePopular(1).observe(this@MainActivity, Observer {
                val data = it.results
                for (item in data){
                    pagerAdapter.addFragment(ImageSliderFragment.getInstance(item))
                }
                setupSliderPage(pagerAdapter)
                pb_slider.visibility = View.GONE
            })
        }
    }

    private fun disableClickTablayout(tabLayout: TabLayout){
        for (i in 0 until tabLayout.tabCount){
            (tabLayout.getChildAt(0) as ViewGroup).getChildAt(i).isEnabled = false
        }
    }

    private fun setupTabAdapter(){
        tabAdapter = TabAdapter(supportFragmentManager)
        val resourceMovieActive =  ContextCompat.getDrawable(this,R.drawable.ic_movie_active) as Drawable
        val resourceMovieInActive =  ContextCompat.getDrawable(this,R.drawable.ic_movie_inactive) as Drawable
        val resourceTvActive =  ContextCompat.getDrawable(this,R.drawable.ic_tv_active) as Drawable
        val resourceTvInActive =  ContextCompat.getDrawable(this,R.drawable.ic_tv_inactive) as Drawable
        tabAdapter.addFragment(MovieFragment(), getString(R.string.tab_movie), resourceMovieActive, resourceMovieInActive)
        tabAdapter.addFragment(TvFragment.getInstance(), getString(R.string.tab_tv), resourceTvActive, resourceTvInActive)
    }

    private fun setupViewPager(){
        vp_main.adapter = tabAdapter
        tab_main.setupWithViewPager(vp_main)
        vp_main.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
                // do nothing
            }
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                // do nothing
            }
            override fun onPageSelected(position: Int) {
                setupCustomTab()
                setupSelectedCustomTab(position)
            }
        })
    }

    private fun setupCustomTab(){
        for (i in 0 until tab_main.tabCount) {
            val tab = tab_main.getTabAt(i)
            tab?.customView = null
            tab?.customView = tabAdapter.getTabView(i, this)
        }
    }

    private fun setupSelectedCustomTab(position: Int){
        val tab = tab_main.getTabAt(position)
        tab?.customView = null
        tab?.customView = tabAdapter.getSelectedTabView(position, this)
    }

    private fun setupViewPagerSlider(){
        pagerAdapter = SliderTabAdapter(supportFragmentManager)
    }

    private fun setupSliderPage(pagerAdapter: SliderTabAdapter){
        vp_slider.adapter = pagerAdapter
        tab_slider.setupWithViewPager(vp_slider, true)
        disableClickTablayout(tab_slider)
    }
}
