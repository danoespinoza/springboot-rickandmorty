package cl.danoespinoza.rickandmorty.pojo;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Location implements Serializable {

	private static final long serialVersionUID = -2734442255942725062L;

	private transient int id;

	@Expose
	private String name;

	private String type;

	@Expose
	private String url;

	@Expose
	private String dimension;

	@Expose
	private List<String> residents;

	public Location() {
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDimension() {
		return dimension;
	}

	public void setDimension(String dimension) {
		this.dimension = dimension;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<String> getResidents() {
		return residents;
	}

	public void setResidents(List<String> residents) {
		this.residents = residents;
	}

	@Override
	public String toString() {
		return "Location [name=" + name + ", type=" + type + ", dimension=" + dimension + ", url=" + url
				+ ", residents=" + residents + "]";
	}
}
