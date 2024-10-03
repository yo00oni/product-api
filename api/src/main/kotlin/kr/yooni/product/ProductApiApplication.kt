package kr.yooni.product

import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.DispatcherServlet

@SpringBootApplication
class ProductApiApplication

fun main(args: Array<String>) {
    runApplication<ProductApiApplication>(*args).getBean<DispatcherServlet>("dispatcherServlet")
}
