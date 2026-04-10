package com.example.clinicalconsultations.repository;

import com.example.clinicalconsultations.model.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
}
