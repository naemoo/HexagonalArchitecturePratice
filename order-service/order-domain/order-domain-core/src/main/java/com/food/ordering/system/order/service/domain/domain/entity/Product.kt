package com.food.ordering.system.order.service.domain.domain.entity

import com.food.ordering.system.domain.entity.BaseEntity
import com.food.ordering.system.domain.valueobject.Money
import com.food.ordering.system.domain.valueobject.ProductId

class Product(id: ProductId) : BaseEntity<ProductId>(id) {
    lateinit var name: String
    lateinit var price: Money

    fun updateWithConfirmedNameAndPrice(product: Product) {
        this.name = product.name
        this.price = product.price
    }
}