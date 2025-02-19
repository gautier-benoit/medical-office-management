package miage.gautier.patient.service;

import miage.gautier.patient.model.Patient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PatientService {

    private final List<Patient> patients = new ArrayList<>();
    private final AtomicLong counter = new AtomicLong();

    public PatientService() {
        patients.add(new Patient(counter.incrementAndGet(), "Dupont", "Jean", "jean.dupont@email.com", "0123456789", "1980-01-01"));
        patients.add(new Patient(counter.incrementAndGet(), "Martin", "Marie", "marie.martin@email.com", "0123456788", "1985-05-15"));
        patients.add(new Patient(counter.incrementAndGet(), "Bernard", "Paul", "paul.bernard@email.com", "0123456787", "1990-10-30"));
    }

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Optional<Patient> getPatientById(Long id) {
        return patients.stream()
                .filter(patient -> patient.getId().equals(id))
                .findFirst();
    }

    public Patient createPatient(Patient patient) {
        patient.setId(counter.incrementAndGet());
        patients.add(patient);
        return patient;
    }

    public void deletePatient(Long id) {
        patients.removeIf(patient -> patient.getId().equals(id));
    }

    public Optional<Patient> updatePatient(Long id, Patient updatedPatient) {
        for (int i = 0; i < patients.size(); i++) {
            if (patients.get(i).getId().equals(id)) {
                updatedPatient.setId(id);
                patients.set(i, updatedPatient);
                return Optional.of(updatedPatient);
            }
        }
        return Optional.empty();
    }
}