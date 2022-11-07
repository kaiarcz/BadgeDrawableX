package com.kaiarcz.badgedrawablex

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.doOnLayout
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeUtils
import com.google.android.material.badge.ExperimentalBadgeUtils
import com.kaiarcz.badgedrawablex.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var badgeDrawable: BadgeDrawable

    @ExperimentalBadgeUtils
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpBadgeDrawable()
        setUpNumberPicker()
    }

    private fun setUpNumberPicker() {
        binding.apply {
            badgeCountUpdaterNp.apply {
                minValue = 0
                maxValue = 10
                setOnValueChangedListener { numberPicker, _, _ ->
                    updateBadgeCount(numberPicker.value)
                }
            }
        }
    }

    private fun updateBadgeCount(count: Int) {
        binding.apply {
            notificationBtn.doOnLayout {
                if (count > 0) {
                    badgeDrawable.apply {
                        isVisible = true
                        number = count
                    }
                } else {
                    badgeDrawable.apply {
                        isVisible = false
                        clearNumber()
                    }
                }
            }
        }
    }

    @ExperimentalBadgeUtils
    private fun setUpBadgeDrawable() {
        binding.apply {
            badgeDrawable = BadgeDrawable.create(this@MainActivity)
            notificationBtn.doOnLayout {
                badgeDrawable.apply {
                    verticalOffset = 30
                    horizontalOffset = 30
                    isVisible = false
                }
            }
            BadgeUtils.attachBadgeDrawable(
                badgeDrawable,
                notificationBtn,
                notificationContainerFl
            )
        }
    }
}