package com.tiooooo.myproduct.data

import com.tiooooo.myproduct.model.Order
import com.tiooooo.myproduct.model.ProductReview
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.random.Random

object Review {
    fun generateDummyReviews(): List<ProductReview> {
        val dummyProductReviews = mutableListOf<ProductReview>()
        for (id in 1..100) {
            val productName = when (id % 3) {
                0 -> "ProductA"
                1 -> "ProductB"
                else -> "ProductC"
            }
            val rating = generateRandomRating()
            val review = generateRandomReview(rating)

            dummyProductReviews.add(ProductReview(id, productName, rating, review))
        }
        return dummyProductReviews
    }
}

object SentimentAnalysis {
    fun generateDummySentimentAnalysis(text: String): String {
        return if (text.isNotEmpty()) {
            if (Random.nextBoolean()) "Positive Review" else "Negative Review"
        } else ""
    }
}

object OptimizePacking {
    fun optimizePackaging(quantity: Map<String, Int>): String {
        return if (quantity.size < 3) {
            "Standard Packaging"
        } else {
            "Custom Packaging"
        }
    }

    fun generateDummyOrder(count: Int = 10): List<Order> {
        val orders = mutableListOf<Order>()
        for (i in 1..count) {
            val order = when (i % 3) {
                0 -> Order(
                    generateUniqueID(),
                    listOf("Laptop", "Mouse", "Keyboard"),
                    mapOf(
                        "Laptop" to generateRandomInt(),
                        "Mouse" to generateRandomInt(),
                        "Keyboard" to generateRandomInt()
                    ),
                    generateRandomName(),
                    generateAddress(),
                )

                1 -> Order(
                    generateUniqueID(),
                    listOf("Monitor", "Headphones"),
                    mapOf("Monitor" to generateRandomInt(), "Headphones" to generateRandomInt()),
                    generateRandomName(),
                    generateAddress(),
                )

                else -> Order(
                    generateUniqueID(),
                    listOf("Tablet", "Charger"),
                    mapOf("Tablet" to generateRandomInt(), "Charger" to generateRandomInt()),
                    generateRandomName(),
                    generateAddress(),
                )
            }
            orders.add(order)
        }

        return orders
    }
}


fun generateRandomRating(): Double {
    val randomValue = Random.nextDouble(1.0, 5.0)
    return BigDecimal(randomValue).setScale(1, RoundingMode.HALF_UP).toDouble()
}

fun generateRandomReview(rating: Double): String {
    val commentList = commentsMap.entries.find { entry -> rating in entry.key }?.value
    return commentList?.random() ?: "No comment available."
}

private val commentsMap = mapOf(
    1.0..2.5 to listOf(
        "Not satisfied. Needs improvement.",
        "Could be better. Disappointed.",
        "Quality is not up to the mark.",
        "Expected more. Disappointing experience.",
        "Not recommended. Waste of money.",
        "Poor quality. Unpleasant experience.",
        "Regrettable purchase. Very dissatisfied.",
        "Subpar. Would not buy again.",
        "Terrible product. Avoid at all costs.",
        "Frustrating experience. Would not recommend.",
        "Not satisfied with the product. It needs improvement to meet my expectations. The quality is subpar, and I'm disappointed with the overall experience. I expected more, but it turned out to be a disappointing purchase. I do not recommend it; it's a waste of money. The product's poor quality led to an unpleasant and regrettable purchase. I am very dissatisfied and would not buy it again. Terrible product; I would advise avoiding it at all costs. The whole experience was frustrating, and I would not recommend it.",
    ),
    2.6..3.5 to listOf(
        "Average product. Could be better.",
        "Okay, but not impressive.",
        "Fair quality. Nothing special.",
        "Mediocre. Wouldn't buy again.",
        "Acceptable. Nothing to rave about.",
        "Middle-of-the-road. Not great, not terrible.",
        "Decent enough. Expected more.",
        "So-so. Wouldn't consider repurchasing.",
        "Satisfactory. Not exceptional.",
        "Could use improvement. Just okay.",
        "The product is average and could be better. It's okay, but not impressive. The quality is fair, with nothing special to highlight. It's a mediocre product that I wouldn't buy again. Acceptable, but there's nothing to rave about. It's middle-of-the-road, not great, and not terrible. Decent enough, but I expected more from it. So-so; I wouldn't consider repurchasing it. Satisfactory, but it's not exceptional. It could use improvement; it's just okay for me."
    ),
    3.6..4.5 to listOf(
        "Satisfied with the purchase. Good quality.",
        "Met my expectations. Decent product.",
        "Above average. Happy with the product.",
        "Impressed with the quality. Would recommend.",
        "Good value for money. Pleased with the purchase.",
        "Solid product. Does the job well.",
        "Well-made. Exceeded my expectations.",
        "Happy customer. Would buy again.",
        "Highly satisfactory. No complaints.",
        "Very pleased with the product. Great value.",
        "I am satisfied with the purchase; the product has good quality. It met my expectations, providing a decent product. Above average, and I am happy with the overall product. I am impressed with the quality and would recommend it to others. It's good value for money, and I'm pleased with the purchase. The product is solid and does the job well. Well-made and exceeded my expectations. I am a happy customer and would buy it again. Highly satisfactory, and I have no complaints. Very pleased with the product; it offers great value."
    ),
    4.6..5.0 to listOf(
        "Excellent product! Highly recommended.",
        "Outstanding quality. Worth every penny.",
        "Top-notch. Couldn't be happier.",
        "Absolutely fantastic! Delighted with the purchase.",
        "Perfect in every way. Exceeds expectations.",
        "Impressive product. Superior quality.",
        "A gem. Remarkably well-crafted.",
        "Beyond satisfied. Incredible value.",
        "Unparalleled quality. Best purchase ever.",
        "Exceptional product. A true standout.",
        "The product is excellent! Highly recommended for its outstanding quality. It's top-notch, and I couldn't be happier with it. Absolutely fantastic! Delighted with the purchase in every way. Perfect in every way; it exceeds expectations. An impressive product with superior quality. A gem of a product, remarkably well-crafted. Beyond satisfied with its incredible value. Unparalleled quality; the best purchase ever. An exceptional product that is a true standout."
    )
)

fun generateDummyLoading(max: Long = 3000L): Long = (0L..max).random()

fun generateRandomInt(max: Int = 10) = (1..max).random()

fun generateUniqueID(): String {
    val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
    val idLength = 7
    val random = java.util.Random()

    val id = StringBuilder(idLength)
    repeat(idLength) {
        id.append(characters[random.nextInt(characters.length)])
    }

    return id.toString()
}

fun generateRandomName(): String {
    val firstNameList =
        listOf("Tio", "Andi", "Budi", "Citra", "Dewi", "Eko", "Fitri", "Gunawan", "Hendra", "Ika")
    val lastNameList =
        listOf(
            "Fani",
            "Saputra",
            "Susilo",
            "Wijaya",
            "Kusuma",
            "Hadi",
            "Sari",
            "Wibowo",
            "Nugroho",
            "Setiawan"
        )


    val random = java.util.Random()

    val firstName = firstNameList[random.nextInt(firstNameList.size)]
    val lastName = lastNameList[random.nextInt(lastNameList.size)]

    return "$firstName $lastName"
}

fun generateRandomString(length: Int): String {
    val characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789 ,.-"
    val random = java.util.Random()

    val randomString = StringBuilder(length)
    repeat(length) {
        randomString.append(characters[random.nextInt(characters.length)])
    }

    return randomString.toString()
}

fun generateAddress(): String {
    val streetList =
        listOf("Jalan Utama", "Jalan Raya", "Jalan Merdeka", "Jalan Pahlawan", "Jalan Bersama")
    val cityList = listOf("Jakarta", "Surabaya", "Bandung", "Medan", "Semarang", "Yogyakarta")
    val provinceList = listOf(
        "DKI Jakarta",
        "Jawa Timur",
        "Jawa Barat",
        "Sumatera Utara",
        "Central Java",
        "DI Yogyakarta"
    )
    val postalCodeList = listOf("10110", "60284", "40121", "20219", "50123")

    val random = java.util.Random()

    val street = streetList[random.nextInt(streetList.size)]
    val city = cityList[random.nextInt(cityList.size)]
    val province = provinceList[random.nextInt(provinceList.size)]
    val postalCode = postalCodeList[random.nextInt(postalCodeList.size)]

    return "$street, $city, $province $postalCode"
}
