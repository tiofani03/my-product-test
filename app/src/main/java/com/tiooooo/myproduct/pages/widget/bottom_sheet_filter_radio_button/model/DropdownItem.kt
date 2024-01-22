package com.tiooooo.myproduct.pages.widget.bottom_sheet_filter_radio_button.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DropdownItem(
    val id: Int,
    val title: String,
    val slug: String? = null,
    val description: String? = null,
) : Parcelable

