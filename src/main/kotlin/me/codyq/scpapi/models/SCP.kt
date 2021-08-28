package me.codyq.scpapi.models

import io.lettuce.core.*
import kotlinx.serialization.Serializable


@Serializable
data class SCP(
    val scpRating: Int,
    val scpId: Int,
    val scpClass: String,
    val scpContainmentProcedures: String,
    val scpDescription: String,
    val scpImages: List<SCPImage>?,
)

@Serializable
data class SCPImage (
    val url: String,
    val caption: String,
)
