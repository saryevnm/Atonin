package com.it.atonin.ui.profile

import android.annotation.SuppressLint
import androidx.lifecycle.lifecycleScope
import com.it.atonin.databinding.FragmentProfileBinding
import com.it.atonin.ui.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("SetTextI18n")
class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    private val profileViewModel: ProfileViewModel by viewModel()

    override fun setupView() {
        with(binding) {
            profileViewModel.getBrandsCount()
            lifecycleScope.launch {
                profileViewModel.getUser().collectLatest {
                    profileName.text = it.name
                    phoneNumber.text = it.phoneNumber
                }
            }

            lifecycleScope.launch {
                profileViewModel.getStoreCount().collectLatest {
                    storeCount.text = "stores $it"
                }
            }
        }
    }

    override fun bindViewModel() {
        lifecycleScope.launch {
            profileViewModel.flowBrandsCount.collectLatest {
                binding.brandCount.text = "brands $it"
            }
        }
    }
}