package co.wedevx.digitalbank.automation.ui.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//build a logic that reads config file(properties file)
public class ConfigReader {
    private static Properties properties;
    //static initializer runs block only once for whole project
    //instance runs block for every object creation
    static{
        //directory of your properties file
        String filePath= "src/test/resources/properties/digitalbank.properties";
        FileInputStream input=null;
//class enables u to read files
        try {
           input=new FileInputStream(filePath);
            properties=new Properties();
            properties.load(input) ;
        } catch (IOException e) {
            System.out.println("File not found");
        }
        finally{
            try {
                input.close();
            } catch (IOException e) {
               e.getStackTrace();
            }
        }

    }
    public static String getPropertiesValues(String key){
return properties.getProperty(key);
    }


}

