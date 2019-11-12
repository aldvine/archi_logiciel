package platform;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	
	public static List<Descriptor> getDescriptor(Class<?> choiceClass) throws Exception{
		Properties prop = loadProperties("src/appli/data.txt");
		List<Descriptor> list = new ArrayList<Descriptor>();
		for (Entry<Object, Object> entry: prop.entrySet()) {
			String[] parts = entry.getValue().toString().split("\\;");
			if(parts[0].equals(choiceClass.getName())) {
				Descriptor current = new Descriptor(entry.getKey().toString(),parts[1],parts[0],"");
				list.add(current);
			}
		}
		return list;
	}
	
	public static Object loadPlugin(Descriptor descriptor) throws Exception{
		// si le plugin est déjà chargé, on recharge pas
		if(!descriptor.getLoaded()) {
			Class <?> classe =	Class.forName(descriptor.getPath());
			Class <?> classeInterface =	Class.forName(descriptor.getIface());
			Object  o = classe.newInstance();	
			if(classeInterface.isInstance(o)) {
				descriptor.setLoaded(true);
				return o;
			}else {
				return null;
			}
		}
		return null;
	}
	
	public static Properties loadProperties(String filePath) throws Exception{
		FileReader file = new FileReader(filePath);
		Properties prop = new Properties();
		prop.load(file);
		return prop;
	}
	
}
