package platform;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Platform {
	List<Descriptor> descriptors;
	
	// verifClass , permet de v�rifier si la classe est la bonne
	@Deprecated
	public static Object load(Class verifClass, String field) throws Exception {
		
		FileReader file = new FileReader("/src/appli/data.txt");
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
	public static File[] returnFiles(String path)
	{
		File repertoire = new File(path);
		File[] files = repertoire.listFiles();
		
		return files;
	}
	
	/* Parse les fichiers de config en descriptor */
	public static List<Descriptor> parseConfigFiles() throws Exception{
		List<Descriptor> list = new ArrayList<Descriptor>();
		File[] files = returnFiles("config/plugins");
		
		for( File f : files)
		{
			Properties prop = loadProperties(f.getAbsolutePath());
			String id = prop.getProperty("id");
			String className = prop.getProperty("className");
			String iface = prop.getProperty("iface");
			String  name = prop.getProperty("name");
			String dependancies = prop.getProperty("dependancies");
			String description = prop.getProperty("description");
			
			List<String> dependanciesList = dependanciesToList(dependancies);
			Descriptor current = new Descriptor(id,className,iface,name,description,dependanciesList);
			list.add(current);
		}
		
		return list;
	}
	public static List<String> dependanciesToList(String dependancies){
		List<String> dependanciesList = new ArrayList<String>();
		String[] dependanciesArray = dependancies.split("\\;");
		for(String dependancy : dependanciesArray)
		{
			dependanciesList.add(dependancy);
		}
		
		return dependanciesList;
	}
	
	
	
	
	public static List<Descriptor> getDescriptor(Class<?> choiceClass) throws Exception{
		File repertoire = new File("path du répertoire");
		File[] files=repertoire.listFiles();
		Properties prop = loadProperties("appli/data.txt");
		List<Descriptor> list = new ArrayList<Descriptor>();
		for (Entry<Object, Object> entry: prop.entrySet()) {
			String[] parts = entry.getValue().toString().split("\\;");
			if(parts[0].equals(choiceClass.getName())) {
				
				//TODO 
				Descriptor current = new Descriptor(entry.getKey().toString(),parts[1],parts[0],"");
				list.add(current);
			}
		}
		return list;
	}
	
	public static Object loadPlugin(Descriptor descriptor) throws Exception{
		// si le plugin est d�j� charg�, on recharge pas
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
