package com.example.selfhelp.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.selfhelp.R

class HelpData(
    @DrawableRes val imageResourceId: Int,
    @StringRes val name: Int,
    @StringRes val num: Int
)

val helps = listOf(
    HelpData(R.drawable.help_img_1, R.string.help_name_1, R.string.help_number_1),
    HelpData(R.drawable.help_img_2, R.string.help_name_2, R.string.help_number_2),
    HelpData(R.drawable.help_img_3, R.string.help_name_3, R.string.help_number_3),
    HelpData(R.drawable.help_img_4, R.string.help_name_4, R.string.help_number_4),
    HelpData(R.drawable.help_img_5, R.string.help_name_5, R.string.help_number_5),
    HelpData(R.drawable.help_img_6, R.string.help_name_6, R.string.help_number_6),
    HelpData(R.drawable.help_img_7, R.string.help_name_7, R.string.help_number_7),
    HelpData(R.drawable.help_img_8, R.string.help_name_8, R.string.help_number_8),
    HelpData(R.drawable.help_img_9, R.string.help_name_9, R.string.help_number_9),
    HelpData(R.drawable.help_img_10, R.string.help_name_10, R.string.help_number_10)
)