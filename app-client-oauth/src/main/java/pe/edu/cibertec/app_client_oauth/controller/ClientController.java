package pe.edu.cibertec.app_client_oauth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController("/client")
public class ClientController {

    private final OAuth2RestTemplate restTemplate;

    @GetMapping("/call-api")
    public ResponseEntity<?> callApi() {
        String url = "http://localhost:9000/protected-endpoint";
        return restTemplate.getForEntity(url, String.class);
    }
}
