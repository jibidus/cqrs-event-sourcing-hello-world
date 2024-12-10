package com.showmeyourcode.cqrseventsourcing.demo.query.updatesbyproduct

import com.showmeyourcode.cqrseventsourcing.demo.domain.QueryHandler
import com.showmeyourcode.cqrseventsourcing.demo.repository.query.UpdatesByProductQueryRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class GetUpdatesByProductHandler(private val repository: UpdatesByProductQueryRepository) :
    QueryHandler<GetUpdatesByProductQueryResult, GetUpdatesByProductQuery> {
    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun handle(query: GetUpdatesByProductQuery?): GetUpdatesByProductQueryResult {
        log.info("Handling 'GetUpdateByProductQuery' command...")
        return GetUpdatesByProductQueryResult(repository.findAll().associate { it.productId to it.count })
    }
}