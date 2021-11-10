package com.showmeyourcode.cqrs.cqs.demo.infra

import com.showmeyourcode.cqrs.cqs.demo.command.addproduct.AddProductCommand
import com.showmeyourcode.cqrs.cqs.demo.command.addproduct.AddProductCommandResult
import com.showmeyourcode.cqrs.cqs.demo.command.addproduct.AddProductHandler
import com.showmeyourcode.cqrs.cqs.demo.command.changeavailability.ChangeProductAvailabilityCommand
import com.showmeyourcode.cqrs.cqs.demo.command.changeavailability.ChangeProductAvailabilityHandler
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerResponse


@Component
class CommandHandlerProvider constructor(private val applicationContext: ApplicationContext) {

    fun handleAddProduct(cmd:AddProductCommand):AddProductCommandResult{
        return applicationContext.getBean(AddProductHandler::class.java).handle(cmd)
    }

    fun changeProductAvailability(cmd:ChangeProductAvailabilityCommand){
        return applicationContext.getBean(ChangeProductAvailabilityHandler::class.java).handle(cmd)
    }
}

