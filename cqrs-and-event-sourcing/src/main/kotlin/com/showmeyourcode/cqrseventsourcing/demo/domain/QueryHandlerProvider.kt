package com.showmeyourcode.cqrseventsourcing.demo.domain

import com.showmeyourcode.cqrseventsourcing.demo.query.getproductavailability.GetProductAvailabilityHandler
import com.showmeyourcode.cqrseventsourcing.demo.query.getproductavailability.GetProductAvailabilityQuery
import com.showmeyourcode.cqrseventsourcing.demo.query.getproductavailability.GetProductAvailabilityQueryResult
import com.showmeyourcode.cqrseventsourcing.demo.query.getproductprice.GetProductPriceHandler
import com.showmeyourcode.cqrseventsourcing.demo.query.getproductprice.GetProductPriceQuery
import com.showmeyourcode.cqrseventsourcing.demo.query.getproductprice.GetProductPriceQueryResult
import com.showmeyourcode.cqrseventsourcing.demo.query.getproducts.GetProductsHandler
import com.showmeyourcode.cqrseventsourcing.demo.query.getproducts.GetProductsQuery
import com.showmeyourcode.cqrseventsourcing.demo.query.getproducts.GetProductsQueryResult
import com.showmeyourcode.cqrseventsourcing.demo.query.updatesbyproduct.GetUpdatesByProductHandler
import com.showmeyourcode.cqrseventsourcing.demo.query.updatesbyproduct.GetUpdatesByProductQuery
import com.showmeyourcode.cqrseventsourcing.demo.query.updatesbyproduct.GetUpdatesByProductQueryResult
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class QueryHandlerProvider constructor(private val applicationContext: ApplicationContext) {

    fun getProductAvailability(query: GetProductAvailabilityQuery): GetProductAvailabilityQueryResult {
        return applicationContext.getBean(GetProductAvailabilityHandler::class.java).handle(query)
    }

    fun getProducts(query: GetProductsQuery): GetProductsQueryResult {
        return applicationContext.getBean(GetProductsHandler::class.java).handle(query)
    }

    fun getProductPrice(query: GetProductPriceQuery): GetProductPriceQueryResult {
        return applicationContext.getBean(GetProductPriceHandler::class.java).handle(query)
    }

    fun getUpdatesByProduct(query: GetUpdatesByProductQuery): GetUpdatesByProductQueryResult {
        return applicationContext.getBean(GetUpdatesByProductHandler::class.java).handle(query)
    }
}
