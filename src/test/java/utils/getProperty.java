package utils;

public class getProperty {
    public static String prop(String name){
        try{
            AutomationProperties a = new AutomationProperties();
            return a.getString(name);
        }
        catch (Exception e){
            return null;
        }
    }
}
