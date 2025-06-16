package com.example.rmutt.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig : WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000") // ปรับตาม URL ของ frontend ที่คุณต้องการ
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true)
    }
    override fun addResourceHandlers(registry: ResourceHandlerRegistry) {
        // Configure static resource handling for receipts
        registry.addResourceHandler("/receipts/**")
            .addResourceLocations("file:uploads/receipts/")
    }
}

