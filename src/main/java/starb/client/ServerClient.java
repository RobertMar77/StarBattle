package starb.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            // Open a connection
            HttpURLConnection connection;
            URL url = new URL(baseUrl+"users/"+ UserID);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            if (responseCode != HttpURLConnection.HTTP_OK) {
                UserID = "";   //incorrect UserID
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

        return UserID;
    }


    public int getUserLevel(String UserID) {
        ResponseEntity<Integer> response = restTemplate.getForEntity(baseUrl + "users/" + UserID + "/level", int.class);
        return response.getBody();
    }

    public void setUserLevel(String UserID, int level)  {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String jsonBody = null;
        try {
            jsonBody = mapper.writeValueAsString(level);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        java.net.http.HttpRequest req = java.net.http.HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(jsonBody))
                .uri(URI.create(baseUrl +"users/" + UserID + "/level"))
                .build();
        try {
            HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public int getUserSolved(String UserID) {
        ResponseEntity<Integer> response = restTemplate.getForEntity(baseUrl + "users/" + UserID + "/solved", int.class);
        return response.getBody();
    }

    public void addSolved(String UserID) {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        java.net.http.HttpRequest req = java.net.http.HttpRequest.newBuilder()
                .header("Content-Type", "application/json")
                .method("PATCH", HttpRequest.BodyPublishers.ofString(""))
                .uri(URI.create(baseUrl +"users/" + UserID + "/solved"))
                .build();
        try {
            HttpResponse<String> response = client.send(req, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public String getTitle(String UserID) {
        ResponseEntity<String> response = restTemplate.getForEntity(baseUrl + "users/" + UserID + "/title", String.class);
        return response.getBody();
    }

}
