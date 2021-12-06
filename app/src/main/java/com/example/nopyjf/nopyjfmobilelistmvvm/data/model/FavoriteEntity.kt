package com.example.nopyjf.nopyjfmobilelistmvvm.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Favorite
import com.example.nopyjf.nopyjfmobilelistmvvm.domain.model.Mobile

@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "price") val price: Double?,
    @ColumnInfo(name = "rating") val rating: Double?,
    @ColumnInfo(name = "thumbImageURL") val thumbImageURL: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "brand") val brand: String?,
    @ColumnInfo(name = "name") val name: String?
)

fun Mobile.toFavoriteEntity(): FavoriteEntity {
    return FavoriteEntity(
        id = this.id,
        price = this.price,
        rating = this.rating,
        thumbImageURL = this.thumbImageURL,
        description = this.description,
        brand = this.brand,
        name = this.name
    )
}

fun FavoriteEntity.toFavorite(): Favorite {
    return Favorite(
        id = this.id,
        price = this.price,
        rating = this.rating,
        thumbImageURL = this.thumbImageURL,
        description = this.description,
        brand = this.brand,
        name = this.name
    )
}