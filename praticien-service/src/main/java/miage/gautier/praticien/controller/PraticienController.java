package miage.gautier.praticien.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import miage.gautier.praticien.model.Praticien;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// TODO describe this class using @Api annotation from swagger
@Api(value = "Praticien Management System", description = "Operations pertaining to praticien")
// TODO this is a Rest Controller
@RestController
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
    @ApiOperation(value = "View a list of available praticiens", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })

    // TODO endpoint getPraticien (return student List)
    @RequestMapping(value = "/getPraticien", method = RequestMethod.GET)
    public List<Praticien> getStudents() {
        return praticiens;
    }

    // TODO Get Praticien by name
    // TODO describe this endpoint using swagger
    @ApiOperation(value = "Search a praticien with a name", response = Praticien.class)
    @RequestMapping(value = "/getPraticien/{name}")
    public Praticien getPraticien(@PathVariable(value = "name") String name) {
        return praticiens.stream()
                .filter(praticien -> praticien.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    // TODO get Praticien by country
    @ApiOperation(value = "Search praticiens by country", response = List.class)
    @RequestMapping(value = "/getPraticienByCountry/{country}")
    public List<Praticien> getStudentByCountry(@PathVariable(value = "country") String country) {

        // TODO impl this method  to getPraticienByCountry
        return praticiens.stream()
                .filter(praticien -> praticien.getCountry().equalsIgnoreCase(country))
                .collect(Collectors.toList());
    }

    // TODO get Praticien by speciality
    @ApiOperation(value = "Search praticiens by speciality", response = List.class)
    @RequestMapping(value = "/getPraticienBySpeciality/{speciality}")
    public List<Praticien> getStudentBySpeciality(@PathVariable(value = "speciality") String speciality) {

        // TODO impl this method  to getPraticienBySpeciality
        return praticiens.stream()
                .filter(praticien -> praticien.getSpeciality().equalsIgnoreCase(speciality))
                .collect(Collectors.toList());
    }


}