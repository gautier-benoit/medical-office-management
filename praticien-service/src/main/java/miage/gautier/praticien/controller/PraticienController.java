package miage.gautier.praticien.controller;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import miage.gautier.praticien.model.Praticien;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO describe this class using @Api annotation from swagger
@Tag(name = "Praticien Management System", description = "Operations pertaining to praticien")
// TODO this is a Rest Controller
@RestController
@RequestMapping("/api/praticiens")
public class PraticienController {

    // TODO create praticien List : name, class, country
    private List<Praticien> praticiens = new ArrayList<>();

    public PraticienController() {
        Praticien praticien1 = new Praticien();
        praticien1.setName("John");
        praticien1.setCountry("USA");
        praticien1.setSpeciality("Generalist");

        Praticien praticien2 = new Praticien();
        praticien2.setName("Jane");
        praticien2.setCountry("UK");
        praticien2.setSpeciality("Dentist");

        praticiens.add(praticien1);
        praticiens.add(praticien2);
    }

    // TODO add http response for getPraticien operation : 200 / 401 / 403 / 404 (use ApiResponses annotation)
    @Operation(summary = "View a list of available praticiens")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    // TODO endpoint getPraticien (return student List)
    @RequestMapping(value = "/findPraticiens", method = RequestMethod.GET)
    public List<Praticien> getPraticiens() {
        return praticiens;
    }

    // TODO Get Praticien by name
    // TODO describe this endpoint using swagger
    @Operation(summary = "Search a praticien with a name")
    @GetMapping(value = "/findPraticienDetail/{name}")
    public Praticien getPraticien(@PathVariable(value = "name") String name) {
        return praticiens.stream()
                .filter(praticien -> praticien.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // TODO get Praticien by country
    @Operation(summary = "Search praticiens by country")
    @GetMapping(value = "/findPraticienDetailsByCountry/{country}")
    public List<Praticien> getPraticienByCountry(@PathVariable(value = "country") String country) {

        // TODO impl this method  to getPraticienByCountry
        return praticiens.stream()
                .filter(praticien -> praticien.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    // TODO get Praticien by speciality
    @Operation(summary = "Search praticiens by speciality")
    @GetMapping(value = "/findPraticienDetailsBySpeciality/{speciality}")
    public List<Praticien> getPraticienBySpeciality(@PathVariable(value = "speciality") String speciality) {

        // TODO impl this method  to getPraticienBySpeciality
        return praticiens.stream()
                .filter(praticien -> praticien.getSpeciality().equalsIgnoreCase(speciality))
                .collect(Collectors.toList());
    }


}