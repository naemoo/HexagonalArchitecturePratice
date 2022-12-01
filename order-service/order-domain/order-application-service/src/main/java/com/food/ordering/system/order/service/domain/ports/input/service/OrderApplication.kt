package com.food.ordering.system.order.service.domain.ports.input.service

import com.food.ordering.system.order.service.domain.dto.create.CreateOrderCommand
import com.food.ordering.system.order.service.domain.dto.create.CreateOrderResponse
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderQuery
import com.food.ordering.system.order.service.domain.dto.track.TrackOrderResponse
import javax.validation.Valid

interface OrderApplication {
    fun createOrder(@Valid createOrderCommand: CreateOrderCommand): CreateOrderResponse
    fun trackOrder(@Valid trackOrderQuery: TrackOrderQuery): TrackOrderResponse
}