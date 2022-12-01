package com.food.ordering.system.domain.valueobject

import java.util.UUID

abstract class BaseId<T> protected constructor(open val value: T) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseId<*>

        if (value != other.value) return false

        return true
    }

    override fun hashCode(): Int {
        return value?.hashCode() ?: 0
    }
}

data class OrderId(override val value: UUID) : BaseId<UUID>(value)
data class ProductId(override val value: UUID) : BaseId<UUID>(value)
data class RestaurantId(override val value: UUID) : BaseId<UUID>(value)
data class CustomerId(override val value: UUID) : BaseId<UUID>(value)
