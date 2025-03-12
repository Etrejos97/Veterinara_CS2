package com.cs2.veterinaria.app.ports;

import com.cs2.veterinaria.app.domains.model.HistoryClinic;

public interface HistoryClinicPort {
    boolean existHistory(Long idHistory);
    void saveHistory(HistoryClinic history);
    HistoryClinic createHistory(HistoryClinic history);
}
