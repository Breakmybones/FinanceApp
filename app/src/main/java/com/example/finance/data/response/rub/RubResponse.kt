package com.example.finance.data.response.rub

import com.google.gson.annotations.SerializedName
import java.util.*
import kotlin.collections.HashMap

data class RubResponse (
    @SerializedName("Date")
    val date: String,
    @SerializedName("PreviousDate")
    val prevDate: String,
    @SerializedName("PreviousURL")
    val prevUrl: String,
    @SerializedName("Timestamp")
    val timestamp: String,
    @SerializedName("Valute")
    val valute: HashMap<String, Currency>
        )

data class Currency (
    @SerializedName("ID")
    val id: String,
    @SerializedName("NumCode")
    val numCode: String,
    @SerializedName("CharCode")
    val charCode: String,
    @SerializedName("Nominal")
    val nominal: Int,
    @SerializedName("Name")
    val name: String,
    @SerializedName("Value")
    val value: Float,
    @SerializedName("Previous")
    val prev: Float
        )