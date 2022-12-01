package com.food.ordering.system.order.service.domain.domain

import com.food.ordering.system.order.service.domain.domain.entity.Order
import com.food.ordering.system.order.service.domain.domain.entity.Restaurant
import com.food.ordering.system.order.service.domain.domain.event.OrderCancelledEvent
import com.food.ordering.system.order.service.domain.domain.event.OrderCreatedEvent
import com.food.ordering.system.order.service.domain.domain.event.OrderPaidEvent
import com.food.ordering.system.order.service.domain.domain.exception.OrderDomainException
import org.slf4j.LoggerFactory
import java.time.LocalDateTime

class OrderDomainServiceImpl : OrderDomainService {
    companion object {
        val log = LoggerFactory.getLogger(this::class.java)
    }


    override fun validateInitiateOrder(order: Order, restaurant: Restaurant): OrderCreatedEvent {
        validateRestaurant(restaurant)
        setOrderProductInformation(order, restaurant)
        order.validateOrder()
        order.initializeOrder()
        log.info("Order with Id : ${order.id} initiated")
        return OrderCreatedEvent(order, LocalDateTime.now())
    }

    private fun validateRestaurant(restaurant: Restaurant) {
        if (!restaurant.activate) {
            throw OrderDomainException("Restaurant id (${restaurant.id}) active 상태가 아닙니다.")
        }
    }

    private fun setOrderProductInformation(order: Order, restaurant: Restaurant) {
        order.items.forEach { orderItem ->
            val currentProduct = orderItem.product

            restaurant.products.forEach { restaurantProduct ->
                if (currentProduct == restaurantProduct) {
                    currentProduct.updateWithConfirmedNameAndPrice(restaurantProduct)
                }
            }
        }
    }

    override fun payOrder(order: Order): OrderPaidEvent {
        order.pay()
        log.info("Order with Id : ${order.id} is paid")
        return OrderPaidEvent(order, LocalDateTime.now())
    }

    override fun approvedOrder(order: Order) {
        order.approve()
        log.info("Order with Id : ${order.id} is approved")
    }

    override fun cancelOrderPayment(order: Order, failureMessages: List<String>): OrderCancelledEvent {
        order.initCancel(failureMessages)
        log.info("Order payment is cancelling for order id: ${order.id}")
        return OrderCancelledEvent(order, LocalDateTime.now())
    }

    override fun cancelOrder(order: Order, failureMessages: List<String>) {
        order.cancel(failureMessages)
        log.info("Order with Id : ${order.id} is  cancelled")
    }
}
