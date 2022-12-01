package com.food.ordering.system.order.service.domain.domain

import com.food.ordering.system.order.service.domain.domain.entity.Order
import com.food.ordering.system.order.service.domain.domain.entity.Restaurant
import com.food.ordering.system.order.service.domain.domain.event.OrderCancelledEvent
import com.food.ordering.system.order.service.domain.domain.event.OrderCreatedEvent
import com.food.ordering.system.order.service.domain.domain.event.OrderPaidEvent

interface OrderDomainService {
    fun validateInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent
    fun payOrder(order: Order): OrderPaidEvent
    fun approvedOrder(order: Order): Unit
    fun cancelOrderPayment(order: Order, failureMessages: List<String>): OrderCancelledEvent
    fun cancelOrder(order: Order, failureMessages: List<String>): Unit
}