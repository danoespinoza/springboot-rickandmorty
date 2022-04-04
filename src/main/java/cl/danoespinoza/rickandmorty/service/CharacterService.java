package cl.danoespinoza.rickandmorty.service;

import cl.danoespinoza.rickandmorty.pojo.Character;
import cl.danoespinoza.rickandmorty.pojo.Location;

public interface CharacterService {
    public Character getCharacterById(Integer id);
    public Location getLocationByUrl(String url);
}
