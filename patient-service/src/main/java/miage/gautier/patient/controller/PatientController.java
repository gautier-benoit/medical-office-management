package miage.gautier.patient.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import miage.gautier.patient.model.Patient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO describe this class using @Api annotation from swagger
@Api(value = "Patient Management System", description = "Operations pertaining to patient")
// TODO this is a Rest Controller
@RestController
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
    @ApiOperation(value = "View a list of available patients", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    // TODO endpoint getPatients (return student List)
    @RequestMapping(value = "/getPatients", method = RequestMethod.GET)
    public List<Patient> getPatients() {
        return patients;
    }

    // TODO Get Patien by name
    // TODO describe this endpoint using swagger
    @ApiOperation(value = "Search a patient with a name", response = Patient.class)
    @RequestMapping(value = "/getPatient/{name}")
    public Patient getPatient(@PathVariable(value = "name") String name) {
        return patients.stream()
                .filter(patient -> patient.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // TODO get Patient by country
    @ApiOperation(value = "Search patients by country", response = List.class)
    @RequestMapping(value = "/getPatientByCountry/{country}")
    public List<Patient> getPatientByCountry(@PathVariable(value = "country") String country) {

        // TODO impl this method  to getPatientByCountry
        return patients.stream()
                .filter(patient -> patient.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }
}