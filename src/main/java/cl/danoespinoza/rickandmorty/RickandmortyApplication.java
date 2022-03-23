package cl.danoespinoza.rickandmorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = { JacksonAutoConfiguration.class })
public class RickandmortyApplication {

	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public HttpHeaders httpHeaders() {
        return new HttpHeaders();
    }
	
	public static void main(String[] args) {
		SpringApplication.run(RickandmortyApplication.class, args);
	}

}
