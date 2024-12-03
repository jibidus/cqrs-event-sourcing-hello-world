package com.showmeyourcode.cqrseventsourcing.demo.query.getproductprice

import com.showmeyourcode.cqrseventsourcing.demo.domain.Query
import com.showmeyourcode.cqrseventsourcing.demo.query.getproductavailability.GetProductAvailabilityQueryResult
import java.util.*

class GetProductPriceQuery(val productId: UUID) :
    Query<GetProductPriceQueryResult>

class GetProductPriceQueryResult(val price: Int)