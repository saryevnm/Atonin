package com.it.atonin.ui.create

import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.provider.MediaStore
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.it.atonin.R
import com.it.atonin.databinding.FragmentCreateBinding
import com.it.atonin.model.Brand
import com.it.atonin.model.Product
import com.it.atonin.model.ProductWithBrandAndStore
import com.it.atonin.model.Store
import com.it.atonin.ui.base.BaseFragment
import com.it.atonin.utils.parcelable
import com.it.atonin.utils.setImage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class CreateFragment : BaseFragment<FragmentCreateBinding>() {

    private val createViewModel: CreateViewModel by viewModel()

    private var productName: String? = null
    private var brandName: String? = null
    private var storeName: String? = null
    private var price: Int? = null
    private var image: String? = null

    private var storeList = emptyList<Store>()
    private var brandList = emptyList<Brand>()

    private val model by lazy {
        arguments?.parcelable(PRODUCT) as ProductWithBrandAndStore?
    }

    override fun bindViewModel() {
        lifecycleScope.launch {
            createViewModel.flowBrands.collectLatest {
                brandList = it
                val brandsAdapter = ArrayAdapter(
                    requireContext(),
                    R.layout.drop_down_item,
                    it.map { region -> region.name }.toTypedArray()
                )
                binding.brandInput.setAdapter(brandsAdapter)
                model?.let { binding.brandInput.setText(model?.brand?.name, false) } ?: kotlin.run {
                    binding.brandInput.text?.clear()
                }
            }
        }

        lifecycleScope.launch {
            createViewModel.flowStores.collectLatest {
                storeList = it
                val storesAdapter = ArrayAdapter(
                    requireContext(),
                    R.layout.drop_down_item,
                    it.map { store -> store.name }.toTypedArray()
                )
                binding.storeInput.setAdapter(storesAdapter)
                model?.let { binding.storeInput.setText(model?.store?.name, false) } ?: kotlin.run {
                    binding.storeInput.text?.clear()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun setupView() {
        setUpListeners()
        createViewModel.getStoresOfUser()
        createViewModel.getBrands()
        with(binding) {

            model?.let {
                nameInput.setText(it.product.name)
                priceInput.setText(it.product.price.toString())
                profileImg.setImage(it.product.image)
                image = it.product.image
                createProductBtn.text = context?.getString(R.string.update)
            } ?: kotlin.run {
                nameInput.text?.clear()
                priceInput.text?.clear()
                profileImg.setImage("")
                createProductBtn.text = context?.getString(R.string.create)
            }
        }
    }

    private fun setUpListeners() {
        with(binding) {
            nameInput.addTextChangedListener {
                productName = if (it?.length == 0) {
                    null
                } else {
                    it.toString()
                }
                createProductBtn.isEnabled = isCreateProductButtonActive()
            }

            brandInput.addTextChangedListener {
                brandName = if (it?.length == 0) {
                    null
                } else {
                    it.toString()
                }
                createProductBtn.isEnabled = isCreateProductButtonActive()
            }

            storeInput.addTextChangedListener {
                storeName = if (it?.length == 0) {
                    null
                } else {
                    it.toString()
                }
                createProductBtn.isEnabled = isCreateProductButtonActive()
            }

            priceInput.addTextChangedListener {
                price = if (it?.length == 0) {
                    null
                } else {
                    it.toString().toInt()
                }
                createProductBtn.isEnabled = isCreateProductButtonActive()
            }
            createProductBtn.setOnClickListener {
                lifecycleScope.launch(Dispatchers.IO) {
                    model?.let {
                        val product = bindProduct().apply {
                            productId = it.product.productId
                            date = it.product.date
                        }
                        createViewModel.updateProduct(product)
                    } ?: kotlin.run {
                        createViewModel.createProduct(
                            bindProduct()
                        )
                    }

                    withContext(Dispatchers.Main) {
                        findNavController().navigate(R.id.homeFragment)
                    }
                }
            }

            profileImg.setOnClickListener {
                openGalleryInterface()
            }
        }
    }

    private fun bindProduct() = Product(
        name = productName ?: "",
        storeId = storeList.firstOrNull { it.name == storeName }?.storeId ?: 0,
        brandId = brandList.firstOrNull { it.name == brandName }?.brandId,
        image = image ?: "",
        price = price ?: 0,
        date = Date().toString()
    )


    private fun openGalleryInterface() {
        val gallery = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI)
        startActivityForResult(gallery, GET_IMAGE_FROM_GALLERY_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK && requestCode == GET_IMAGE_FROM_GALLERY_CODE) {
            val imageUri = data?.data
            image = imageUri.toString()
            binding.profileImg.setImage(imageUri.toString())
        }
    }

    private fun isCreateProductButtonActive() =
        storeName != null && productName != null && price != null

    companion object {
        const val GET_IMAGE_FROM_GALLERY_CODE = 1002
        const val PRODUCT = "product"
    }
}