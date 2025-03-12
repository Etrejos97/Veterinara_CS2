package com.cs2.veterinaria.app.adapters.clinicalRegistry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.clinicalRegistry.entity.ClinicalRegistryEntity;
import com.cs2.veterinaria.app.adapters.clinicalRegistry.repository.ClinicalRegistryRepository;
import com.cs2.veterinaria.app.domains.model.ClinicalRegistry;
import com.cs2.veterinaria.app.ports.ClinicalRegistryPort;

@Service
public class ClinicalRegistryAdapter implements ClinicalRegistryPort {
    @Autowired
    private ClinicalRegistryRepository clinicalRegistryRepository;

    @Override
    public boolean existRegistry(Long idRegistry) {
        return clinicalRegistryRepository.existsById(idRegistry);
    }

    @Override
    public void saveRegistry(ClinicalRegistry registry) {
        ClinicalRegistryEntity registryEntity = new ClinicalRegistryEntity();
        clinicalRegistryRepository.save(registryEntity);
        registry.setIdRegistry(registryEntity.getIdRegistry());
    }

    public ClinicalRegistry createRegistry(ClinicalRegistry registry) {
        ClinicalRegistryEntity registryEntity = new ClinicalRegistryEntity(
            
        );
        clinicalRegistryRepository.save(registryEntity);
        return adapterRegistry(registryEntity);
    }

    private ClinicalRegistry adapterRegistry(ClinicalRegistryEntity registryEntity) {
        ClinicalRegistry registry = new ClinicalRegistry();
        registry.setIdRegistry(registryEntity.getIdRegistry());
        registry.setDateRegistrer(registryEntity.getDateRegistrer());
        registry.setVeterinarian(registryEntity.getVeterinarian());
        registry.setReasonConsultation(registryEntity.getReasonConsultation());
        registry.setDiagnosis(registryEntity.getDiagnosis());
        registry.setDose(registryEntity.getDose());
        registry.setIdOrder(registryEntity.getIdOrder());
        return registry;
    }
}
