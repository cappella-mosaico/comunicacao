package com.javacrud.javacrud;

import com.javacrud.services.EmployeeService;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {

    @Inject
    private EmployeeService employeeService;
    @Inject
    private KafkaService kafkaService;

    @GET
    @Produces("text/plain")
    public String hello() {
        var employee = employeeService.generateNew();
        kafkaService.notifyEvent(employee.getFirstName() + " - " + employee.getId());
        return "Hello, World! " + employee.getId();
    }
}