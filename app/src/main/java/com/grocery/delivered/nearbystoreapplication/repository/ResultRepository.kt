package com.grocery.delivered.nearbystoreapplication.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import com.google.firebase.database.ValueEventListener
import com.grocery.delivered.nearbystoreapplication.domain.CategoryModel
import com.grocery.delivered.nearbystoreapplication.domain.StoreModel

class ResultRepository {

    private val firebaseDatabase = FirebaseDatabase.getInstance()

    fun loadSubCategory(
        id: String
    ): LiveData<MutableList<CategoryModel>> {

        val listData = MutableLiveData<MutableList<CategoryModel>>()

        val ref = firebaseDatabase.getReference("SubCategory")

        val query: Query = ref
            .orderByChild("CategoryId")

        query.addListenerForSingleValueEvent(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<CategoryModel>()

                    for (childSnapshot in snapshot.children) {

                        val item = childSnapshot.getValue(
                            CategoryModel::class.java
                        )

                        item?.let {
                            list.add(it)
                        }
                    }

                    listData.value = list
                }

                override fun onCancelled(error: DatabaseError) {

                    listData.value = mutableListOf()
                }
            }
        )

        return listData
    }






    fun loadPopular(
        id: String
    ): LiveData<MutableList<StoreModel>> {

        val listData = MutableLiveData<MutableList<StoreModel>>()

        val ref = firebaseDatabase.getReference("Stores")

        val query: Query = ref
            .orderByChild("CategoryId")

        query.addListenerForSingleValueEvent(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<StoreModel>()

                    for (childSnapshot in snapshot.children) {

                        val item = childSnapshot.getValue(
                            StoreModel::class.java
                        )

                        item?.let {
                            list.add(it)
                        }
                    }

                    listData.value = list
                }

                override fun onCancelled(error: DatabaseError) {

                    listData.value = mutableListOf()
                }
            }
        )

        return listData
    }



    fun loadNearest(
        id: String
    ): LiveData<MutableList<StoreModel>> {

        val listData = MutableLiveData<MutableList<StoreModel>>()

        val ref = firebaseDatabase.getReference("Nearest")

        val query: Query = ref
            .orderByChild("CategoryId")

        query.addListenerForSingleValueEvent(
            object : ValueEventListener {

                override fun onDataChange(snapshot: DataSnapshot) {

                    val list = mutableListOf<StoreModel>()

                    for (childSnapshot in snapshot.children) {

                        val item = childSnapshot.getValue(
                            StoreModel::class.java
                        )

                        item?.let {
                            list.add(it)
                        }
                    }

                    listData.value = list
                }

                override fun onCancelled(error: DatabaseError) {

                    listData.value = mutableListOf()
                }
            }
        )

        return listData
    }
}