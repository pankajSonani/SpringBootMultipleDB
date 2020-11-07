package com.pankaj.multipledb.admissions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
//@Profile("Admission")
public interface AdmissionRepository extends JpaRepository<Admission, Integer> {
}
