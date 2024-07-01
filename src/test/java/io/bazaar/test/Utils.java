package io.bazaar.test;

public class Utils
{
    private static  String email;
    private  static String password;

    public static String getEmail(){
        if( email == null || email.isEmpty() ){
            readConfig();
        }

        return email;
    }

    public static String getPassword(){
        if(  password == null || password.isEmpty()){
            readConfig();
        }

        return password;
    }
    private static  void readConfig() {
        email = System.getenv("BAZAAR_USER_EMAIL");
        password = System.getenv("BAZAAR_USER_PASSWORD");
    }
}
