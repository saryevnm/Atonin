package com.it.atonin.ui.activity

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.it.atonin.R
import com.it.atonin.database.AppDataBase
import com.it.atonin.databinding.ActivityMainBinding
import com.it.atonin.model.Brand
import com.it.atonin.model.Product
import com.it.atonin.model.Store
import com.it.atonin.model.User
import com.it.atonin.ui.base.BaseActivity
import com.it.atonin.utils.SHARED_PREFERENCES_IS_DATA_INIT
import com.it.atonin.utils.get
import com.it.atonin.utils.put
import org.koin.android.ext.android.inject
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    private val db: AppDataBase by inject()
    override fun setupView() {

        if (!sharedPrefs.get(SHARED_PREFERENCES_IS_DATA_INIT, false)) {
            initData()
            sharedPrefs.put(SHARED_PREFERENCES_IS_DATA_INIT, true)
        }

        val navController =
            (supportFragmentManager.findFragmentById(R.id.nav_view) as NavHostFragment).navController
        binding.bottomNavigation.setupWithNavController(navController)
    }

    override fun bindViewModel() {}

    private val listImages = listOf(
        "https://inter-sport.s3.amazonaws.com/products/DA1105/black_001/DA1105-3_resized_800X800.png",
        "https://inter-sport.s3.amazonaws.com/products/DD1104/black_013/DD1104-2_resized_800X800.png",
        "https://inter-sport.s3.amazonaws.com/products/DA1351/black_045/DA1351-3_resized_800X800.png",
        "https://inter-sport.s3.amazonaws.com/products/CU6445/black_003/CU6445-4_resized_800X800.jpg",
        "https://inter-sport.s3.amazonaws.com/products/DM5662/dk%20grey%20heather_063/DM5662-1_resized_800X800.png",
        "https://inter-sport.s3.amazonaws.com/products/DA9898/black_010/DA9898-1_resized_400X400.png",
        "https://inter-sport.s3.amazonaws.com/products/CU4953/black_010/CU4953-1_resized_400X400.png",
        "https://inter-sport.s3.amazonaws.com/products/CW6131/black_013/CW6131-1_resized_400X400.png",
        "https://static.insales-cdn.com/images/products/1/6576/562330032/import_files_ea_eaba3d1377dc11eab7c2001e582bf58c_b5e4ea3eebce11ecbad6b42e99659391.jpg",
        "https://static.insales-cdn.com/images/products/1/892/553739132/import_files_32_329e118da6b611ecb933b42e99659391_df9d408ba9b611ecb933b42e99659391.jpg",
        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfZcTXQmmdJ7nLKeCVaN98JwC7Q8cjuZ-aGw&usqp=CAU"
    )

    private fun initData() {
        with(db.getUserDao()) {
            addUser(
                User(
                    0,
                    "Nurumbet",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/c/c7/Flag_of_Kyrgyzstan.svg/240px-Flag_of_Kyrgyzstan.svg.png",
                    "+996509803311"
                )
            )
        }
        with(db.getStoreDao()) {
            var productId = 0
            var brandId = 0
            var storeId = 0
            repeat(3) {
                addStore(
                    Store(
                        storeId, "store $storeId", 0, "+996509803311", "bishkek"
                    )
                )
                repeat(3) {
                    db.getBrandDao().addBrand(
                        Brand(
                            brandId, "brand $brandId", null, brandId, "date $brandId"
                        )
                    )
                    db.getProductDao().addProduct(
                        Product(
                            productId,
                            "name $productId",
                            "description $productId",
                            storeId,
                            brandId,
                            listImages[productId],
                            "${34 + productId * productId} som",
                            Date().toString()
                        )
                    )
                    productId++
                    brandId++
                }
                storeId++
            }
        }
    }
}