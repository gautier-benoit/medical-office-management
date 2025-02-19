package miage.gautier.patient.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Patient details")
public class Patient {

    @ApiModelProperty(notes = "Patient name")
    private String name;

    @ApiModelProperty(notes = "Patient country")
    private String country;


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
}