package com.showmeyourcode.cqrs_eventsourcing.demo

import com.showmeyourcode.cqrs_eventsourcing.demo.command.addproduct.AddProductCommand
import com.showmeyourcode.cqrs_eventsourcing.demo.command.changeavailability.ChangeProductAvailabilityCommand
import com.showmeyourcode.cqrs_eventsourcing.demo.domain.query.ProductQ
import com.showmeyourcode.cqrs_eventsourcing.demo.query.getproductavailability.GetProductAvailabilityQuery
import com.showmeyourcode.cqrs_eventsourcing.demo.query.getproducts.GetProductsQuery
import com.showmeyourcode.cqrs_eventsourcing.demo.repository.query.ProductQueryRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.util.*


@ExtendWith(SpringExtension::class)
@WebFluxTest
@AutoConfigureDataJpa
@ComponentScan(basePackages = ["com.showmeyourcode.cqrs_eventsourcing.demo"])
class ProductControllerTest(
    @Autowired private var webClient: WebTestClient,
    @Autowired private val queryRepository: ProductQueryRepository
) {

    @Test
    fun shouldPerformAddProductCommand() {
        val addProductCmd = AddProductCommand("ExampleProduct", 100)
        webClient.post()
            .uri("/addProduct")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(addProductCmd))
            .exchange()
            .expectStatus().isOk
    }

    @Test
    fun shouldPerformChangeProductAvailabilityCommand() {
        val changeAvailability = ChangeProductAvailabilityCommand(
            UUID.fromString("d76e796b-d809-4adf-abbe-34734eecf8d4"),
            100
        )
        webClient.post()
            .uri("/changeProductAvailability")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(changeAvailability))
            .exchange()
            .expectStatus().isOk;
    }

    @Test
    fun shouldPerformGetProductAvailabilityQuery() {
        val productId = UUID.fromString("11b0673f-e1d6-4dea-8525-ce2e45946fab")
        queryRepository.save(ProductQ(productId,"Example Name",100))

        val getAvailability =
            GetProductAvailabilityQuery(productId)
        webClient.post()
            .uri("/getProductAvailability")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(getAvailability))
            .exchange()
            .expectStatus().isOk;
    }

    @Test
    fun shouldPerformGetProductsQuery() {
        val productsQuery = GetProductsQuery()
        webClient.post()
            .uri("/getProducts")
            .contentType(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromObject(productsQuery))
            .exchange()
            .expectStatus().isOk;
    }
}
