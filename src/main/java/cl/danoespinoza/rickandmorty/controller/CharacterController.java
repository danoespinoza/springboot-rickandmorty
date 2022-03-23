package cl.danoespinoza.rickandmorty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.danoespinoza.rickandmorty.pojo.Character;
import cl.danoespinoza.rickandmorty.service.CharacterService;

@RestController
@RequestMapping(path = "/api")
public class CharacterController {

    @Autowired
    private CharacterService characterService;
    
    @GetMapping(value = "/character/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> getCharacterById(@PathVariable(name = "id", required = true) Integer id) {
        Character character = characterService.getCharacterById(id);
        return new ResponseEntity<>(character, HttpStatus.OK);
    }
}
