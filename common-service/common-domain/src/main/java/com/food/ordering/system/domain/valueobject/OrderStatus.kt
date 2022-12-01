package com.food.ordering.system.domain.valueobject


enum class OrderStatus(val description: String) {
    PENDING("대기 상태"),
    PAID("지불 됨"),
    APPROVED("승인"),
    CANCELLING("취소중"),
    CANCELED("최소");
}