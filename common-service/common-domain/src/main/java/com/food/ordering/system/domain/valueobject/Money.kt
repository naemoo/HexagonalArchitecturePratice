package com.food.ordering.system.domain.valueobject

import java.math.BigDecimal
import java.math.RoundingMode

data class Money(val amount: BigDecimal) {
    companion object {
        private const val SCALE = 2
        private val ROUNDING_MODE = RoundingMode.HALF_EVEN
        val ZERO: Money = Money(BigDecimal.ZERO)
    }

    fun isGreaterThanZero(): Boolean = amount > BigDecimal.ZERO
    fun isGreaterThan(money: Money): Boolean = this > money


    private fun setScale(input: BigDecimal): BigDecimal =
        input.setScale(SCALE, ROUNDING_MODE)

    operator fun compareTo(other: Money): Int =
        this.amount.compareTo(other.amount)

    operator fun plus(other: Money): Money =
        Money(setScale(this.amount + other.amount))

    operator fun minus(other: Money): Money =
        Money(setScale(this.amount - other.amount))

    operator fun times(multiplier: Int): Money =
        Money(setScale(this.amount.multiply(BigDecimal(multiplier))))

    operator fun times(other: Money): Money =
        Money(setScale(this.amount * other.amount))

    operator fun div(other: Money): Money =
        Money(this.amount.divide(other.amount, SCALE, ROUNDING_MODE))

    operator fun div(divider: Int): Money =
        Money(this.amount.divide(BigDecimal(divider), SCALE, ROUNDING_MODE))

    operator fun rem(other: Money): Money =
        Money(setScale(this.amount % other.amount))

    operator fun unaryMinus(): Money =
        Money(-this.amount)

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Money

        if ((amount.compareTo(other.amount)) != 0) return false

        return true
    }

    override fun hashCode(): Int {
        return amount.hashCode()
    }
}

fun Iterable<Money>.sum(): Money = this.fold(Money.ZERO) { m1, m2 -> m1 + m2 }
