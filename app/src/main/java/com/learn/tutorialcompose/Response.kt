package com.learn.tutorialcompose

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import com.google.gson.annotations.SerializedName

@Parcelize
data class Response(
	@field:SerializedName("cards")
	val cards: List<CardsItem?>? = null
) : Parcelable

@Parcelize
data class CardsItem(

	@field:SerializedName("image_url")
	val imageUrl: String? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: String? = null,

	@field:SerializedName("title")
	val title: String? = null
) : Parcelable