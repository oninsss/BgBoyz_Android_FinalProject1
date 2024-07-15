package com.example.bigboyz_final_project

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class WebviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)

        val webView = findViewById<WebView>(R.id.webview)
        webView.webViewClient = WebViewClient()

        // Replace with the correct YouTube embed URL
        val videoId = "Z-zNHHpXoMM"
        val embedUrl = "https://www.youtube.com/embed/$videoId"

        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

        webView.loadUrl(embedUrl)

        val btnBack = findViewById<Button>(R.id.btn_back)
        btnBack.setOnClickListener {
            finish()
        }
    }
}
