package com.cyber.service;

import com.cyber.exception.ResourceNotFoundException;
import com.cyber.model.Incident;
import com.cyber.repository.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    @Autowired
    private IncidentRepository repository;

    public Incident createIncident(Incident incident) {
        return repository.save(incident);
    }

    public List<Incident> getAllIncidents() {
        return repository.findAll();
    }

    public Incident getIncidentById(Long id) {
        return repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Incident not found with id: " + id));
    }

    public Incident updateIncident(Long id, Incident incidentDetails) {
        Incident incident = getIncidentById(id);
        incident.setType(incidentDetails.getType());
        incident.setSeverity(incidentDetails.getSeverity());
        
        // Fixed: changed setStatus to getStatus
        incident.setStatus(incidentDetails.getStatus()); 
        
        incident.setResolutionTimeHours(incidentDetails.getResolutionTimeHours());
        return repository.save(incident);
    }

    public void deleteIncident(Long id) {
        Incident incident = getIncidentById(id);
        repository.delete(incident);
    }
}