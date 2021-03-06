package com.pankaj.multipledb;

import com.pankaj.multipledb.admissions.Admission;
import com.pankaj.multipledb.admissions.AdmissionRepository;
import com.pankaj.multipledb.appointments.Appointment;
import com.pankaj.multipledb.appointments.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
@RestController
public class MultipledbApplication {
    public static void main(String[] args) {
        SpringApplication.run(MultipledbApplication.class, args);
    }

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    /******************************************************************************
     * ENDPOINTS FOR ADMISSION SERVICE
     ******************************************************************************/
    @PostConstruct
    public void addAdmissionsData() {
        admissionRepository.saveAll(Stream.of(
                new Admission(101, "A man needing surgery"),
                new Admission(102, "Nursing mum and baby"))
                .collect(Collectors.toList()));
    }
    @GetMapping("/getAdmissions")
    public List<Admission> getAdmissions(){
        return admissionRepository.findAll();
    }

    /******************************************************************************
     * ENDPOINTS FOR APPOINTMENTS SERVICE
     ******************************************************************************/
    @PostConstruct
    public void addAppointmentsData() {
        appointmentRepository.saveAll(Stream.of(
                new Appointment(101, "Teh first appointment ever"),
                new Appointment(102, "Appointment with a Dentist"))
                .collect(Collectors.toList()));
    }
    @GetMapping("/getAppointments")
    public List<Appointment> getAppointments(){
        return appointmentRepository.findAll();
    }

}
