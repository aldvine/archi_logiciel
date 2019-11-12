package platform;

public class Descriptor {
	private String path;
	private String iface; // interface
	private String name;
	private String description;
	private Boolean loaded; 
	

	public Descriptor(String name, String path,String iface, String description) {
		super();
		this.path = path;
		this.iface = iface;
		this.name = name;
		this.loaded=false;
		this.setDescription(description);
	}

	@Override
	public String toString() {
		return "Descriptor [path=" + path + ", name=" + name + ", description=" + description + "]";
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
		return path;
	}

	public void setPath(String path) {
		this.path = path;
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

	public Boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(Boolean loading) {
		this.loaded = loading;
	}

}
