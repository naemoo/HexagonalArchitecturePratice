package com.food.ordering.system.order.service.domain.domain.entity

import com.food.ordering.system.domain.entity.BaseEntity
import com.food.ordering.system.domain.valueobject.Money
import com.food.ordering.system.domain.valueobject.OrderId
import com.food.ordering.system.order.service.domain.domain.exception.OrderDomainException
import com.food.ordering.system.order.service.domain.domain.valueobject.OrderItemId

class OrderItem(
    id: OrderItemId,
    val product: Product,
    val quantity: Int,
    val price: Money,
    val subTotal: Money
) : BaseEntity<OrderItemId>(id) {
    lateinit var orderId: OrderId

    fun initializeOrderItem(orderId: OrderId) {
        this.orderId = orderId
    }

    fun validateItemPrice() {
        if (!price.isGreaterThanZero()) {
            throw OrderDomainException("Order Item Price 는 음수 일 수 없습니다")
        }

        if (price != product.price) {
            throw OrderDomainException("Order Item Price 가격이 Product Price 와 맞지 않습니다")
        }

        if (price * quantity != subTotal) {
            throw OrderDomainException("Order Item Price 가격의 총합이 Subtotal 과 맞지 않습니다")
        }
    }
}