package ru.grabovsky.recordkeeping.notification.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory
import org.springframework.kafka.support.converter.RecordMessageConverter
import org.springframework.kafka.support.converter.StringJsonMessageConverter
import org.springframework.kafka.support.mapping.DefaultJackson2JavaTypeMapper
import org.springframework.kafka.support.mapping.Jackson2JavaTypeMapper
import org.springframework.kafka.support.serializer.JsonDeserializer
import ru.grabovsky.recordkeeping.api.notification.SimpleTextEmailMessage


@EnableKafka
@Configuration
class KafkaConsumerConfig(
    @Value("\${kafka.server}")
    private val bootstrapAddress: String,
    @Value("\${kafka.group-id}")
    private val groupId: String
) {


    @Bean
    fun consumerFactory(): ConsumerFactory<String, SimpleTextEmailMessage> {
        val props: MutableMap<String, Any?> = mutableMapOf()

        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.GROUP_ID_CONFIG] = groupId
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[JsonDeserializer.USE_TYPE_INFO_HEADERS] = true;
        props[JsonDeserializer.TYPE_MAPPINGS] =
            "simple:ru.grabovsky.recordkeeping.api.notification.SimpleTextEmailMessage, html:ru.grabovsky.recordkeeping.api.notification.HTMLEmailMessage";
            return DefaultKafkaConsumerFactory(
                props, StringDeserializer(), JsonDeserializer(
                    SimpleTextEmailMessage::class.java, false
                )
            )
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, SimpleTextEmailMessage> {
        val factory =
            ConcurrentKafkaListenerContainerFactory<String, SimpleTextEmailMessage>()
        factory.consumerFactory = consumerFactory()
        return factory
    }

    @Bean
    fun multiTypeConverter(): RecordMessageConverter {
        val converter = StringJsonMessageConverter()
        val typeMapper = DefaultJackson2JavaTypeMapper()
        typeMapper.typePrecedence = Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID
        typeMapper.addTrustedPackages("ru.grabovsky.recordkeeping.api.notification")
//        val mappings: MutableMap<String, Class<*>> = HashMap()
//        mappings["simple"] = SimpleTextEmailMessage::class.java
//        mappings["html"] = HTMLEmailMessage::class.java
//        typeMapper.idClassMapping = mappings
        converter.typeMapper = typeMapper
        return converter
    }

    @Bean
    fun multiTypeConsumerFactory(): ConsumerFactory<String, Any> {
        val props = mutableMapOf<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapAddress
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        return DefaultKafkaConsumerFactory(props)
    }

    @Bean
    fun multiTypeKafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, Any> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, Any>()
        factory.consumerFactory = multiTypeConsumerFactory()
        factory.setRecordMessageConverter(multiTypeConverter())
        return factory
    }
}