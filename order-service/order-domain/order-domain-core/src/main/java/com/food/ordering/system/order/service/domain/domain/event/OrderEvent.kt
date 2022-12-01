package com.food.ordering.system.order.service.domain.domain.event

import com.food.ordering.system.domain.event.DomainEvent
import com.food.ordering.system.order.service.domain.domain.entity.Order
import java.time.LocalDateTime

abstract class OrderEvent(val order: Order, val createdAt:LocalDateTime) : DomainEvent<Order>