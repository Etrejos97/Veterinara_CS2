package com.cs2.veterinaria.app.adapters.bill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cs2.veterinaria.app.adapters.bill.entity.BillEntity;
import com.cs2.veterinaria.app.adapters.bill.repository.BillRepository;
import com.cs2.veterinaria.app.domains.model.Bill;
import com.cs2.veterinaria.app.ports.BillPort;



@Service
public class BillAdapter implements BillPort {
    @Autowired
    private BillRepository billRepository;

    @Override
    public void saveBill(Bill bill) {
        BillEntity billEntity = new BillEntity();
        billRepository.save(billEntity);
        bill.setIdBill(billEntity.getIdBill());
    }

    
}
