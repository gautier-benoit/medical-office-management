package miage.gautier.praticien.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Paticien details")
public class Praticien {

    @ApiModelProperty(notes = "Paticien name")
    private String name;

    @ApiModelProperty(notes = "Paticien country")
    private String country;

    @ApiModelProperty(notes = "Paticien speciality")
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