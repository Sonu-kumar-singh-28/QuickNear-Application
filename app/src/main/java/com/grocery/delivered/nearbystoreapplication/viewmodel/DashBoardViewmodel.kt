package com.grocery.delivered.nearbystoreapplication.viewmodel

import androidx.lifecycle.LiveData
import com.grocery.delivered.nearbystoreapplication.domain.BannerModel
import com.grocery.delivered.nearbystoreapplication.domain.CategoryModel

class DashBoardViewmodel {
    private val  repository = DashBoardViewmodel()
    fun loadCategory(): LiveData<MutableList<CategoryModel>>{
        return repository.loadCategory()
    }

    fun loadBanner():LiveData<MutableList<BannerModel>>{
        return repository.loadBanner()
    }
}