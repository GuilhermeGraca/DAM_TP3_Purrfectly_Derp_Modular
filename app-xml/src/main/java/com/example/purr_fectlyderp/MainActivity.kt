package com.example.purr_fectlyderp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.slider.Slider
import com.example.purr_fectlyderp.model.UnsplashImage
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.purr_fectlyderp.adapter.UnsplashAdapter
import com.example.purr_fectlyderp.viewmodel.MainViewModel
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels {
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)
    }
    private lateinit var adapter: UnsplashAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupToolbar()
        setupRecyclerView()
        setupObservers()
        setupListeners()
    }

    private fun setupToolbar() {
        val btnNavFavorites: ImageView = findViewById(R.id.btnNavFavorites)
        btnNavFavorites.setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
    }

    private fun setupRecyclerView() {
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = UnsplashAdapter(emptyList()) { image ->
            showDerpOMeter(image)
        }
        recyclerView.adapter = adapter
    }

    private fun showDerpOMeter(image: UnsplashImage) {
        val dialog = BottomSheetDialog(this)
        val view = layoutInflater.inflate(R.layout.dialog_derp_o_meter, null)
        dialog.setContentView(view)

        val imageView: ImageView = view.findViewById(R.id.dialogImageView)
        val tvDescription: TextView = view.findViewById(R.id.dialogDescription)
        val slider: Slider = view.findViewById(R.id.dialogSlider)
        val tvSliderText: TextView = view.findViewById(R.id.dialogSliderText)
        val favButton: Button = view.findViewById(R.id.dialogFavButton)

        Glide.with(this)
            .load(image.urls.regular)
            .centerCrop()
            .into(imageView)

        tvDescription.text = image.description ?: "Sem descrição disponível"

        slider.addOnChangeListener { _, value, _ ->
            tvSliderText.text = "Nível de Derp: ${value.toInt()}%"
        }

        favButton.setOnClickListener {
            viewModel.saveFavorite(image, slider.value.toInt())
            Toast.makeText(this, "Adicionado ao Hall of Fame com ${slider.value.toInt()}% Derp!", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
        }

        dialog.show()
    }

    private fun setupObservers() {
        val progressBar: ProgressBar = findViewById(R.id.progressBar)
        val errorLayout: View = findViewById(R.id.errorLayout)
        val errorTextView: TextView = findViewById(R.id.errorTextView)
        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)

        viewModel.images.observe(this) { images ->
            adapter.submitList(images)
        }

        viewModel.isLoading.observe(this) { isLoading ->
            if (!swipeRefreshLayout.isRefreshing) {
                progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
            }
            if (!isLoading) {
                swipeRefreshLayout.isRefreshing = false
            }
        }

        viewModel.errorMessage.observe(this) { message ->
            if (message != null) {
                errorLayout.visibility = View.VISIBLE
                errorTextView.text = message
            } else {
                errorLayout.visibility = View.GONE
            }
        }
    }

    private fun setupListeners() {
        val retryButton: Button = findViewById(R.id.retryButton)
        retryButton.setOnClickListener {
            viewModel.fetchDerpImages()
        }

        val swipeRefreshLayout: SwipeRefreshLayout = findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout.setOnRefreshListener {
            viewModel.fetchDerpImages()
        }
    }
}