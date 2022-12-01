package com.food.ordering.system.order.service.domain.ports.input.service.message.listener.payment

import com.food.ordering.system.order.service.domain.dto.message.PaymentResponse

interface PaymentResponseMessageListener {
    fun paymentComplete(paymentResponse: PaymentResponse) {

    }

    fun paymentCancelled(paymentResponse: PaymentResponse) {

    }
}