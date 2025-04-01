package com.cs2.veterinaria.app.adapters.inputs;

import com.cs2.veterinaria.app.adapters.inputs.utils.PersonValidator;
import com.cs2.veterinaria.app.adapters.inputs.utils.Utils;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.domains.services.AdminServices;
import com.cs2.veterinaria.app.domains.services.VeterinarianService;
import com.cs2.veterinaria.app.ports.InputPort;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.cs2.veterinaria.app.domains.model.Order;
import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.Pet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Component
public class VeterinarianInput implements InputPort{

    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private AdminServices adminService;
    @Autowired
    private VeterinarianService veterinarianService;
    @Autowired
    @Lazy
    private LoginInput loginInput;

    private final String MENU = "Ingrese la opción:"
        + " \n 1. Registrar una nueva orden"
        + " \n 2. Cancelar una orden"
        + " \n 3. Crear historia clínica"
        + " \n 4. Consultar historia clínica"
        + " \n 5. Consultar todas las órdenes"
        + " \n 6. Registrar Dueño de mascota"
        + " \n 7. Actualizar dueño de mascota"
        + " \n 8. Listar dueños"
        + " \n 9. Registrar una nueva mascota"
        + " \n 10. Salir";

        public void menu() throws Exception {
            System.out.println(MENU);
            String option = Utils.getReader().nextLine();
            switch (option) {
                case "1": {
                    try {
                        registerOrder();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "2": {
                    try {
                        cancelOrder();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "3": {
                    try {
                        createClinicalHistory();;
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "4": {
                    try {
                        getClinicalHistory();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "5": {
                    try {
                        getAllOrders();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "6": {
                    try {
                        registerOwner();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "7": {
                    try {
                        deleteOwner();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "8": {
                    try {
                        listOwners();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "9": {
                    try {
                        registerPet();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                }
                case "10": {
                    System.out.println("Hasta una proxima ocación");
                    loginInput.menu();
                    break;
                }
                
                default: {
                    System.out.println("Opción no válida");
                    break;
                }
            }
        }

        private void registerOrder() throws Exception {
            try {
                // Solicitar los datos necesarios para la orden
                System.out.println("Ingrese el nombre del medicamento:");
                String drugName = Utils.getReader().nextLine();
        
                System.out.println("Ingrese la fecha de la orden (YYYY-MM-DD):");
                String dateOrder = Utils.getReader().nextLine();
        
                System.out.println("Ingrese el ID de la mascota asociada a la orden:");
                long idPet = Long.parseLong(Utils.getReader().nextLine());
        
                System.out.println("Ingrese el ID del dueño de la mascota:");
                long idOwner = Long.parseLong(Utils.getReader().nextLine());
        
                System.out.println("Ingrese el ID del usuario que registra la orden:");
                long userId = Long.parseLong(Utils.getReader().nextLine());
        
                // Crear el objeto Order
                Order order = new Order();
                order.setDrugName(drugName);
                order.setDateOrder(dateOrder);
                order.setIdPet(idPet);
                order.setIdOwner(idOwner);
                order.setUserId(userId);
        
                // Llamar al servicio para registrar la orden
                veterinarianService.registerOrder(order);
                System.out.println("Orden registrada exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al registrar la orden: " + e.getMessage());
            }
        }

        private void cancelOrder() throws Exception {
            System.out.println("Ingrese el ID de la orden a cancelar:");
            int idOrder = Integer.parseInt(Utils.getReader().nextLine());
            System.out.println("Ingrese el ID de la orden a cancelar:");
            long idPet = Long.parseLong(Utils.getReader().nextLine());
            System.out.println("Ingrese el ID de la orden a cancelar:");
            String reason = Utils.getReader().nextLine();
        
            veterinarianService.cancelOrder(idOrder, idPet,reason);
            System.out.println("Orden cancelada exitosamente.");
        }

        private void createClinicalHistory() {
            try {
                System.out.println("Ingrese el ID de la mascota:");
                long idPet = Long.parseLong(Utils.getReader().nextLine());
        
                System.out.println("Ingrese los detalles de la historia clínica:");
                String details = Utils.getReader().nextLine();
        
                // Crear el objeto HistoryClinic
                HistoryClinic history = new HistoryClinic();
                history.setIdPet(idPet);
                history.setDetails(details);
        
                // Llamar al servicio para crear la historia clínica
                veterinarianService.createClinicalHistory(history);
                System.out.println("Historia clínica creada exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al crear la historia clínica: " + e.getMessage());
            }
        }

        private void getClinicalHistory() {
            try {
                System.out.println("Ingrese el ID de la mascota:");
                long idPet = Long.parseLong(Utils.getReader().nextLine());

                List<HistoryClinic> history = veterinarianService.getClinicalHistory(idPet);
                System.out.println("Historia clínica de la mascota:");
                for (HistoryClinic record : history) {
                    System.out.println("Fecha: " + record + ", Descripción: ");
                }
            } catch (Exception e) {
                System.out.println("Error al consultar la historia clínica: " + e.getMessage());
            }
        }

        private void getAllOrders() {
            try {
                List<Order> orders = veterinarianService.getAllOrders();
                System.out.println("Lista de órdenes:");
                for (Order order : orders) {
                    System.out.println("ID: " + order.getIdOrder() + ", Descripción: ");
                }
            } catch (Exception e) {
                System.out.println("Error al consultar las órdenes: " + e.getMessage());
            }
        }
        private void registerOwner() {
        try {
            System.out.println("Ingrese el nombre del dueño:");
            String name = personValidator.nameValidator(Utils.getReader().nextLine());

            System.out.println("Ingrese el documento del dueño:");
            Long document = personValidator.documentValidator(Utils.getReader().nextLine());

            Person person = new Person();
            person.setName(name);
            person.setDocument(document);

            veterinarianService.registerOwner(person);
            System.out.println("Dueño registrado exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al registrar el dueño: " + e.getMessage());
                }
    }

    private void deleteOwner() {
        try {
            System.out.println("Ingrese el documento del dueño a eliminar:");
            Long document = personValidator.documentValidator(Utils.getReader().nextLine());
            veterinarianService.deleteOwner(document);
            System.out.println("Dueño eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el dueño: " + e.getMessage());
            }
    }

    

    private void listOwners() {
        try {
            System.out.println("Lista de dueños:");
            List<Person> owners = adminService.listOwners();
            for (Person owner : owners) {
                System.out.println("Documento: " + owner.getDocument() + ", Nombre: " + owner.getName());
            }
        } catch (Exception e) {
            System.out.println("Error al listar los dueños: " + e.getMessage());
        }
    }

    private void registerPet() throws Exception {
        System.out.println("Ingrese el nombre de la mascota:");
        String name = Utils.getReader().nextLine();

        System.out.println("Ingrese el ID del dueño de la mascota:");
        Long idOwner = Long.parseLong(Utils.getReader().nextLine());

        System.out.println("Ingrese la edad de la mascota:");
        int age = Integer.parseInt(Utils.getReader().nextLine());

        System.out.println("Ingrese la especie de la mascota:");
        String species = Utils.getReader().nextLine();

        System.out.println("Ingrese la raza de la mascota:");
        String race = Utils.getReader().nextLine();

        System.out.println("Ingrese las características de la mascota:");
        String characteristics = Utils.getReader().nextLine();

        System.out.println("Ingrese el peso de la mascota:");
        float weight = Float.parseFloat(Utils.getReader().nextLine());

        // Crear el objeto Pet
        Pet pet = new Pet();
        pet.setName(name);
        pet.setIdOwner(idOwner);
        pet.setAge(age);
        pet.setSpecies(species);
        pet.setRace(race);
        pet.setCharacteristics(characteristics);
        pet.setWeight(weight);

        // Llamar al servicio para registrar la mascota
        veterinarianService.registerPet(pet);
    }

       
}