package com.example.bigboyz_final_project

import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MusicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        val webView = findViewById<WebView>(R.id.musicview)
        webView.webViewClient = WebViewClient()

        // YouTube video URL
        val videoUrl = "https://www.youtube.com/watch?v=jfKfPfyJRdk"

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl(videoUrl)

        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
    }
}
