package com.food.ordering.system.order.service.domain.domain.entity

import com.food.ordering.system.domain.entity.AggregateRoot
import com.food.ordering.system.domain.valueobject.CustomerId
import com.food.ordering.system.domain.valueobject.Money
import com.food.ordering.system.domain.valueobject.OrderId
import com.food.ordering.system.domain.valueobject.OrderStatus
import com.food.ordering.system.domain.valueobject.RestaurantId
import com.food.ordering.system.domain.valueobject.sum
import com.food.ordering.system.order.service.domain.domain.exception.OrderDomainException
import com.food.ordering.system.order.service.domain.domain.valueobject.StreetAddress
import com.food.ordering.system.order.service.domain.domain.valueobject.TrackingId
import java.util.*


class Order(
    id: OrderId,
    val customerId: CustomerId,
    val restaurantId: RestaurantId,
    val streetAddress: StreetAddress,
    val price: Money,
    val items: MutableList<OrderItem>,
    var trackingId: TrackingId? = null,
    var status: OrderStatus = OrderStatus.PENDING
) : AggregateRoot<OrderId>(id) {

    var failureMessages: MutableList<String>? = null

    fun initializeOrder(): Unit {
        trackingId = TrackingId(UUID.randomUUID())
    }

    private fun initializeOrderItems() {
        var itemId = 1L
        items.forEach {
            it.initializeOrderItem(id)
        }
    }

    fun validateOrder() {
        validateTotalPrice()
        validateItemsPrice()
    }

    fun pay() {
        if (status != OrderStatus.PENDING) {
            throw OrderDomainException("${OrderStatus.PENDING} 상태에서만 결제를 시작할 수 있습니다")
        }
        this.status = OrderStatus.PAID
    }

    fun approve() {
        if (status != OrderStatus.PAID) {
            throw OrderDomainException("${OrderStatus.PAID} 상태에서만 결제를 승인할 수 있습니다")
        }

        this.status = OrderStatus.APPROVED
    }

    fun initCancel(failureMessages: List<String>) {
        if (status != OrderStatus.PAID) {
            throw OrderDomainException("${OrderStatus.PAID} 상태에서만 결제를 승인할 수 있습니다")
        }

        this.status = OrderStatus.CANCELLING
        updateFailureMessages(failureMessages)
    }

    private fun updateFailureMessages(failureMessages: List<String>) {
        (this.failureMessages ?: run { this.failureMessages = mutableListOf(); this.failureMessages!! })
            .addAll(failureMessages)
    }

    fun cancel(failureMessages: List<String>) {
        if (!EnumSet.of(OrderStatus.CANCELLING, OrderStatus.PENDING).contains(status)) {
            throw OrderDomainException("주문 취소는 ${OrderStatus.CANCELLING},${OrderStatus.PENDING} 상태여야 합니다")
        }

        updateFailureMessages(failureMessages)
        this.status = OrderStatus.CANCELED
    }

    private fun validateTotalPrice() {
        if (!price.isGreaterThanZero())
            throw OrderDomainException("Price 는 음수 일 수 없습니다")
    }

    private fun validateItemsPrice() {
        val orderItemsTotal = items.map {
            it.validateItemPrice()
            it.subTotal
        }.sum()

        if (price != orderItemsTotal) {
            throw OrderDomainException("price 와 order Item total 이 맞지 않습니다.")
        }
    }
}
