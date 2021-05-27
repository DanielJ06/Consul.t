package com.ioasys.diversidade.models

data class Consult(
    val id: String,
    val status: String,
    val reason: String,
    val comment: String,
    val wasGood: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val professionalId: String,
    val userId: String,
    val professional: Professional
)

data class ConsultsList(
    val data: List<Consult>
)