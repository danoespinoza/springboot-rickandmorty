package cl.danoespinoza.rickandmorty.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
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
	
	@Override
    public Character getCharacterById(Integer id) {
		HttpEntity<Object> entity = new HttpEntity<Object>(httpHeaders);
		
        ResponseEntity<Character> characterResponse = restTemplate.exchange(
        		characterEndpoint + id, HttpMethod.GET, entity, Character.class);
        
        Character character = characterResponse.getBody();
        character.setEpisodeCount(character.getEpisode().size());
        
        if (character.getOrigin() != null && StringUtils.isNotEmpty(character.getOrigin().getUrl())) {
            Location location = this.getLocationByUrl(character.getOrigin().getUrl());
            
            character.getOrigin().setId(location.getId());
            character.getOrigin().setDimension(location.getDimension());
            character.getOrigin().setResidents(location.getResidents());
        }
        
        return character;
    }

    @Override
    public Location getLocationByUrl(String url) {
        HttpEntity<Object> entity = new HttpEntity<Object>(httpHeaders);

        ResponseEntity<Location> locationResponse = restTemplate.exchange(
            url, HttpMethod.GET, entity, Location.class);
            
        return locationResponse.getBody();
    }
}
