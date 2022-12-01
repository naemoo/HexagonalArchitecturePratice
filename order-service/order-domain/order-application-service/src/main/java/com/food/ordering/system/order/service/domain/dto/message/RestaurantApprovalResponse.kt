package com.food.ordering.system.order.service.domain.dto.message

import com.food.ordering.system.domain.valueobject.OrderApprovalStatus
import java.time.LocalDateTime

data class RestaurantApprovalResponse(
    val id: String,
    val sagaId: String,
    val orderId: String,
    val restaurantId: String,
    val createdAt: LocalDateTime,
    val orderApprovalStatus: OrderApprovalStatus,
    val failureMessage: List<String>
)