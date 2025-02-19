package miage.gautier.praticien.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Praticien details")
public class Praticien {

    @ApiModelProperty(notes = "Praticien name")
    private String name;

    @ApiModelProperty(notes = "Praticien country")
    private String country;

    @ApiModelProperty(notes = "Praticien speciality")
    private String speciality;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}