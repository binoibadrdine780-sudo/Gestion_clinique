package com.example.clinicalconsultations;

import com.example.clinicalconsultations.model.Consultation;
import com.example.clinicalconsultations.model.Doctor;
import com.example.clinicalconsultations.model.Patient;
import com.example.clinicalconsultations.repository.ConsultationRepository;
import com.example.clinicalconsultations.repository.DoctorRepository;
import com.example.clinicalconsultations.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class ClinicalConsultationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClinicalConsultationApplication.class, args);
    }

    @Bean
    CommandLineRunner loadSampleData(PatientRepository patientRepository,
                                     DoctorRepository doctorRepository,
                                     ConsultationRepository consultationRepository) {
        return args -> {
            // Creating a few patients for testing quickly.
            Patient patient1 = new Patient("Sara Ali", "sara@email.com");
            Patient patient2 = new Patient("Youssef Karim", "youssef@email.com");
            patientRepository.save(patient1);
            patientRepository.save(patient2);

            // Creating doctors.
            Doctor doctor1 = new Doctor("Dr. Nadia", "Cardiology");
            Doctor doctor2 = new Doctor("Dr. Hamid", "Dermatology");
            doctorRepository.save(doctor1);
            doctorRepository.save(doctor2);

            // Linking each consultation with one patient and one doctor.
            consultationRepository.save(new Consultation(new Date(), patient1, doctor1));
            consultationRepository.save(new Consultation(new Date(), patient2, doctor2));
            consultationRepository.save(new Consultation(new Date(), patient1, doctor2));
        };
    }
}
