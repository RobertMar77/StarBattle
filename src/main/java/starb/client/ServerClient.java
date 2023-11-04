package starb.client;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

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
    public int[][] getLayout(String puzzleId) {
        ResponseEntity<int[][]> response = restTemplate.getForEntity(baseUrl + "puzzles/" + puzzleId + "/layout", int[][].class);
        return response.getBody();
    }

    public int[][] getAnswer(String puzzleId) {
        ResponseEntity<int[][]> response = restTemplate.getForEntity(baseUrl + "puzzles/" + puzzleId + "/answer", int[][].class);
        return response.getBody();
    }
}
