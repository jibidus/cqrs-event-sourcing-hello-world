package com.showmeyourcode.cqrseventsourcing.demo.domain.query

import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class ProductPriceQuery(
    @Id
    val id: UUID = UUID.randomUUID(),
    val name: String?,
    val price: Int
)
