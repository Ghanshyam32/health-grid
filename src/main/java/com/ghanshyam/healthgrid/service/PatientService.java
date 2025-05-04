package com.ghanshyam.healthgrid.service;

import com.ghanshyam.healthgrid.dto.PatientRequestDTO;
import com.ghanshyam.healthgrid.dto.PatientResponseDTO;
import com.ghanshyam.healthgrid.exception.EmailAlreadyExistsException;
import com.ghanshyam.healthgrid.exception.PatientNotFoundException;
import com.ghanshyam.healthgrid.mapper.PatientMapper;
import com.ghanshyam.healthgrid.model.Patient;
import com.ghanshyam.healthgrid.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class PatientService {

    private PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getPatient() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientResponseDTO> patientResponseDTOS = patients.stream().map(patient -> PatientMapper.toDTO(patient)).toList();
        return patientResponseDTOS;
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        if (patientRepository.existsByEmail(patientRequestDTO.getEmail())) {
            throw new EmailAlreadyExistsException("patient with this email already exists");
        }
        Patient newPatient = patientRepository.save(PatientMapper.toDTO(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

    public PatientResponseDTO updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findById(id).orElseThrow(() ->
                new PatientNotFoundException("Patient not found with ID: " + id));
        if (patientRepository.existsByEmailAndIDNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("patient with this email already exists");
        }
        patient.setName(patientRequestDTO.getName());
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(patient);
        return PatientMapper.toDTO(updatedPatient);
    }

    public void deletePatient(UUID id) {
        patientRepository.deleteById(id);
    }

}
