package cl.danoespinoza.rickandmorty.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

@Configuration
@ConditionalOnClass(Gson.class)
@ConditionalOnMissingClass(value = "com.fasterxml.jackson.core.JsonGenerator")
@ConditionalOnBean(Gson.class)
public class GsonConfiguration {
	
	@Bean
	@ConditionalOnMissingBean
    public Gson gson() {
        return new GsonBuilder()
        		.excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
