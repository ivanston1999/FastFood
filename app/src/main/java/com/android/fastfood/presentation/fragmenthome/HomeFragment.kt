package com.android.fastfood.presentation.fragmenthome

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.android.fastfood.R
import com.android.fastfood.data.FoodDataSource
import com.android.fastfood.data.FoodDataSourceImpl
import com.android.fastfood.databinding.FragmentHomeBinding
import com.android.fastfood.model.FoodMenu
import com.android.fastfood.presentation.fragmenthome.adapter.FoodMenuAdapter
import com.android.fastfood.presentation.fragmenthome.adapter.LayoutGridLinear

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val dataSource: FoodDataSource by lazy {
        FoodDataSourceImpl()
    }
    private val adapter: FoodMenuAdapter by lazy {
        FoodMenuAdapter(LayoutGridLinear.LINEAR){FoodMenu: FoodMenu ->
            navigateToDetail(FoodMenu)
        }
    }

    private fun navigateToDetail(foodMenu: FoodMenu) {
        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToDetailFragment(foodMenu)
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        linearOrGrid()
        switchLinearGrid()

    }

    private fun linearOrGrid() {
        val span = if(adapter.layoutGridLinear == LayoutGridLinear.LINEAR) 1 else 2
        binding.rvFoodMenu.apply {
            layoutManager = GridLayoutManager(requireContext(),span)
            adapter = this@HomeFragment.adapter
        }
        adapter.sendData(dataSource.getFood())

    }

    private fun switchLinearGrid() {
        binding.switchGrid.setOnCheckedChangeListener { _, isChecked ->
            (binding.rvFoodMenu.layoutManager as GridLayoutManager).spanCount = if (isChecked) 2 else 1
            adapter.layoutGridLinear = if(isChecked) LayoutGridLinear.GRID else LayoutGridLinear.LINEAR
            adapter.refreshList()
        }

    }


}