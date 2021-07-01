package com.dir.irfan_tentwenty_assignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.adapter.MoviesAdapter
import com.dir.irfan_tentwenty_assignment.adapter.SliderAdapter
import com.dir.irfan_tentwenty_assignment.model.images.Poster
import com.dir.irfan_tentwenty_assignment.model.moviesdetail.MoviesDetail
import com.dir.irfan_tentwenty_assignment.model.videotrailer.VideoTrailer
import com.dir.irfan_tentwenty_assignment.network.RequestBodies
import com.dir.irfan_tentwenty_assignment.repository.AppRepository
import com.dir.irfan_tentwenty_assignment.util.Resource
import com.dir.irfan_tentwenty_assignment.util.errorSnack
import com.dir.irfan_tentwenty_assignment.viewmodel.MoviesDetailViewModel
import com.dir.irfan_tentwenty_assignment.viewmodel.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar
import com.tbuonomo.viewpagerdotsindicator.WormDotsIndicator
import kotlinx.android.synthetic.main.layout_movies_detail.*


class MoviesDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: MoviesDetailViewModel
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var rootLayout: RelativeLayout
    lateinit var progress: ProgressBar
    lateinit var title: TextView
    lateinit var txtgenres: TextView
    lateinit var txtDates: TextView
    lateinit var txtDetail: TextView
    lateinit var moviesDetail: MoviesDetail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        supportActionBar?.hide()

        setContentView(R.layout.layout_movies_detail)
        init()
    }

    private fun init() {
        rootLayout = findViewById(R.id.rootLayout)
        progress = findViewById(R.id.progressBar)
        title = findViewById(R.id.title)
        txtgenres = findViewById(R.id.txtgenres)
        txtDates = findViewById(R.id.txtDates)
        txtDetail = findViewById(R.id.txtDetail)
        setupViewModel()
        setActions()

    }

    fun setPagers( posterArray : List<Poster>?){
        val dotsIndicator = findViewById<WormDotsIndicator>(R.id.dots_indicator)
        val viewPager = findViewById<ViewPager>(R.id.viewPage)
        with(viewPager) {
           var  slideradapter = SliderAdapter(this@MoviesDetailActivity,posterArray!!)
            viewPager!!.adapter = slideradapter
            dotsIndicator!!.setViewPager(this!!)
        }
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(MoviesDetailViewModel::class.java)

        getMovies(intent.extras!!.get("id") as Int)
        getImagesList()
    }

    fun setActions(){
        btnWatchTrailer.setOnClickListener {
            getVideoTrailerId(moviesDetail.imdbId!!)
        }
    }

    private fun getMovies(id: Int) {
        val body = RequestBodies.requestBody(
            id,
            ""
        )
        viewModel.moviesDetails(body)
        viewModel._moviesDetailResponse.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { movieDetailResponse ->
                        setData(movieDetailResponse)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        rootLayout.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    fun getVideoTrailerId(id: String){
        val body = RequestBodies.requestBody(
            0,
            id
        )
        viewModel.videoTrailers(body)
        viewModel.videoTrailer.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { videoTrailerResponse ->
                        navigateScreen(videoTrailerResponse)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        rootLayout.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    fun getImagesList(){
        val body = RequestBodies.requestBody(
            0,
            intent.extras!!.get("id").toString()
        )
        viewModel.imagesList(body)
        viewModel.imagesResponse.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { imagesResponse ->
                        setPagers(imagesResponse.posters)
                    }
                }

                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        rootLayout.errorSnack(message, Snackbar.LENGTH_LONG)
                    }

                }

                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    fun navigateScreen(videoTrailer: VideoTrailer){
        startActivity(Intent(this,YoutubePlayerActivity::class.java).putExtra("id",videoTrailer.results!!.get(0).key))
    }

    fun setData( moviesDetail: MoviesDetail){
        this.moviesDetail = moviesDetail
        title.text = moviesDetail.title
        txtgenres.text = moviesDetail.genres!!.get(0).name
        txtDates.text = moviesDetail.releaseDate
        txtDetail.text = moviesDetail.overview
    }

    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }
}