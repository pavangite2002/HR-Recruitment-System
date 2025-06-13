//package com.example.employeemodel.dto;
//
//import com.fasterxml.jackson.annotation.JsonInclude;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.SerializationFeature;
//import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
//import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
//import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
//
//import java.time.format.DateTimeFormatter;
//
//@Configuration
//@EnableCaching
//public class BeanConfig {
//
//    @Bean
//    public ObjectMapper objectMapper() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
//        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
//        return objectMapper;
//
//    }
//    @Bean
//    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
//        Jackson2ObjectMapperBuilder builder =
//                new Jackson2ObjectMapperBuilder()
//                        .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//                        .serializers(
//                                new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")))
//                        .serializationInclusion(JsonInclude.Include.NON_NULL);
//        return new MappingJackson2HttpMessageConverter(builder.build());
//    }
//}
//
