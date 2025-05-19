package com.cs2.veterinaria.app.domains.services;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.cs2.veterinaria.app.Exceptions.BusinessException;
import com.cs2.veterinaria.app.Exceptions.NotFoundException;
import com.cs2.veterinaria.app.adapters.pet.entity.PetEntity;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.domains.model.Order;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.Pet;
import com.cs2.veterinaria.app.ports.ClinicalPort;
import com.cs2.veterinaria.app.ports.OrderPort;
import com.cs2.veterinaria.app.ports.PersonPort;
import com.cs2.veterinaria.app.ports.PetOwnerPort;
import com.cs2.veterinaria.app.ports.PetPort;
import com.cs2.veterinaria.app.ports.UserPort;

import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Service
public class VeterinarianService {

    @Autowired
    private PersonPort personPort;
    @Autowired
    private PetPort petPort;

    @Autowired
    private PetOwnerPort petOwnerPort;

    @Autowired
    private ClinicalPort clinicalPort;

    @Autowired
    private OrderPort orderPort;

    @Autowired
    private UserPort userPort;

    // Método para registrar una nueva orden
    public void registerOrder(Order order) throws Exception {
        // Verificar si la mascota asociada existe
        if (!petPort.existPetByIdPet(order.getIdPet())) {
            throw new BusinessException("No existe una mascota con el ID especificado.");
        }
    
        // Verificar si el dueño asociado existe
        if (!petOwnerPort.existPetOwnerById(order.getIdOwner())) {
            throw new BusinessException("No existe un dueño con el ID especificado.");
        }
    
        // Verificar si el usuario asociado existe
        if (!userPort.existUserId(order.getUserId())) {
            throw new BusinessException("No existe un usuario con el ID especificado.");
        }
    
        // Guardar la orden en la base de datos
        orderPort.saveOrder(order);
    }

    // Método para cancelar una orden de medicamento
    public void cancelOrder(int idOrder, long idPet, String reason) throws Exception {
        if (!orderPort.existOrder(idOrder)) {
            throw new BusinessException("No existe una orden con el ID especificado");
        }
        orderPort.cancelOrder(idOrder);

        // Registrar la anulación en la historia clínica
        HistoryClinic history = new HistoryClinic();
        history.setIdPet(idPet);
        history.setDetails("Orden médica anulada. Razón: " + reason);
        clinicalPort.saveHistory(history);
    }

    public HistoryClinic createClinicalHistory(HistoryClinic history) throws Exception {
        // Verificar si la mascota asociada existe
        if (!petPort.existPetByIdPet(history.getIdPet())) {
            throw new BusinessException("No existe una mascota con el ID especificado.");
        }
    
        // Crear la historia clínica
        return clinicalPort.createHistory(history);
    }

    // Método para consultar la historia clínica de una mascota
    public List<HistoryClinic> getClinicalHistory(long idPet) {
        PetEntity petEntity = new PetEntity();
        petEntity.setIdPet(idPet); 
        return clinicalPort.findClinicalHistoryByPetId(petEntity);
}

    // Método para consultar todas las órdenes de medicamentos
    public List<Order> getAllOrders() {
        return orderPort.findAllOrders();
    }

    

    public void registerOwner(Person person) throws Exception {
        // Verificar si la persona ya existe
        if (personPort.existPerson(person.getDocument())) {
            throw new BusinessException("Ya existe una persona con ese documento");
        }
    
        // Guardar la persona en la base de datos
        personPort.savePerson(person);
    
        // Verificar que el person_id se haya asignado correctamente
        if (person.getPersonId() == 0) {
            throw new NotFoundException("Error al asignar el ID de la persona.");
        }
    
        // Guardar el dueño de la mascota
        petOwnerPort.savePetOwner(person);
    }

    public List<Person> listOwners() {
        return petOwnerPort.findAllPetOwners()
            .stream()
            .map(owner -> (Person) owner)
            .toList();
    }

    public void registerPet(Pet pet) throws Exception {
        // Verificar si el dueño de la mascota existe por idOwner
        if (!petOwnerPort.existPetOwnerById(pet.getIdOwner())) {
            throw new NotFoundException("No existe un dueño con el ID especificado.");
        }
    
        // Crear la mascota en la base de datos
        petPort.createPet(pet);
        System.out.println("Mascota registrada exitosamente.");
    }
}