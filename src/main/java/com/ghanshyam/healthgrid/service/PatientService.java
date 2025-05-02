package com.ghanshyam.healthgrid.service;

import com.ghanshyam.healthgrid.dto.PatientRequestDTO;
import com.ghanshyam.healthgrid.dto.PatientResponseDTO;
import com.ghanshyam.healthgrid.mapper.PatientMapper;
import com.ghanshyam.healthgrid.model.Patient;
import com.ghanshyam.healthgrid.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Patient newPatient = patientRepository.save(PatientMapper.toDTO(patientRequestDTO));
        return PatientMapper.toDTO(newPatient);
    }

}
