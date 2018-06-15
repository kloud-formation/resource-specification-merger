package io.kloudformation

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.junit.Test

class SpecificationMergerTest{

    private val jacksonObjectMapper = jacksonObjectMapper()

    @Test
    fun test(){
        println(SpecificationMerger.merge(listOf(
                "ap-northeast-1",
                "ap-northeast-2",
                "ap-south-1",
                "ap-southeast-1",
                "ap-southeast-2",
                "ca-central-1",
                "eu-central-1",
                "eu-west-1",
                "eu-west-2",
                "sa-east-1",
                "us-east-1",
                "us-east-2",
                "us-west-1",
                "us-west-2"
        ).map {
            jacksonObjectMapper.readValue<Specification>(
                    this.javaClass.classLoader.getResource("specification/$it.json")
            )
        }))
    }
}