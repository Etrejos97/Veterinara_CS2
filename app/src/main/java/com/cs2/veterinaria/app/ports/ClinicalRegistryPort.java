package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.ClinicalRegistry;

public interface ClinicalRegistryPort {
    boolean existRegistry(Long idRegistry);
    void saveRegistry(ClinicalRegistry registry);
    ClinicalRegistry createRegistry(ClinicalRegistry registry);
}
