package com.example.mininytimes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.mininytimes.network.ApiFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Launch this code on a background thread, handle the result on the main/UI thread.
        GlobalScope.launch(Dispatchers.Main) {
            // Request the "home" section from the NYTimes Public Top Stories API.
            // https://api.nytimes.com/svc/topstories/v2/home.json?api-key=<insert api key>
            val postRequest = ApiFactory.topStoriesApi.getSection("home", BuildConfig.API_KEY)
            try {
                // Await the response.
                val response = postRequest.await()
                // Map the section content from the response body.
                response.body()?.let { section ->
                    // Print the contents of the section in the debug logs.
                    Log.d("MainActivity", section.toString())
                }
            } catch (e: Exception) {
                // If the internet was disconnected or something else went wrong, show the user a message.
                Toast.makeText(baseContext, "Something went wrong.", Toast.LENGTH_LONG).show()
            }
        }
    }
}
