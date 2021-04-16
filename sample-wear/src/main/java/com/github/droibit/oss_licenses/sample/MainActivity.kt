package com.github.droibit.oss_licenses.sample

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.github.droibit.oss_licenses.sample.databinding.ActivityMainBinding
import com.github.droibit.oss_licenses.ui.wearable.WearableOssLicensesActivity

class MainActivity : FragmentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.show.setOnClickListener {
            val intent = WearableOssLicensesActivity.createIntent(
                this,
                listOf("com.google.errorprone:javac-shaded-9-dev-r4023-3")
            )
            startActivity(intent)
        }
    }
}
