package appli;

public class Character {
	String nom;
	Integer sante;
	Integer force;
	Integer intelligence;
	Integer agilite;
	String origin;
	String status;
		
	public Character(String nom, Integer sante, Integer force, Integer intelligence, Integer agilite, String origin,
			String status) {
		super();
		this.nom = nom;
		this.sante = sante;
		this.force = force;
		this.intelligence = intelligence;
		this.agilite = agilite;
		this.origin = origin;
		this.status = status;
	}

	public Character() {
		super();
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getSante() {
		return sante;
	}

	public void setSante(Integer sante) {
		this.sante = sante;
	}

	public Integer getForce() {
		return force;
	}

	public void setForce(Integer force) {
		this.force = force;
	}

	public Integer getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(Integer intelligence) {
		this.intelligence = intelligence;
	}

	public Integer getAgilite() {
		return agilite;
	}

	public void setAgilite(Integer agilite) {
		this.agilite = agilite;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Character [nom=" + nom + ", sante=" + sante + ", force=" + force + ", intelligence=" + intelligence
				+ ", agilite=" + agilite + ", origin=" + origin + ", status=" + status + "]";
	}
}
