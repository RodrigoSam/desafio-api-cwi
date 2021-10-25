package br.com.desafioapi.utils;

public class Utils {
    public static String getSchemaBasePath(String pack,String nameSchema){

        return System.getProperty("user.dir")
                + "/src/test/java/br/com/desafioapi/tests/"
                + pack
                + "/schema/"
                + nameSchema
                + ".json";
    }



}
