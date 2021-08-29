package me.codyq.scpapi.models

import kotlinx.serialization.Serializable


@Serializable
data class SCP(
    val scpRating: Int,
    val scpId: String,
    val scpClass: String,
    val scpContainmentProcedures: String,
    val scpDescription: String,
    val scpBody: String,
    val scpImages: List<SCPImage>?,
)

@Serializable
data class SCPImage (
    val url: String,
    val caption: String,
)
