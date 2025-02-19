package miage.gautier.patient.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import miage.gautier.patient.model.Patient;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO describe this class using @Api annotation from swagger
@Tag(name = "Patient Management System", description = "Operations pertaining to patient")
// TODO this is a Rest Controller
@RestController
@RequestMapping("/api/patients")
public class PatientController {


    // TODO create patient List : name, class, country
    private List<Patient> patients = new ArrayList<>();

    public PatientController() {
        Patient patient1 = new Patient();
        patient1.setName("Quentin");
        patient1.setCountry("FR");

        Patient patient2 = new Patient();
        patient2.setName("Michel");
        patient2.setCountry("CH");

        patients.add(patient1);
        patients.add(patient2);
    }

    // TODO add http response for getPatient operation : 200 / 401 / 403 / 404 (use ApiResponses annotation)
    @Operation(summary = "View a list of available patients")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    // TODO endpoint getPatients (return student List)
    @GetMapping(value = "/findPatients")
    public List<Patient> getPatients() {
        return patients;
    }

    // TODO Get Patien by name
    // TODO describe this endpoint using swagger
    @Operation(summary = "Search a patient with a name")
    @GetMapping(value = "/findPatientDetailsByName/{name}")
    public Patient getPatient(@PathVariable(value = "name") String name) {
        return patients.stream()
                .filter(patient -> patient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // TODO get Patient by country
    @Operation(summary = "Search patients by country")
    @GetMapping(value = "/findPatientDetailsByCountry/{country}")
    public List<Patient> getPatientByCountry(@PathVariable(value = "country") String country) {

        // TODO impl this method  to getPatientByCountry
        return patients.stream()
                .filter(patient -> patient.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }
}