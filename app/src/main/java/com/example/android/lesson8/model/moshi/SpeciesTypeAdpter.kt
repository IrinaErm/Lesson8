package com.example.android.lesson8.model.moshi

import android.os.Build
import android.text.Html
import androidx.annotation.RequiresApi
import androidx.core.text.HtmlCompat
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.ToJson


@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class SpeciesImgURL

@Retention(AnnotationRetention.RUNTIME)
@JsonQualifier
annotation class SpeciesString



class SpeciesImgUrlAdapter {
    @ToJson
    fun toJson(@SpeciesImgURL url: String): String? {
        return url
    }

    @FromJson
    @SpeciesImgURL
    fun fromJson(json: Map<String, Any?>): String {
        return json.getValue("src") as String
    }
}

class SpeciesStringAdapter {
    @ToJson
    fun toJson(@SpeciesString name: String): String? {
        return name
    }

    @RequiresApi(Build.VERSION_CODES.N)
    @FromJson
    @SpeciesString
    fun fromJson(json: String): String {
        return Html.fromHtml(json, HtmlCompat.FROM_HTML_MODE_LEGACY).toString()
    }
}