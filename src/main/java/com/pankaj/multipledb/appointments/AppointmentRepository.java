package com.pankaj.multipledb.appointments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Profile("appointments")
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

}
