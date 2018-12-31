package com.github.pozo

import org.json.JSONArray
import org.json.JSONObject
import java.time.LocalDate


infix fun JSONObject.getObject(key: String): JSONObject {
    return this.optJSONObject(key)
}

fun JSONArray.objects(): MutableList<JSONObject> {
    val objects = mutableListOf<JSONObject>()
    for (j in 0 until this.length()) {
        objects.add(this.optJSONObject(j))
    }
    return objects
}

infix fun JSONObject.objects(key: String): MutableList<JSONObject> {
    return this.getJSONArray(key).objects()
}


infix fun JSONObject.getLocalDate(key: String): LocalDate {
    return if (this.isNull(key)) {
        LocalDate.MIN
    } else {
        LocalDate.parse(this.getString(key))
    }
}

infix fun JSONObject.getString(key: String): String {
    return if (this.isNull(key)) {
        ""
    } else {
        this.getString(key)
    }
}

infix fun JSONObject.getInt(key: String): Int {
    return if (this.isNull(key)) {
        0
    } else {
        this.getInt(key)
    }
}