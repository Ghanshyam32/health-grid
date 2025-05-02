package com.ghanshyam.healthgrid.mapper;

import com.ghanshyam.healthgrid.dto.PatientRequestDTO;
import com.ghanshyam.healthgrid.dto.PatientResponseDTO;
import com.ghanshyam.healthgrid.model.Patient;

import java.time.LocalDate;

public class PatientMapper {
    public static PatientResponseDTO toDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getID().toString());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());

        return patientResponseDTO;
    }

    public static Patient toDTO(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        patient.setAddress(patientRequestDTO.getAddress());
        patient.setName(patientRequestDTO.getName());
        patient.setEmail(patientRequestDTO.getEmail());
        patient.setDateOfBirth(LocalDate.parse(patientRequestDTO.getDateOfBirth()));
        patient.setRegisteredDate(LocalDate.parse(patientRequestDTO.getRegisteredDate()));
        return patient;
    }
}
