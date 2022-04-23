package com.showmeyourcode.cqrs_eventsourcing.demo.eventhandler

import com.showmeyourcode.cqrs_eventsourcing.demo.domain.event.Event
import com.showmeyourcode.cqrs_eventsourcing.demo.domain.event.ProductCreatedEvent
import com.showmeyourcode.cqrs_eventsourcing.demo.domain.event.ProductUpdatedEvent
import com.showmeyourcode.cqrs_eventsourcing.demo.domain.query.ProductQ
import com.showmeyourcode.cqrs_eventsourcing.demo.infra.eventbus.ProductEventListener
import com.showmeyourcode.cqrs_eventsourcing.demo.repository.query.ProductQueryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EventProjectionHandler(private val repository: ProductQueryRepository) {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    @ProductEventListener
    fun handleProductEvents(event: Event) {
        log.info("Handling an event - $event")
        when (event) {
            is ProductCreatedEvent -> repository.save(
                ProductQ(
                    event.productID,
                    event.name,
                    event.availability
                )
            )
            is ProductUpdatedEvent -> {
                val existingProduct = repository.getById(event.productID)
                repository.save(
                    ProductQ(
                        event.productID,
                        existingProduct.name,
                        event.availability
                    )
                )
            }
        else -> log.warn("Event not handled! $event")
    }
}
}
