package com.example.courutin_test_fire

import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object DatabaseManager {

    private val database = Firebase.database


    // заполнится только после регистрации или захода в MainActivity
    private var _userId: String? = null
    private val userId: String
        get() = _userId ?: throw IllegalStateException("User ID not initialized")


    suspend fun saveUserContractAddress(contractAddress: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val userContractsRef = database.getReference(userId)
                userContractsRef.child("address").setValue(contractAddress).await()
                // результат будет в виде Success(данные), чтобы этого избежать можно использовать result.getOrThrow()
                // где result это результат данного метода
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun saveAnyInfo(key: String, info: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                val userContractsRef = database.getReference(userId)
                userContractsRef.child(key).setValue(info).await()
                // результат будет в виде Success(данные), чтобы этого избежать можно использовать result.getOrThrow()
                // где result это результат данного метода
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun getAnyInfo(key: String): Result<String?> {
        return withContext(Dispatchers.IO) {
            try {
                val userContractsRef = database.getReference(userId)
                val snapshot = userContractsRef.child(key).get().await()
                val res = snapshot.getValue(String::class.java)
                Result.success(res)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }


    suspend fun getUserContractAddress(): Result<String> {
        return withContext(Dispatchers.IO) {
            try {
                val userContractsRef = database.getReference(userId)
                val snapshot = userContractsRef.child("address").get().await()
                val contractAddress = snapshot.getValue(String::class.java)
                    ?: throw Exception("Contract address not found")
                Result.success(contractAddress)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    fun setUserId(id: String) {
        _userId = id
    }
}