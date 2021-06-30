package com.example.tegeta.data

import com.example.tegeta.data.model.ServiceType

class DataGenerator {

    companion object {
        fun getServices(): List<ServiceType> {
            return listOf(
                ServiceType("ზეთის შეცვლა"),
                ServiceType("დიაგნოსტიკა"),
                ServiceType("ცეპის დამჭიმის შეცვლა")
            )
        }
    }

}