package com.food.ordering.system.order.service.domain.domain.event

import com.food.ordering.system.order.service.domain.domain.entity.Order
import java.time.LocalDateTime

class OrderCreatedEvent(
    order: Order,
    createdAt: LocalDateTime
) : OrderEvent(order, createdAt)