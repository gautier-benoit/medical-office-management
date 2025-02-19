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
public class PatricienController {

    @Autowired
    RestTemplate restPraticienTemplate;

    @RequestMapping(value = "/PraticienDetails/{praticienName}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "fallbackPraticienMethod")
    public String getPraticiens(@PathVariable String praticienName)
    {
        System.out.println("Getting Patricien details for " + praticienName);

        String response = restPraticienTemplate.exchange("http://praticien-service/findPraticienDetail/{praticienName}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, praticienName).getBody();

        System.out.println("Response Body " + response);

        return "Patricien Id -  " + praticienName + " [ Patricien Details " + response+" ]";
    }

    public String  fallbackPraticienMethod(String praticienName){
        return "Fallback response:: No Patricien details available temporarily";
    }

    @Bean
    @LoadBalanced
    public RestTemplate restPraticienTemplate() {
        return new RestTemplate();
    }
}