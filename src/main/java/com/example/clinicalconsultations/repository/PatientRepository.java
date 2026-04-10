package com.example.clinicalconsultations.repository;

import com.example.clinicalconsultations.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
