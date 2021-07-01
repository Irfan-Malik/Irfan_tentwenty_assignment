package com.dir.irfan_tentwenty_assignment.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dir.irfan_tentwenty_assignment.R
import com.dir.irfan_tentwenty_assignment.adapter.MoviesAdapter
import com.dir.irfan_tentwenty_assignment.repository.AppRepository
import com.dir.irfan_tentwenty_assignment.util.Resource
import com.dir.irfan_tentwenty_assignment.util.errorSnack
import com.dir.irfan_tentwenty_assignment.viewmodel.MoviesViewModel
import com.dir.irfan_tentwenty_assignment.viewmodel.ViewModelProviderFactory
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MoviesViewModel
    lateinit var moviesAdapter: MoviesAdapter
    lateinit var recyclerView: RecyclerView
    lateinit var rootLayout: ConstraintLayout
    lateinit var progress: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        recyclerView = findViewById(R.id.recyclerView)
        rootLayout = findViewById(R.id.rootLayout)
        progress = findViewById(R.id.progressBar)
        recyclerView.layoutManager = LinearLayoutManager(this)
        setupViewModel()
    }

    private fun setupViewModel() {
        val repository = AppRepository()
        val factory = ViewModelProviderFactory(application, repository)
        viewModel = ViewModelProvider(this, factory).get(MoviesViewModel::class.java)
        getMovies()
    }

    private fun getMovies() {
        viewModel.moviesData.observe(this, Observer { response ->
            when (response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { picsResponse ->
                        val arrayMovies  = picsResponse.results
                        Log.e("getMovies", "arrayMoviesSize = " + arrayMovies!!.size)
                        moviesAdapter = MoviesAdapter(this,arrayMovies)
                        recyclerView.adapter = moviesAdapter
                        recyclerView.visibility = View.VISIBLE
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

    private fun hideProgressBar() {
        progress.visibility = View.GONE
    }

    private fun showProgressBar() {
        progress.visibility = View.VISIBLE
    }

}