package com.cs2.veterinaria.app.adapters.rest;

import com.cs2.veterinaria.app.adapters.rest.request.HistoryClinicRequest;
import com.cs2.veterinaria.app.adapters.rest.request.OrderRequest;
import com.cs2.veterinaria.app.adapters.rest.request.OwnerRequest;
import com.cs2.veterinaria.app.adapters.rest.request.PetRequest;
import com.cs2.veterinaria.app.adapters.rest.response.HistoryClinicResponse;
import com.cs2.veterinaria.app.adapters.rest.response.OrderResponse;
import com.cs2.veterinaria.app.adapters.rest.response.OwnerResponse;
import com.cs2.veterinaria.app.Exceptions.BusinessException;
import com.cs2.veterinaria.app.Exceptions.InputsException;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.domains.model.Order;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.Pet;
import com.cs2.veterinaria.app.domains.services.VeterinarianService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/veterinarian")
public class VeterinarianController {

    @Autowired
    private VeterinarianService veterinarianService;

    @PostMapping("/registerOrder")
    public ResponseEntity<String> registerOrder(@RequestBody OrderRequest request) {
        try {
            // Validaciones básicas
            if (request.getDrugName() == null || request.getDrugName().isEmpty()) {
                throw new InputsException("El nombre del medicamento es obligatorio");
            }
            if (request.getDateOrder() == null || request.getDateOrder().isEmpty()) {
                throw new InputsException("La fecha de la orden es obligatoria");
            }
            if (request.getIdPet() <= 0) {
                throw new InputsException("El ID de la mascota es obligatorio");
            }
            if (request.getIdOwner() <= 0) {
                throw new InputsException("El ID del dueño es obligatorio");
            }
            if (request.getUserId() <= 0) {
                throw new InputsException("El ID del usuario es obligatorio");
            }

            // Crear el objeto Order (modelo de dominio)
            Order order = new Order();
            order.setDrugName(request.getDrugName());
            order.setDateOrder(request.getDateOrder());
            order.setIdPet(request.getIdPet());
            order.setIdOwner(request.getIdOwner());
            order.setUserId(request.getUserId());

            // Llamar al servicio para registrar la orden
            veterinarianService.registerOrder(order);

            return new ResponseEntity<>("Orden registrada exitosamente.", HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    

    @PostMapping("/createClinicalHistory")
    public ResponseEntity<String> createClinicalHistory(@RequestBody HistoryClinicRequest request) {
        try {
            if (request.getIdPet() <= 0) {
                throw new InputsException("El ID de la mascota es obligatorio");
            }
            if (request.getDetails() == null || request.getDetails().isEmpty()) {
                throw new InputsException("Debe ingresar los detalles de la historia clínica");
            }

            
            HistoryClinic history = new HistoryClinic();
            history.setIdPet(request.getIdPet());
            history.setDetails(request.getDetails());

            veterinarianService.createClinicalHistory(history);
            return new ResponseEntity<>("Historia clínica creada exitosamente.", HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/clinicalHistory/{idPet}")
    public ResponseEntity<?> getClinicalHistory(@PathVariable long idPet) {
        try {
            if (idPet <= 0) {
                throw new InputsException("El ID de la mascota es obligatorio");
            }
            List<HistoryClinic> historyList = veterinarianService.getClinicalHistory(idPet);
            if (historyList == null || historyList.isEmpty()) {
                return new ResponseEntity<>("No se encontraron registros de historia clínica para la mascota.", HttpStatus.NOT_FOUND);
            }
            // Mapear a DTO de respuesta
            List<HistoryClinicResponse> responseList = historyList.stream()
                .map(h -> new HistoryClinicResponse(h.getIdHistory(), h.getIdPet(), h.getDetails()))
                .toList();
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/orders")
    public ResponseEntity getAllOrders() {
        try {
            List<Order> orders = veterinarianService.getAllOrders();
            if (orders == null || orders.isEmpty()) {
                return new ResponseEntity<>("No se encontraron órdenes registradas.", HttpStatus.NOT_FOUND);
            }
            List<OrderResponse> responseList = orders.stream()
                .map(o -> new OrderResponse(o.getIdOrder(), o.getDrugName(), o.getDateOrder()))
                .toList();
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerOwner")
    public ResponseEntity<String> registerOwner(@RequestBody OwnerRequest request) {
        try {
            if (request.getName() == null || request.getName().isEmpty()) {
                throw new InputsException("El nombre del dueño es obligatorio");
            }
            if (request.getDocument() == null || request.getDocument() <= 0) {
                throw new InputsException("El documento del dueño es obligatorio");
            }

            Person person = new Person();
            person.setName(request.getName());
            person.setDocument(request.getDocument());

            veterinarianService.registerOwner(person);
            return new ResponseEntity<>("Dueño registrado exitosamente.", HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/owners")
    public ResponseEntity<?> listOwners() {
        try {
            List<Person> owners = veterinarianService.listOwners();
            if (owners == null || owners.isEmpty()) {
                return new ResponseEntity<>("No se encontraron dueños registrados.", HttpStatus.NOT_FOUND);
            }
            List<OwnerResponse> responseList = owners.stream()
                .map(o -> new OwnerResponse(o.getDocument(), o.getName()))
                .toList();
            return new ResponseEntity<>(responseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/registerPet")
    public ResponseEntity<String> registerPet(@RequestBody PetRequest request) {
        try {
            if (request.getName() == null || request.getName().isEmpty()) {
                throw new InputsException("El nombre de la mascota es obligatorio");
            }
            if (request.getIdOwner() == null || request.getIdOwner() <= 0) {
                throw new InputsException("El ID del dueño es obligatorio");
            }
            if (request.getAge() <= 0) {
                throw new InputsException("La edad de la mascota es obligatoria");
            }
            if (request.getSpecies() == null || request.getSpecies().isEmpty()) {
                throw new InputsException("La especie de la mascota es obligatoria");
            }
            if (request.getRace() == null || request.getRace().isEmpty()) {
                throw new InputsException("La raza de la mascota es obligatoria");
            }
            if (request.getCharacteristics() == null || request.getCharacteristics().isEmpty()) {
                throw new InputsException("Las características de la mascota son obligatorias");
            }
            if (request.getWeight() <= 0) {
                throw new InputsException("El peso de la mascota es obligatorio");
            }

            Pet pet = new Pet();
            pet.setName(request.getName());
            pet.setIdOwner(request.getIdOwner());
            pet.setAge(request.getAge());
            pet.setSpecies(request.getSpecies());
            pet.setRace(request.getRace());
            pet.setCharacteristics(request.getCharacteristics());
            pet.setWeight(request.getWeight());

            veterinarianService.registerPet(pet);
            return new ResponseEntity<>("Mascota registrada exitosamente.", HttpStatus.OK);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
