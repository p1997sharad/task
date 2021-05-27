package com.example.place.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.place.databinding.ActivityMainBinding
import com.example.place.Adapter.PlaceDataAdapter
import com.example.place.Adapter.LoaderStateAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val mainViewModel:MainViewModel by viewModels()
    @Inject
     lateinit var placeDataAdapter: PlaceDataAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerview()
        lifecycleScope.launchWhenStarted {
            mainViewModel.getAllDogs.collectLatest { response->
                binding.apply {
                   progressBar.isVisible=false
                    recyclerview.isVisible=true
                }
                placeDataAdapter.submitData(response)
            }
        }
    }

    private fun initRecyclerview() {
        binding.apply {
            recyclerview.apply {
                setHasFixedSize(true)
                layoutManager = GridLayoutManager(this@MainActivity,2)
                adapter = placeDataAdapter.withLoadStateHeaderAndFooter(
                    header = LoaderStateAdapter { placeDataAdapter :: retry},
                    footer = LoaderStateAdapter{placeDataAdapter :: retry}
                )
            }
        }
    }
}




//aa230118-74d4-4f21-b6a5-37058cfaa771