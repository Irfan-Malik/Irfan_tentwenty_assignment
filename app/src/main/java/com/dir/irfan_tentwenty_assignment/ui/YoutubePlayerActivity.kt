package com.dir.irfan_tentwenty_assignment.ui

import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import com.dir.irfan_tentwenty_assignment.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class YoutubePlayerActivity : YouTubeBaseActivity(),YouTubePlayer.PlayerStateChangeListener{

    val api_key = "" 
    var ytPlayer : YouTubePlayerView? = null;
    var  videoId : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.youtube_player_screen)
        init()
    }

    private fun init() {
        ytPlayer = findViewById<YouTubePlayerView>(R.id.ytPlayer)
        videoId = intent.extras!!.getString("id")
        setPlayer()
    }

    fun setPlayer(){
        ytPlayer!!.initialize(api_key, object : YouTubePlayer.OnInitializedListener{

            override fun onInitializationSuccess(
                provider: YouTubePlayer.Provider?,
                player: YouTubePlayer?,
                p2: Boolean
            ) {
                player?.loadVideo(videoId)
                player?.play()
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(this@YoutubePlayerActivity , "Video player Failed" , Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onLoading() {

    }

    override fun onLoaded(p0: String?) {

    }

    override fun onAdStarted() {

    }

    override fun onVideoStarted() {

    }

    override fun onVideoEnded() {
        finish()
    }

    override fun onError(p0: YouTubePlayer.ErrorReason?) {

    }
}
