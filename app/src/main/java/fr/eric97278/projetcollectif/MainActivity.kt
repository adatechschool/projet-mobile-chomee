package fr.eric97278.projetcollectif

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
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

//        binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null)
//                .setAnchorView(R.id.fab).show()
//        }
        getData()
    }
private fun getData() {
    val titleView = findViewById<TextView>(R.id.first_element);
    val descriptionView = findViewById<TextView>(R.id.description);
    //val attractionsView = findViewById<Spinner>(R.id.attractions);
    //val dishesView = findViewById<Spinner>(R.id.dishes);
    //val activitiesView = findViewById<Spinner>(R.id.activities);


    // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://freetestapi.com/api/v1/destinations/1"

// Request a string response from the provided URL.
        val stringRequest = JsonObjectRequest(
            Request.Method.GET, url,null,
            { response ->
                val name = response.getString("name")
                val country = response.getString("country")
//                val image = response.getString("image")
                val continent = response.getString("continent")
                val description = response.getString("description")
//                val attractions = response.getJSONArray("attractions")
//                val dishes = response.getJSONArray("dishes")
//                val activities = response.getJSONArray("activities")

               // Display the first 500 characters of the response string.
                titleView.text = "$name; $country; $continent"
                descriptionView.text = description
//                attractionsView.li = "$attractions"
//                dishesView.text = "$dishes"
//                activitiesView.text = "$activities"


            },
            { titleView.text = "That didn't work!" })

// Add the request to the RequestQueue.
        queue.add(stringRequest)
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