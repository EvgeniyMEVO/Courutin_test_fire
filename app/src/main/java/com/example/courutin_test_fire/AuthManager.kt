package com.example.courutin_test_fire

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

object AuthManager {
    private val firebaseAuth: FirebaseAuth = Firebase.auth

    suspend fun signUp(email: String, password: String): Result<Unit> {
        // Dispatchers.IO: использует общий пул потоков
        // для выполнения операций ввода-вывода (например, операции с файлами или сетевыми запросами)
        return withContext(Dispatchers.IO) {
            try {
                firebaseAuth.createUserWithEmailAndPassword(email, password).await()
                val userId = AuthManager.getCurrentUserId().toString() // get authenticated user id
                DatabaseManager.setUserId(userId)
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun signIn(email: String, password: String): Result<Unit> {
        // приостановит выполнение корутины до завершения операции аутентификации
        return withContext(Dispatchers.IO) {
            try {
                firebaseAuth.signInWithEmailAndPassword(email, password).await()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

    suspend fun forgotPassword(email: String): Result<Unit>{
        return withContext(Dispatchers.IO){
            try {
                firebaseAuth.sendPasswordResetEmail(email).await()
                Result.success(Unit)
            } catch (e: Exception){
                Result.failure(e)
            }
        }
    }

    fun signOut() {
        firebaseAuth.signOut()
    }

    fun getCurrentUser() = firebaseAuth.currentUser

    fun getCurrentUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    fun getCurrentUserEmail(): String? {
        return firebaseAuth.currentUser?.email
    }

    suspend fun updateEmail(newEmail: String): Result<Unit> {
        return withContext(Dispatchers.IO) {
            try {
                firebaseAuth.currentUser?.updateEmail(newEmail)?.await()
                Result.success(Unit)
            } catch (e: Exception) {
                Result.failure(e)
            }
        }
    }

}