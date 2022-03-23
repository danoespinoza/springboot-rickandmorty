package cl.danoespinoza.rickandmorty.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import cl.danoespinoza.rickandmorty.pojo.Character;
import cl.danoespinoza.rickandmorty.pojo.Location;
import io.micrometer.core.instrument.util.StringUtils;

@Service
public class CharacterServiceImpl implements CharacterService {

	@Autowired
    private RestTemplate restTemplate;
	
	@Autowired
    private HttpHeaders httpHeaders;
	
	@Value("${rickandmorty.character-endpoint}")
	private String characterEndpoint;
	
	@PostConstruct
    private void postConstruct() {
		httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
    }
	
	@Override
    public Character getCharacterById(Integer id) {
		HttpEntity<Object> entity = new HttpEntity<Object>(httpHeaders);
		
        ResponseEntity<Character> characterResponse = restTemplate.exchange(
        		characterEndpoint + id, HttpMethod.GET, entity, Character.class);
        
        Character character = characterResponse.getBody();
        character.setEpisodeCount(character.getEpisode().size());
        
        if (character.getOrigin() != null && StringUtils.isNotEmpty(character.getOrigin().getUrl())) {
        	ResponseEntity<Location> locationResponse = restTemplate.exchange(
            		character.getOrigin().getUrl(), HttpMethod.GET, entity, Location.class);
            
            Location location = locationResponse.getBody();
            
            character.getOrigin().setId(location.getId());
            character.getOrigin().setDimension(location.getDimension());
            character.getOrigin().setResidents(location.getResidents());
        }
        
        return character;
    }

}
