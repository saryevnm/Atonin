package com.it.atonin.di

import android.content.Context
import androidx.room.Room
import com.it.atonin.database.AppDataBase
import com.it.atonin.repository.ProductRepository
import com.it.atonin.ui.create.CreateViewModel
import com.it.atonin.ui.home.HomeViewModel
import com.it.atonin.ui.profile.ProfileViewModel
import com.it.atonin.utils.SHARED_PREFERENCES_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private const val DEFAULT_DB_NAME = "ATONIN_DB"
val AppModule = module {
    single { androidContext().getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE) }

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDataBase::class.java,
            DEFAULT_DB_NAME
        ).allowMainThreadQueries().build()
    }

    single { ProductRepository(get()) }

    viewModel { HomeViewModel(get(), get()) }
    viewModel { CreateViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}