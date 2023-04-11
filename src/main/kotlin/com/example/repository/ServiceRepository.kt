package com.example.repository

interface ServiceRepository {
    suspend fun serviceRepository(): String
}