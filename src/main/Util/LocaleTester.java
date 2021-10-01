package main.Util;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LocaleTester {

    public static void test(){

        try{
            ResourceBundle resourceBundle = ResourceBundle.getBundle("main/Nat", Locale.getDefault());
            if (Locale.getDefault().getLanguage().equals("de")
                    ||Locale.getDefault().getLanguage().equals("es")
                    ||Locale.getDefault().getLanguage().equals("fr")){
                System.out.println(resourceBundle.getString("hello")+ " " +resourceBundle.getString("world"));
            }
        }catch(MissingResourceException e){
            System.out.println("No resource bundle found for location, reverting to US Locale");
            Locale.setDefault(Locale.US);
            ResourceBundle resourceBundle = ResourceBundle.getBundle("main/Nat", Locale.getDefault());


            System.out.println(resourceBundle.getString("hello")+" "+resourceBundle.getString("world"));

        }
    }

    public static void setLocaleAndTest(Locale locale){
        Locale.setDefault(locale);
        test();
    }
}
