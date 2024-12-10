package com.showmeyourcode.cqrseventsourcing.demo.eventhandler

import com.showmeyourcode.cqrseventsourcing.demo.domain.event.Event
import com.showmeyourcode.cqrseventsourcing.demo.domain.event.ProductCreatedEvent
import com.showmeyourcode.cqrseventsourcing.demo.domain.event.ProductUpdatedEvent
import com.showmeyourcode.cqrseventsourcing.demo.domain.query.ProductQuery
import com.showmeyourcode.cqrseventsourcing.demo.domain.query.UpdateByProductsQuery
import com.showmeyourcode.cqrseventsourcing.demo.infra.eventbus.ProductEventListener
import com.showmeyourcode.cqrseventsourcing.demo.repository.query.ProductQueryRepository
import com.showmeyourcode.cqrseventsourcing.demo.repository.query.UpdatesByProductQueryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EventProjectionHandler(private val repository: ProductQueryRepository, private val updatesByProductQueryRepository: UpdatesByProductQueryRepository) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ProductEventListener
    fun handleProductEvents(event: Event) {
        log.info("Handling an event - $event")
        when (event) {
            is ProductCreatedEvent -> {
                repository.save(
                    ProductQuery(
                        event.productID,
                        event.name,
                        event.availability,
                    ))
                updatesByProductQueryRepository.save(
                    UpdateByProductsQuery(event.productID, 1)
                )
            }
            is ProductUpdatedEvent -> {
                val existingProduct = repository.getById(event.productID)
                repository.save(
                    ProductQuery(
                        event.productID,
                        existingProduct.name,
                        event.availability,
                    ),
                )
                val productsQuery = updatesByProductQueryRepository.findById(event.productID).get()
                updatesByProductQueryRepository.save(
                    UpdateByProductsQuery(event.productID, productsQuery.count + 1)
                )
            }
            else -> log.warn("Event not handled! $event")
        }
    }
}
