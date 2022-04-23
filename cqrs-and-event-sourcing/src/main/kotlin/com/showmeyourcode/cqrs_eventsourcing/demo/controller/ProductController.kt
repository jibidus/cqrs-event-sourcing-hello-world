package com.showmeyourcode.cqrs_eventsourcing.demo.controller

import com.showmeyourcode.cqrs_eventsourcing.demo.command.addproduct.AddProductCommand
import com.showmeyourcode.cqrs_eventsourcing.demo.command.addproduct.AddProductCommandResult
import com.showmeyourcode.cqrs_eventsourcing.demo.command.changeavailability.ChangeProductAvailabilityCommand
import com.showmeyourcode.cqrs_eventsourcing.demo.domain.CommandHandlerProvider
import com.showmeyourcode.cqrs_eventsourcing.demo.domain.QueryHandlerProvider
import com.showmeyourcode.cqrs_eventsourcing.demo.query.getproductavailability.GetProductAvailabilityQuery
import com.showmeyourcode.cqrs_eventsourcing.demo.query.getproductavailability.GetProductAvailabilityQueryResult
import com.showmeyourcode.cqrs_eventsourcing.demo.query.getproducts.GetProductsQuery
import com.showmeyourcode.cqrs_eventsourcing.demo.query.getproducts.GetProductsQueryResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.server.ServerResponse
import reactor.core.publisher.Mono

@RestController
class ProductController(
    private val commandHandler: CommandHandlerProvider,
    private val queryHandlerProvider: QueryHandlerProvider
) {

    @PostMapping("/addProduct")
    fun addProduct(@RequestBody command: AddProductCommand): Mono<AddProductCommandResult> {
        // TODO: consider returning ID of a new product
        commandHandler.handleAddProduct(command)
        return Mono.empty()
    }

    @PostMapping("/changeProductAvailability")
    fun buyAdditionalCover(@RequestBody command: ChangeProductAvailabilityCommand): Mono<ServerResponse> {
        commandHandler.changeProductAvailability(command)
        return ServerResponse.noContent().build()
    }

    @PostMapping("/getProductAvailability")
    fun buyAdditionalCover(@RequestBody query: GetProductAvailabilityQuery): Mono<GetProductAvailabilityQueryResult> {
        return Mono.just(queryHandlerProvider.getProductAvailability(query))
    }

    @PostMapping("/getProducts")
    fun buyAdditionalCover(@RequestBody query: GetProductsQuery): Mono<GetProductsQueryResult> {
        return Mono.just(queryHandlerProvider.getProducts(query))
    }
}
