package com.bektursun.core.constants

enum class Intervals(val interval: String) {
    OneHour("1h"),
    OneDay("1d"),
    SevenDays("7d"),
    ThirtyDays("30d"),
    OneYear("365d");

    companion object {
        fun fetchDefaultInterval(): String =
            "${OneHour.interval},${OneDay.interval},${SevenDays.interval},${ThirtyDays.interval}," +
                    OneYear.interval
    }
}