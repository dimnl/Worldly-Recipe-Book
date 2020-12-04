package com.dim.recipes.api

import android.util.Log
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class FavouriteRecipeFirebaseDatabase {
    companion object {
        var userLoggedIn: Boolean = false
        var favouriteRecipeIdList: MutableList<String> = mutableListOf()

        private val database = Firebase.database
        private lateinit var databaseReference: DatabaseReference
        private lateinit var valueEventListener: ValueEventListener

        fun create(user: FirebaseUser) {
            databaseReference =
                database.reference.child("users").child(user.uid).child("fav_recipe_id")
            retrieveExistingFavouriteRecipes()
            valueEventListener =
                databaseReference.addValueEventListener(object : ValueEventListener {
                    override fun onDataChange(dataSnapshot: DataSnapshot) {
                        favouriteRecipeIdList.clear()
                        val values = dataSnapshot.getValue<HashMap<String, Boolean>>()
                        values?.map { keyValue ->
                            if (keyValue.value) {
                                favouriteRecipeIdList.add(keyValue.key)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.w("Database", "Failed to read value")
                    }
                })
        }

        fun write(favId: String) {
            databaseReference.child(favId).setValue(true)
        }

        fun delete(favId: String) {
            databaseReference.child(favId).setValue(false)
            favouriteRecipeIdList.remove(favId)
        }

        private fun retrieveExistingFavouriteRecipes() {
            favouriteRecipeIdList.clear()
            databaseReference.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    val values = dataSnapshot.getValue<HashMap<String, Boolean>>()
                    values?.map { keyValue ->
                        if (keyValue.value) {
                            favouriteRecipeIdList.add(keyValue.key)
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.w("Database", "Failed to read value")
                }
            })
        }
    }
}