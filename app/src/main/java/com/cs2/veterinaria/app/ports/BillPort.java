package com.cs2.veterinaria.app.ports;


import com.cs2.veterinaria.app.domains.model.Bill;

public interface BillPort {
    void saveBill(Bill bill);
    
}
