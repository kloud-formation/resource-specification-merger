package io.kloudformation.resource

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.JsonNode
import io.kloudformation.CreationPolicy
import io.kloudformation.KloudResource
import io.kloudformation.UpdatePolicy
import io.kloudformation.Value
import io.kloudformation.function.Att
import io.kloudformation.model.KloudFormationTemplate

fun KloudFormationTemplate.Builder.testResource(
        cidrBlock: Value<String>,
        logicalName: String? = null,
        dependsOn: List<String>? = null,
        condition: String? = null,
        metadata: Value<JsonNode>? = null,
        creationPolicy: CreationPolicy? = null,
        updatePolicy: UpdatePolicy? = null,
        deletionPolicy: String? = null,
        builder: TestResource.Builder.() -> TestResource.Builder = { this }
) = add( builder( TestResource.create(logicalName = logicalName ?: allocateLogicalName("VPC"), cidrBlock = cidrBlock, dependsOn = dependsOn ?: currentDependees, condition = condition, metadata = metadata, creationPolicy = creationPolicy, updatePolicy = updatePolicy, deletionPolicy = deletionPolicy) ).build() )

data class TestResource(
        @JsonIgnore
        override val logicalName: String,
        val cidrBlock: Value<String>,
        val enableDnsHostnames: Value<Boolean>? = null,
        @JsonIgnore
        override val dependsOn: List<String>? = null,
        @JsonIgnore
        override val condition: String? = null,
        @JsonIgnore
        override val metadata: Value<JsonNode>? = null,
        @JsonIgnore
        override val creationPolicy: CreationPolicy? = null,
        @JsonIgnore
        override val updatePolicy: UpdatePolicy? = null,
        @JsonIgnore
        override val deletionPolicy: String? = null
) : KloudResource<String>(kloudResourceType = "AWS::TestResource", logicalName = logicalName, dependsOn = dependsOn, condition = condition, metadata = metadata) {
    fun DefaultSecurityGroup() = Att<String>(logicalName, Value.Of("DefaultSecurityGroup"))

    class Builder(
            val logicalName: String,
            val cidrBlock: Value<String>,
            val dependsOn: List<String>? = null,
            val condition: String? = null,
            val metadata: Value<JsonNode>? = null,
            val creationPolicy: CreationPolicy? = null,
            val updatePolicy: UpdatePolicy? = null,
            val deletionPolicy: String? = null
    ) {
        var enableDnsHostnames: Value<Boolean>? = null

        fun enableDnsHostnames(enableDnsHostnames: Boolean) = also { it.enableDnsHostnames = Value.Of(enableDnsHostnames) }

        fun enableDnsHostnames(enableDnsHostnames: Value<Boolean>) = also { it.enableDnsHostnames = enableDnsHostnames }


        fun build() = TestResource( logicalName, cidrBlock = cidrBlock, enableDnsHostnames = enableDnsHostnames,  dependsOn = dependsOn, condition = condition, metadata = metadata, creationPolicy = creationPolicy, updatePolicy = updatePolicy, deletionPolicy = deletionPolicy)
    }
    companion object {
        fun create(
                logicalName: String,
                cidrBlock: Value<String>,
                dependsOn: List<String>? = null,
                condition: String? = null,
                metadata: Value<JsonNode>? = null,
                creationPolicy: CreationPolicy? = null,
                updatePolicy: UpdatePolicy? = null,
                deletionPolicy: String? = null
        ) = Builder(logicalName = logicalName, cidrBlock = cidrBlock, dependsOn = dependsOn, condition = condition, metadata = metadata, creationPolicy = creationPolicy, updatePolicy = updatePolicy, deletionPolicy = deletionPolicy)
    }
}