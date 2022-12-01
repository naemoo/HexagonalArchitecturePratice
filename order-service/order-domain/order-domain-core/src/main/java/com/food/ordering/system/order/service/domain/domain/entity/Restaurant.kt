package com.food.ordering.system.order.service.domain.domain.entity

import com.food.ordering.system.domain.entity.AggregateRoot
import com.food.ordering.system.domain.valueobject.RestaurantId

class Restaurant(
    id: RestaurantId,
    val activate: Boolean,
    val products: MutableSet<Product> = mutableSetOf(),
) : AggregateRoot<RestaurantId>(id)