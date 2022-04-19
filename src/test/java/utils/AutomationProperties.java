package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.testng.Assert.fail;


public class AutomationProperties {

    private Properties properties;
    private  static AutomationProperties instance;

    public AutomationProperties() throws Exception {
        /*if(properties == null){
            properties = new Properties();
            InputStream in = AutomationProperties.class.getClassLoader().getResourceAsStream("automation.properties");
            try{
                properties.load(in);
            } catch (FileNotFoundException | NullPointerException e){
                throw new Exception("Unable to find automation properties file", e);
            } catch (IOException e){
                throw new Exception("Error parsing automation properties file", e);
            }
        }*/
    }


    public static synchronized AutomationProperties getInstance()throws Exception{
        if(instance == null){
            instance = new AutomationProperties();
        }
        return instance;
    }

    public String getString(String propertyName){
        try{
            if(null !=System.getProperty(propertyName) && !System.getProperty(propertyName).isEmpty()){
                return System.getProperty(propertyName);
            }else{
                return  properties.getProperty(propertyName);
            }
        }
        catch (Exception e){
            fail();
        }
        return null;
    }
}
