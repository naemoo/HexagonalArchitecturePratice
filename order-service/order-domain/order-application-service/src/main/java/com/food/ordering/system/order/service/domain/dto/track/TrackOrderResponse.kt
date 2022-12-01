package com.food.ordering.system.order.service.domain.dto.track

import com.food.ordering.system.domain.valueobject.OrderStatus
import java.util.*

data class TrackOrderResponse(
    val orderTrackingId: UUID,
    val orderStatus: OrderStatus,
    val failureMessage: List<String>?
)