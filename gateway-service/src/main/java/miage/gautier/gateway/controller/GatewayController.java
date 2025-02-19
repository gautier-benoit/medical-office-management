package miage.gautier.gateway.controller;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
@Slf4j
@Tag(name = "Gateway API", description = "API Gateway pour la gestion du cabinet médical")
public class GatewayController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String PATIENT_SERVICE = "patient-service";
    private static final String PRACTITIONER_SERVICE = "praticien-service";

    @GetMapping("/patients/findPatients")
    @Operation(summary = "Récupérer tous les patients")
    @CircuitBreaker(name = PATIENT_SERVICE, fallbackMethod = "getAllPatientsFallback")
    public ResponseEntity<?> getPatients() {
        log.info("Requête de récupération de tous les patients");
        try {
            return restTemplate.getForEntity("http://patient-service/api/patients/findPatients", Object.class);
        } catch (RestClientException e) {
            log.error("Erreur lors de l'appel au service patient: {}", e.getMessage());
            throw e; // Relancer l'exception pour que le circuit breaker puisse l'attraper
        }
    }

    public ResponseEntity<?> getAllPatientsFallback(Exception e) {
        // Vérifiez si l'exception est due à une indisponibilité du service
        if (e instanceof ResourceAccessException) {
            return ResponseEntity.status(503).body(
                    "Le service de liste des patients est temporairement indisponible");
        }
        return ResponseEntity.status(500).body("Erreur interne du serveur");
    }


    @GetMapping("/praticiens/findPraticiens")
    @Operation(summary = "Récupérer tous les praticiens")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "getAllPraticiensFallback")
    public ResponseEntity<?> getPraticiens() {
        log.info("Requête de récupération de tous les praticiens");
        return restTemplate.getForEntity("http://praticien-service/api/praticiens/findPraticiens", Object.class);
    }

    @GetMapping("/praticiens/findPraticienDetail/{name}")
    @Operation(summary = "Récupérer un praticien par son name")
    @CircuitBreaker(name = PRACTITIONER_SERVICE, fallbackMethod = "getPraticiensByIdFallback")
    public ResponseEntity<?> getPraticien(@PathVariable String name) {
        log.info("Requête de récupération du praticien avec l'name: {}", name);
        return restTemplate.getForEntity("http://praticien-service/api/praticiens/findPraticienDetail" + "/{name}", Object.class, name);
    }

    public ResponseEntity<?> getPatientByIdFallback(String name, Exception e) {
        return ResponseEntity.status(503).body(
                "Le service de récupération du patient est temporairement indisponible");
    }

    public ResponseEntity<?> getAllPraticiensFallback(Exception e) {
        return ResponseEntity.status(503).body(
                "Le service de liste des praticiens est temporairement indisponible");
    }

    public ResponseEntity<?> getPraticiensByIdFallback(String name, Exception e) {
        return ResponseEntity.status(503).body(
                "Le service de récupération du praticien est temporairement indisponible");
    }
}