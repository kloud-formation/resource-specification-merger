package io.kloudformation

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.fasterxml.jackson.databind.ser.std.StdSerializer
import io.kloudformation.function.*

interface Value<out T>{
    @JsonSerialize(using = Of.Serializer::class)
    data class Of<T>(val value: T)
        :   Value<T>,
            Cidr.Value<T>,
            Att.Value<T>,
            Select.IndexValue<T>,
            Select.ObjectValue<T>,
            GetAZs.Value<T>,
            ImportValue.Value<T>,
            Split.Value<T>{
        class Serializer: StdSerializer<Value.Of<*>>(Value.Of::class.java){
            override fun serialize(item: Value.Of<*>, generator: JsonGenerator, provider: SerializerProvider) {
                generator.writeObject(item.value)
            }
        }
    }
}