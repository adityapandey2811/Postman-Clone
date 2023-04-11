package com.example.di

import com.example.repository.ServiceRepository
import com.example.repository.ServiceRepositoryImpl
import org.koin.dsl.module

val koinModule = module {
    single<ServiceRepository> {
        ServiceRepositoryImpl()
    }
}