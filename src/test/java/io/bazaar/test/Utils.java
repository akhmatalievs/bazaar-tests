package io.bazaar.test;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import io.netty.util.internal.StringUtil;


public class Utils
{
    private static  String email;
    private  static String password;

    public static String getEmail(){
        if( StringUtil.isNullOrEmpty( email ) ){
            readConfig();
        }

        return email;
    }

    public static String getPassword(){
        if( StringUtil.isNullOrEmpty( password ) ){
            readConfig();
        }

        return password;
    }
    private static  void readConfig() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = new FileInputStream( "config.properties" );
            // Load a properties file
            prop.load(input);
            // Get the property value and store
            email = prop.getProperty("email");
            password = prop.getProperty("password");
        } catch ( IOException ex) {
            ex.printStackTrace();
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
