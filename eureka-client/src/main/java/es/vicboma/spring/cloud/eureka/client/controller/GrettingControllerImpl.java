package es.vicboma.spring.cloud.eureka.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;
import com.netflix.discovery.EurekaClient;

@RestController
public class GrettingControllerImpl implements GreetingController {

    private final EurekaClient eurekaClient;

    @Autowired
    public GrettingControllerImpl(final EurekaClient eurekaClient){
        this.eurekaClient = eurekaClient;
    }

    @Value("${spring.application.name}")
    private String appName;

    @Value("${server.port}")
    private String portNumber;

    @Override
    public String greeting() {
        System.out.println("Request received on port number " + portNumber);
        return String.format("Hello from '%s with Port Number %s'!", eurekaClient.getApplication(appName)
            .getName(), portNumber);
    }
}
