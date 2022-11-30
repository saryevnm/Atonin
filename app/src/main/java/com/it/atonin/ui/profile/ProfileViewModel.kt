package com.it.atonin.ui.profile

import android.app.Application
import androidx.lifecycle.viewModelScope
import com.it.atonin.repository.StoreRepository
import com.it.atonin.repository.UserRepository
import com.it.atonin.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val storeRepository: StoreRepository,
    private val userRepository: UserRepository,
    app: Application
) : BaseViewModel(app) {

    private val _flowBrandsCount = MutableStateFlow(0)
    val flowBrandsCount: Flow<Int> = _flowBrandsCount

    fun getUser() = userRepository.getUser(0)

    fun getStoreCount() = storeRepository.getStoresCount(0)

    fun getBrandsCount() {
        viewModelScope.launch {
            storeRepository.getStoresOfUser(0).collect { stores ->
                val storeIds = stores.map { it.storeId }
                storeRepository.getBrandsCount(storeIds).collect {
                    _flowBrandsCount.emit(it)
                }
            }
        }
    }
}