package cl.danoespinoza.rickandmorty.controller;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.danoespinoza.rickandmorty.pojo.Character;
import cl.danoespinoza.rickandmorty.service.CharacterService;

@RestController
@Validated
@RequestMapping(path = "/api")
public class CharacterController {

    @Autowired
    private CharacterService characterService;
    
    @GetMapping(value = "/character/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Character> getCharacterById(@PathVariable(name = "id") @NotNull @NotBlank @Digits(fraction = 0, integer = 10, message= "Character id must be a number") String id) throws Exception {
        try {
            Character character = characterService.getCharacterById(Integer.valueOf(id));
        return new ResponseEntity<>(character, HttpStatus.OK);
        } catch (NumberFormatException e) {
            throw e;
        }
    }
}
