package com.mycompany.primerjuego2d.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

public class APIConnect {

    public static String getFromAPI(String API) throws IOException{
        
        HttpURLConnection connection = null; 
        BufferedReader br = null; 

        try{

            URL url = new URL(API); 

            connection = (HttpURLConnection) url.openConnection(); 
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // Let's set the max time of waiting to 5 seconds 
            connection.setReadTimeout(5000);

            connection.setRequestProperty("Accept", "application/json");

            int status = connection.getResponseCode(); 
            if(status != HttpURLConnection.HTTP_OK){
                throw new IOException("Error a la hora de leer los datos de Mongo!"); 
            }

            br = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8)); 

            StringBuilder sb = new StringBuilder(); 
            String linea; 
            while((linea = br.readLine())!= null){
                sb.append(linea); 
            }

            return sb.toString(); 

        }finally{
            if (br != null) {
                br.close();
            }
            if (connection != null) {
                connection.disconnect();
            }
            
        }
        
        
       
    }
    
}
