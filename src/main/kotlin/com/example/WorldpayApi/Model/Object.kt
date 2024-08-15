package com.example.WorldpayApi.Model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class Object(
    var userId: Long?,
    var id: Long?,
    var title: String?,
    var body: String?
)

data class ObjectUpdate(
    var title: String?
)