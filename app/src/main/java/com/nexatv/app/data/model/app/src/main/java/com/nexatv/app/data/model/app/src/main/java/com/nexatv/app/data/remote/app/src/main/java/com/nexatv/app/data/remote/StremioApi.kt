package com.nexatv.app.data.remote

import com.nexatv.app.data.model.Addon
import com.nexatv.app.data.model.CatalogItem
import com.nexatv.app.data.model.StreamItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import org.json.JSONObject

object StremioApi {

    private val client = OkHttpClient()

    suspend fun parseAddon(url: String): Addon {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(url)
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""

            val json = JSONObject(body)
            val manifest = json.optJSONObject("manifest") ?: JSONObject()

            Addon(
                id = manifest.optString("id"),
                name = manifest.optString("name"),
                url = url,
                type = "stremio"
            )
        }
    }

    suspend fun getCatalog(addonUrl: String): List<CatalogItem> {
        return withContext(Dispatchers.IO) {
            val catalogUrl = addonUrl.replace(
                "manifest.json",
                "catalog/movie/top.json"
            )

            val request = Request.Builder()
                .url(catalogUrl)
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""

            val json = JSONObject(body)
            val metas = json.optJSONArray("metas") ?: JSONArray()

            val items = mutableListOf<CatalogItem>()

            for (i in 0 until metas.length()) {
                val meta = metas.getJSONObject(i)

                items.add(
                    CatalogItem(
                        id = meta.optString("id"),
                        name = meta.optString("name"),
                        poster = meta.optString("poster"),
                        description = meta.optString("description"),
                        type = meta.optString("type")
                    )
                )
            }

            items
        }
    }

    suspend fun getMeta(addonUrl: String, metaId: String): CatalogItem? {
        return withContext(Dispatchers.IO) {
            val metaUrl = addonUrl.replace(
                "manifest.json",
                "meta/movie/$metaId.json"
            )

            val request = Request.Builder()
                .url(metaUrl)
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""

            val json = JSONObject(body)
            val meta = json.optJSONObject("meta") ?: return@withContext null

            CatalogItem(
                id = meta.optString("id"),
                name = meta.optString("name"),
                poster = meta.optString("poster"),
                description = meta.optString("description"),
                type = meta.optString("type")
            )
        }
    }

    suspend fun getStreams(addonUrl: String, metaId: String): List<StreamItem> {
        return withContext(Dispatchers.IO) {
            val streamUrl = addonUrl.replace(
                "manifest.json",
                "stream/movie/$metaId.json"
            )

            val request = Request.Builder()
                .url(streamUrl)
                .build()

            val response = client.newCall(request).execute()
            val body = response.body?.string() ?: ""

            val json = JSONObject(body)
            val streams = json.optJSONArray("streams") ?: JSONArray()

            val items = mutableListOf<StreamItem>()

            for (i in 0 until streams.length()) {
                val stream = streams.getJSONObject(i)

                items.add(
                    StreamItem(
                        title = stream.optString("title"),
                        url = stream.optString("url"),
                        quality = stream.optString("quality"),
                        size = stream.optString("size"),
                        source = stream.optString("source")
                    )
                )
            }

            items
        }
    }
}
