package platform;

import java.util.List;

public class Descriptor {
	private String id; 
	private String className;
	private String iface; // interface
	private String name;
	private String description;
	private String statut = "unload"; // loaded / fail / unload
	private List<String>  dependancies; 
	private Object instance; // instance du plugin si charg�
	

	public Descriptor(String id, String className, String iface, String name, String description,List<String> dependancies) {
		super();
		this.setId(id);
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

	public String getClassName() {
		return className;
	}

	public void setClassName(String path) {
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

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}



}
