package com.nexatv.app.data

import com.nexatv.app.data.local.AddonDao
import com.nexatv.app.data.local.LocalDatabase
import com.nexatv.app.data.model.Addon
import com.nexatv.app.data.model.CatalogItem
import com.nexatv.app.data.model.StreamItem
import com.nexatv.app.data.remote.StremioApi
import kotlinx.coroutines.flow.firstOrNull

class StremioRepository(
    private val apiKeys: ApiKeys,
    private val database: LocalDatabase
) {

    private val addonDao: AddonDao = database.addonDao()

    suspend fun addAddon(url: String): Addon? {
        return try {
            val addon = StremioApi.parseAddon(url)

            addonDao.insert(
                Addon(
                    id = addon.id,
                    name = addon.name,
                    url = addon.url,
                    type = addon.type
                )
            )

            addon
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getAddons(): List<Addon> {
        return addonDao.getAll()
    }

    suspend fun removeAddon(id: String) {
        addonDao.delete(id)
    }

    suspend fun getCatalog(addonId: String): List<CatalogItem> {
        return try {
            val addon = addonDao.getById(addonId) ?: return emptyList()

            StremioApi.getCatalog(addon.url)
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun getMeta(metaId: String): CatalogItem? {
        return try {
            val addons = addonDao.getAll()

            addons.firstNotNullOfOrNull { addon ->
                StremioApi.getMeta(addon.url, metaId)
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getStreams(metaId: String): List<StreamItem> {
        return try {
            val addons = addonDao.getAll()

            addons.flatMap { addon ->
                StremioApi.getStreams(addon.url, metaId)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}
