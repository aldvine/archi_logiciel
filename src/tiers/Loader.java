package tiers;

import java.io.FileReader;
import java.util.Properties;

public class Loader {

	// verifClass , permet de vérifier si la classe est la bonne
	@Deprecated
	public static Object load(Class verifClass, String field) throws Exception {
		
		FileReader file = new FileReader("src/appli/data.txt");
		Properties prop = new Properties();
		prop.load(file);
		
		String className = prop.getProperty(field);
		Class <?> classe =	Class.forName(className);
		Object  o = classe.newInstance();	
		if(verifClass.isInstance(o)) {
			return o;
		}else {
			return null;
		}
	}
}
