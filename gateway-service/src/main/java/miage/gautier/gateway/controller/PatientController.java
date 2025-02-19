package miage.gautier.gateway.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PatientController {

    @Autowired
    RestTemplate restPatientTemplate;

    @RequestMapping(value = "/PatientDetails/{patientName}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackPatientMethod")
    public String getPraticiens(@PathVariable String patientName)
    {
        System.out.println("Getting patient details for " + patientName);

        String response = restPatientTemplate.exchange("http://patient-service/findPraticienDetail/{patientName}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, patientName).getBody();

        System.out.println("Response Body " + response);

        return "Patient Id -  " + patientName + " [ Patient Details " + response+" ]";
    }

    public String  fallbackPatientMethod(String patientName){
        return "Fallback response:: No patient details available temporarily";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restPatientTemplate() {
        return new RestTemplate();
    }
}