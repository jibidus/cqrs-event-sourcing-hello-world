package com.showmeyourcode.cqrseventsourcing.demo.repository.query

import com.showmeyourcode.cqrseventsourcing.demo.domain.query.ProductQuery
import com.showmeyourcode.cqrseventsourcing.demo.domain.query.UpdateByProductsQuery
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UpdatesByProductQueryRepository: JpaRepository<UpdateByProductsQuery, UUID>