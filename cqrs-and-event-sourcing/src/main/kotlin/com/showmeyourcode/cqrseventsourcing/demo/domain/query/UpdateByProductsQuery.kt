package com.showmeyourcode.cqrseventsourcing.demo.domain.query

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class UpdateByProductsQuery(
    @Id
    val productId: UUID = UUID.randomUUID(),
    val count: Int
)