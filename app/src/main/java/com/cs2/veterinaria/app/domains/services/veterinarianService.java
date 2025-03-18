package com.cs2.veterinaria.app.domains.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.domains.model.Order;
import com.cs2.veterinaria.app.ports.ClinicalPort;
import com.cs2.veterinaria.app.ports.OrderPort;
import com.cs2.veterinaria.app.ports.UserPort;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class veterinarianService {

    @Autowired
    private ClinicalPort clinicalPort;

    @Autowired
    private OrderPort orderPort;

    @Autowired
    private UserPort userPort;

    // Método para registrar una nueva orden
    public void registerOrder(Order order) throws Exception {
        if (orderPort.existOrder(order.getIdOrder())) {
            throw new Exception("Ya existe una orden con el ID especificado");
        }
        orderPort.saveOrder(order);
    }

    // Método para cancelar una orden de medicamento
    public void cancelOrder(int idOrder, long idPet, String reason) throws Exception {
        if (!orderPort.existOrder(idOrder)) {
            throw new Exception("No existe una orden con el ID especificado");
        }
        orderPort.cancelOrder(idOrder);

        // Registrar la anulación en la historia clínica
        HistoryClinic history = new HistoryClinic();
        history.setIdPet(idPet);
        history.setDetails("Orden médica anulada. Razón: " + reason);
        clinicalPort.saveHistory(history);
    }

    // Método para consultar la historia clínica de una mascota
    public List<HistoryClinic> getClinicalHistory(long idPet) {
        return clinicalPort.findClinicalHistoryByPetId(idPet);
    }

    // Método para consultar todas las órdenes de medicamentos
    public List<Order> getAllOrders() {
        return orderPort.findAllOrders();
    }
}