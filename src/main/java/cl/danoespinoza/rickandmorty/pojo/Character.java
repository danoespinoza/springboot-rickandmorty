package cl.danoespinoza.rickandmorty.pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Character implements Serializable {

	private static final long serialVersionUID = 6683137855027333705L;

	@Expose
	private int id;

	@Expose
	private String name;

	@Expose
	private String status;

	@Expose
	private String species;

	@Expose
	private String type;

	@Expose
	@SerializedName("episode_count")
	private int episodeCount;

	@Expose
	@SerializedName("origin")
	private Location origin;

	private transient List<String> episode;

	public Character() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSpecies() {
		return species;
	}

	public void setSpecies(String species) {
		this.species = species;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getEpisodeCount() {
		return episodeCount;
	}

	public void setEpisodeCount(int episodeCount) {
		this.episodeCount = episodeCount;
	}

	public Location getOrigin() {
		return origin;
	}

	public void setOrigin(Location origin) {
		this.origin = origin;
	}

	public List<String> getEpisode() {
		return episode;
	}

	public void setEpisode(List<String> episode) {
		this.episode = episode;
	}

}
