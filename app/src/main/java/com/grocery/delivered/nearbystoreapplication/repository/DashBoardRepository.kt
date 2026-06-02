package com.grocery.delivered.nearbystoreapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.grocery.delivered.nearbystoreapplication.domain.BannerModel
import com.grocery.delivered.nearbystoreapplication.domain.CategoryModel

class DashBoardRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadCategory(): LiveData<MutableList<CategoryModel>> {

        val listData = MutableLiveData<MutableList<CategoryModel>>()

        val ref = firebaseDatabase.getReference("Category")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<CategoryModel>()

                for (childSnapshot in snapshot.children) {

                    val item =
                        childSnapshot.getValue(CategoryModel::class.java)

                    item?.let {
                        list.add(it)
                    }
                }

                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        return listData
    }

    fun loadBanner(): LiveData<MutableList<BannerModel>> {

        val listData = MutableLiveData<MutableList<BannerModel>>()

        val ref = firebaseDatabase.getReference("Banners")

        ref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<BannerModel>()

                for (childSnapshot in snapshot.children) {

                    val item =
                        childSnapshot.getValue(BannerModel::class.java)

                    item?.let {
                        list.add(it)
                    }
                }

                listData.value = list
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle error
            }
        })

        return listData
    }
}