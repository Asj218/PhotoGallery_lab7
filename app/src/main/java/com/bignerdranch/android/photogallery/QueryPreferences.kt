package com.bignerdranch.android.photogallery

import android.content.Context
import android.preference.PreferenceManager

class QueryPreferences {
    private const val PREF_SEARCH_QUERY = "searchQuery"

    object QueryPreferences {
        fun getStoredQuery(context: Context): String {
            val prefs = PreferenceManager.getDefaultSharedPreferences(context)
            return
            val PREF_SEARCH_QUERY
            prefs.getString(PREF_SEARCH_QUERY, "")!!
        }

        fun setStoredQuery(context: Context, query:
        String) {
            PreferenceManager.getDefaultSharedPrefe rences(context)
                .edit {
                    .putString(PREF_SEARCH_QUERY, query)
                }
        }
    }
}