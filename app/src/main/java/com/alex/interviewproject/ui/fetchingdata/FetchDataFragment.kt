package com.alex.interviewproject.ui.fetchingdata

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.GridView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.alex.interviewproject.R
import com.alex.interviewproject.framework.domain.City
import com.alex.interviewproject.framework.network.Status
import com.alex.interviewproject.ui.zip.ZipPhotosAdapter
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fetch_data_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File


@AndroidEntryPoint
class FetchDataFragment : Fragment() {


    private val viewModel: FetchDataViewModel by viewModels()
    private lateinit var citiesAdapter: CitiesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fetch_data_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        setUpViewModelObservers()
        setUpOnClickListeners()
        setUpAdapter()
    }

    private fun setUpAdapter() {
        citiesAdapter = CitiesAdapter()
        recyclerViewCities.apply {
            adapter = citiesAdapter
        }
    }

    private fun setUpViewModelObservers() {
        viewModel.citiesNet.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    //citiesAdapter.submitList(it.data!!)
                    progressBar.visibility = View.GONE
                    fillDropDown(city_drop_down, it.data!!)
                }
                Status.ERROR -> {
                    progressBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.citiesStorage.observe(viewLifecycleOwner, {
            citiesAdapter.submitList(it)
        })
    }

    private fun setUpOnClickListeners() {
        city_drop_down?.apply {
            setOnItemClickListener{ parent, ag1, position, ag3 ->
                val item = parent.getItemAtPosition(position) as City
                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.addCity(City(item.id, item.name))
                }
            }
        }
    }

    private fun <T>fillDropDown(dropDown: MaterialAutoCompleteTextView, list: List<T>) {
        dropDown.setAdapter(ArrayAdapter(requireContext(), R.layout.dropdown_menu_item, list))
    }

}