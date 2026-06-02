package com.grocery.delivered.nearbystoreapplication.viewmodel

import androidx.lifecycle.LiveData
import com.grocery.delivered.nearbystoreapplication.domain.CategoryModel
import com.grocery.delivered.nearbystoreapplication.domain.StoreModel
import com.grocery.delivered.nearbystoreapplication.repository.ResultRepository

class ResultViewModel {
    private val repository = ResultRepository()

    fun loadSubCateGory(id: String): LiveData<MutableList<CategoryModel>>{
        return  repository.loadSubCategory(id)
    }

    fun loadNearest(id: String): LiveData<MutableList<StoreModel>>{
        return  repository.loadNearest(id)
    }

    fun loadPopular(id: String): LiveData<MutableList<StoreModel>>{
        return  repository.loadPopular(id)
    }
}