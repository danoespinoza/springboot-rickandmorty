package cl.danoespinoza.rickandmorty.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;

import cl.danoespinoza.rickandmorty.pojo.Character;

@SpringBootTest 
@AutoConfigureMockMvc
public class CharacterControllerTest {

	@Autowired 
    private MockMvc mockMvc;
	
	@Autowired
	private Gson gson;
	
	@Test
	void whenGetCharacterWithOrigin_thenAccept() throws Exception {
		String uri = "/api/character/1";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		
		String content = mvcResult.getResponse().getContentAsString();
		
		Character character = gson.fromJson(content, Character.class);
		assertTrue(character.getId() == 1);
		
		assertFalse(character.getOrigin().getName().equals("unknown"));
	}

	@Test
	void whenGetCharacterWithoutOrigin_thenAccept() throws Exception {
		String uri = "/api/character/8";

		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(HttpStatus.OK.value(), status);
		
		String content = mvcResult.getResponse().getContentAsString();
		
		Character character = gson.fromJson(content, Character.class);
		assertTrue(character.getId() == 8);
		
		assertTrue(character.getOrigin().getName().equals("unknown"));
	}
}
