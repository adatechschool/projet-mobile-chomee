package fr.eric97278.projetcollectif

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import fr.eric97278.projetcollectif.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        getData()
    }

    private fun getData() {
        val titleView = findViewById<TextView>(R.id.first_element)
        val descriptionView = findViewById<TextView>(R.id.description)
        val attractionsView = findViewById<Spinner>(R.id.attractions)
        val dishesView = findViewById<Spinner>(R.id.dishes)
        val activitiesView = findViewById<Spinner>(R.id.activities)

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://freetestapi.com/api/v1/destinations/1"

        // Request a JSON response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val name = response.getString("name")
                val country = response.getString("country")
                val continent = response.getString("continent")
                val description = response.getString("description")

                // Set TextView content
                titleView.text = "$name; $country; $continent"
                descriptionView.text = description

                // Convert JSONArrays to Lists safely
                val attractionsList = ArrayList<String>()
                if (response.has("top_attractions")) {  // Check if key exists
                    val attractions = response.getJSONArray("top_attractions")
                    for (i in 0 until attractions.length()) {
                        attractionsList.add(attractions.getString(i))
                    }
                }

                val dishesList = ArrayList<String>()
                if (response.has("local_dishes")) {  // Check if key exists
                    val dishes = response.getJSONArray("local_dishes")
                    for (i in 0 until dishes.length()) {
                        dishesList.add(dishes.getString(i))
                    }
                }

                val activitiesList = ArrayList<String>()
                if (response.has("activities")) {  // Check if key exists
                    val activities = response.getJSONArray("activities")
                    for (i in 0 until activities.length()) {
                        activitiesList.add(activities.getString(i))
                    }
                }

                // Create Adapters for Spinners
                val attractionsAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, attractionsList)
                attractionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                attractionsView.adapter = attractionsAdapter

                val dishesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, dishesList)
                dishesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                dishesView.adapter = dishesAdapter

                val activitiesAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, activitiesList)
                activitiesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                activitiesView.adapter = activitiesAdapter
            },
            {
                titleView.text = "That didn't work!"
            })

        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
