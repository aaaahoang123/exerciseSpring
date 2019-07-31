package com.spring.excercise.util

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.nio.charset.Charset
import java.util.Random



fun randomString(length: Int): String {
    var n = length
    // length is bounded by 256 Character
    val array = ByteArray(256)
    Random().nextBytes(array)

    val randomString = String(array, Charset.forName("UTF-8"))

    // Create a StringBuffer to store the result
    val r = StringBuffer()

    // Append first 20 alphanumeric characters
    // from the generated random String into the result
    for (k in 0 until randomString.length) {

        val ch = randomString[k]

        if ((ch in 'a'..'z'
                        || ch in 'A'..'Z'
                        || ch in '0'..'9') && n > 0) {

            r.append(ch)
            n--
        }
    }

    // return the resultant string
    return r.toString()
}

fun errorResponse(message: String = "Thất bại", status: HttpStatus = HttpStatus.NOT_FOUND): ResponseEntity<Any> {
    return ResponseEntity(
            mapOf(
                    "status" to status.value(),
                    "message" to message
            ),
            status
    )
}

fun successResponse(body: Any, isMulti: Boolean = false, meta: Any? = null): ResponseEntity<Any> {
    val result = mapOf(
            "status" to 200,
            "message" to "Success",
            "data" to body
    )
//    if (isMulti) {
//        result.plus("datas" to body)
//    } else {
//        result.plus("data" to body)
//    }

//    if (meta != null) {
//        result.plus("meta" to meta)
//    }
//    result.plus("meta" to "aaloo")
    return ResponseEntity(result, HttpStatus.OK)
}
