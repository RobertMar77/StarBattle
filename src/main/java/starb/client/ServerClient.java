package starb.client;

import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;
import java.util.Scanner;

/**
 * Class client will use to connect to server (not in use right now).
 */
public class ServerClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ServerClient() {
        this.restTemplate = new RestTemplate();
        this.baseUrl = "http://localhost:3390/";
    }
    public int[][] getLayout(int puzzleLevel) {
        ResponseEntity<int[][]> response = restTemplate.getForEntity(baseUrl + "puzzles/" + puzzleLevel + "/layout", int[][].class);
        return response.getBody();
    }

    public int[][] getAnswer(int puzzleLevel) {
        ResponseEntity<int[][]> response = restTemplate.getForEntity(baseUrl + "puzzles/" + puzzleLevel + "/answer", int[][].class);
        return response.getBody();
    }

    /*
    * Gets the userID from the UserID file. If the file has an invalid value/is empty it will return ""
    * @ return  UserID  taken from the file if that UserID is valid otherwise returns ""
     */
    public String getUserID(){
        String UserID = "";
        try {
            File file = new File("src/main/java/starb/client/UserID.txt");
            Scanner scanner =new Scanner(file);
            while(scanner.hasNextLine()){
                UserID=scanner.nextLine();
            }
//            System.out.println("read: "+ UserID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            // Open a connection
            HttpURLConnection connection;
            URL url = new URL(baseUrl+"users/"+ UserID);
            connection = (HttpURLConnection) url.openConnection();
            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            // Read the response content if needed
            if (responseCode != HttpURLConnection.HTTP_OK) {
                UserID = "";   //incorrect UserID
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        System.out.println("User ID: "+UserID);

        return UserID;
    }


    public String postUserID(){
        ResponseEntity<String> response= restTemplate.postForEntity(baseUrl + "users", null, String.class);

        String UserID = response.getBody();
        try (FileWriter writer = new FileWriter("src/main/java/starb/client/UserID.txt")) {
            writer.write(UserID);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println("Post User ID: "+UserID);

        return UserID;
    }




    //stuff below not all working
    public int getUserLevel(String UserID) {
        ResponseEntity<Integer> response = restTemplate.getForEntity(baseUrl + "users/" + UserID + "/level", int.class);
        System.out.print(response.getBody());
        return response.getBody();
    }

    public void setUserLevel(String UserID, int level)  {
//        try {
//            URL url = new URL(baseUrl+ "users/" + UserID + "/solved");
//        HttpEntity<Integer> Entity = new HttpEntity<>(level);
//            restTemplate.exchange(baseUrl+ "users/" + UserID + "/level", HttpMethod.PATCH, Entity,Void.class);
//        } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
//        }
 //        restTemplate.patchForObject(baseUrl+ "users/" + UserID + "/level", level,Void.class);
//        ResponseEntity<Void> responseEntity = this.testRestTemplate.exchange(testUri + ENDPOINT_USER, HttpMethod.PATCH, new HttpEntity<>(userUpdate), Void.class);
//        restTemplate.exchange(U, level, Void.class);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Integer> requestEntity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        try {
            // Make the PATCH request with level in the URL
            restTemplate.exchange(baseUrl + "/users/" + UserID + "/" + level, HttpMethod.PATCH, requestEntity, Void.class);
            System.out.println("PATCH request successful");
        } catch (Exception e) {
            System.err.println("Error making PATCH request: " + e.getMessage());

        }




    }


    public int getUserSolved(String UserID) {
        ResponseEntity<Integer> response = restTemplate.getForEntity(baseUrl + "users/" + UserID + "/solved", int.class);
        return response.getBody();
    }

    public void addSolved(String UserID) {
        restTemplate.patchForObject(baseUrl + "users/" + UserID + "/solved", null,null);
    }

}
