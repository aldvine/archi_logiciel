package platform;

import java.util.List;

public class Descriptor {
	private String id; 
	private String className;
	private String iface; // interface
	private String name;
	private String description;
	private String statut;
	private List<String>  dependancies; 
	

	public Descriptor(String id, String className, String iface, String name, String description,
			List<String> dependancies) {
		super();
		this.id = id;
		this.className = className;
		this.iface = iface;
		this.name = name;
		this.description = description;
		this.dependancies = dependancies;
	}

	@Override
	public String toString() {
		return "Descriptor [path=" + className + ", name=" + name + ", description=" + description + "]";
	}

	public Descriptor() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return className;
	}

	public void setPath(String path) {
		this.className = path;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIface() {
		return iface;
	}

	public void setIface(String iface) {
		this.iface = iface;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}



}
