package com.showmeyourcode.cqrseventsourcing.demo.query.getproductprice

import com.showmeyourcode.cqrseventsourcing.demo.domain.QueryHandler
import com.showmeyourcode.cqrseventsourcing.demo.repository.query.ProductPriceQueryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetProductPriceHandler(private val repository: ProductPriceQueryRepository) :
    QueryHandler<GetProductPriceQueryResult, GetProductPriceQuery> {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun handle(query: GetProductPriceQuery): GetProductPriceQueryResult {
        log.info("Handling 'GetProductPrice' command...")
        val product = repository.findById(query.productId)
        val price = if (product.isEmpty) 0 else product.get().price
        return GetProductPriceQueryResult(price)
    }
}
