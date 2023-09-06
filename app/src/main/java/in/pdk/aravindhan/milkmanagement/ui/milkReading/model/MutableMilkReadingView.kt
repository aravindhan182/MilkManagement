package `in`.pdk.aravindhan.milkmanagement.ui.milkReading.model

data class MutableMilkReadingView(
    var readingDate:String? = null,
    var morningReading:Double? = null,
    var eveningReading:Double? = null
)

data class MilkReadingError(
    var morningReading:String? = null,
    var eveningReading:String? = null
)

data class MilkReadingView(
    var readingDate:String,
    var morningDate:String,
    var eveningDate:String
)

