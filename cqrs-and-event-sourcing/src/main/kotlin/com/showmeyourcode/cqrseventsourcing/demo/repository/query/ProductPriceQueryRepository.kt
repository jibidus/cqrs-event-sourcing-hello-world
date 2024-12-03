package com.showmeyourcode.cqrseventsourcing.demo.repository.query

import com.showmeyourcode.cqrseventsourcing.demo.domain.query.ProductPriceQuery
import com.showmeyourcode.cqrseventsourcing.demo.domain.query.ProductQuery
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ProductPriceQueryRepository : JpaRepository<ProductPriceQuery, UUID>
