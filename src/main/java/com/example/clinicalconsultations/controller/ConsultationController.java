package com.example.clinicalconsultations.controller;

import com.example.clinicalconsultations.model.Consultation;
import com.example.clinicalconsultations.model.Doctor;
import com.example.clinicalconsultations.model.Patient;
import com.example.clinicalconsultations.repository.ConsultationRepository;
import com.example.clinicalconsultations.repository.DoctorRepository;
import com.example.clinicalconsultations.repository.PatientRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/consultations")
public class ConsultationController {

    private final ConsultationRepository consultationRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;

    public ConsultationController(ConsultationRepository consultationRepository,
                                  PatientRepository patientRepository,
                                  DoctorRepository doctorRepository) {
        this.consultationRepository = consultationRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consultation> getConsultationById(@PathVariable Long id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        return consultation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Consultation> createConsultation(@RequestBody Consultation consultationData) {
        // For simplicity, request should include patient.id and doctor.id
        if (consultationData.getPatient() == null || consultationData.getPatient().getId() == null
                || consultationData.getDoctor() == null || consultationData.getDoctor().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Patient> patient = patientRepository.findById(consultationData.getPatient().getId());
        Optional<Doctor> doctor = doctorRepository.findById(consultationData.getDoctor().getId());

        if (patient.isEmpty() || doctor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Consultation consultation = new Consultation();
        consultation.setDate(consultationData.getDate());
        consultation.setPatient(patient.get());
        consultation.setDoctor(doctor.get());

        return ResponseEntity.ok(consultationRepository.save(consultation));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consultation> updateConsultation(@PathVariable Long id,
                                                           @RequestBody Consultation consultationData) {
        Optional<Consultation> consultationOptional = consultationRepository.findById(id);
        if (consultationOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        if (consultationData.getPatient() == null || consultationData.getPatient().getId() == null
                || consultationData.getDoctor() == null || consultationData.getDoctor().getId() == null) {
            return ResponseEntity.badRequest().build();
        }

        Optional<Patient> patient = patientRepository.findById(consultationData.getPatient().getId());
        Optional<Doctor> doctor = doctorRepository.findById(consultationData.getDoctor().getId());

        if (patient.isEmpty() || doctor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Consultation consultation = consultationOptional.get();
        consultation.setDate(consultationData.getDate());
        consultation.setPatient(patient.get());
        consultation.setDoctor(doctor.get());
        return ResponseEntity.ok(consultationRepository.save(consultation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable Long id) {
        if (!consultationRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        consultationRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
