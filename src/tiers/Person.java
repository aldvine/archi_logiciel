package tiers;

public class Person {
	String nom;
	String ville;
	Integer age;
	
	public Person(String nom,String ville, int age) {
		this.nom=nom; 
		this.ville=ville; 
		this.age = age;
		// TODO Auto-generated constructor stub
	}
	
		
	public Person() {
		super();
	}



	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getVille() {
		return ville;
	}
	@Override
	public String toString() {
		return "Person [nom=" + nom + ", ville=" + ville + ", age=" + age + "]";
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	
}
