package platform;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Platform {
	List<Descriptor> descriptors;
	
	public static File[] returnFiles(String path)
	{
		File repertoire = new File(path);
		File[] files = repertoire.listFiles();
		
		return files;
	}
	
	/* Parse les fichiers de config en descriptor */
	public static List<Descriptor> parseConfigFiles() throws Exception{
		List<Descriptor> list = new ArrayList<Descriptor>();
		File[] files = returnFiles("src/config/plugins");
		
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
	
	
	public static Object loadPlugin(Descriptor descriptor) throws Exception{
		// si le plugin est deja charge on recharge pas
		
		if(descriptor.getStatut() != "loaded") {
			Class <?> classe ;
			Class <?> classeInterface ;
			
			try {
				classe= Class.forName(descriptor.getClassName());
				try {
					classeInterface =Class.forName(descriptor.getIface());
					
					Object  o = classe.newInstance();	;
					if(classeInterface.isInstance(o)) {
						// on garde le plugin charger pour pouvoir le recuperer plutot que de le charger une seconde fois
						descriptor.setInstance(o); 
						descriptor.setStatut("loaded");
						System.out.println("Plugin "+descriptor.getName()+" charg�");
						return o;
					}else {
						descriptor.setStatut("fail_implement");
						System.out.println("Erreur lors du chargement du plugin "+descriptor.getName()+", il n'implemente pas l'interface "+ descriptor.getIface() );
						return null;
					}

				} catch (ClassNotFoundException e) {
					descriptor.setStatut("fail_interface");
					return null;
				}
				
			} catch (ClassNotFoundException e) {
				descriptor.setStatut("fail_class");
				return null;
			}	
					
		}else if (descriptor.getStatut() == "loaded") {
			System.out.println("Plugin "+descriptor.getName()+" d�j� charg�");
			return descriptor.getInstance();
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
