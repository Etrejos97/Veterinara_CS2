package com.cs2.veterinaria.app.adapters.inputs;

import com.cs2.veterinaria.app.adapters.inputs.utils.Utils;
import com.cs2.veterinaria.app.domains.model.HistoryClinic;
import com.cs2.veterinaria.app.domains.services.VeterinarianService;
import com.cs2.veterinaria.app.ports.InputPort;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import com.cs2.veterinaria.app.domains.model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@Component
public class VeterinarianInput implements InputPort{

    @Autowired
    private VeterinarianService veterinarianService;

    private final String MENU = "Ingrese la opción:"
        + " \n 1. Registrar una nueva orden"
        + " \n 2. Cancelar una orden"
        + " \n 3. Consultar historia clínica"
        + " \n 4. Consultar todas las órdenes"
        + " \n 5. Guardar un usuario"
        + " \n 6. Eliminar un usuario";

        public void menu() {
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
                        getClinicalHistory();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                case "4": {
                    try {
                        getAllOrders();
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }
                default: {
                    System.out.println("Opción no válida");
                    break;
                }
            }
        }

        private void registerOrder() throws Exception {
            System.out.println("Ingrese el ID de la orden:");
            int idOrder = Integer.parseInt(Utils.getReader().nextLine());
        
            Order order = new Order();
            order.setIdOrder(idOrder);
        
            veterinarianService.registerOrder(order);
            System.out.println("Orden registrada exitosamente.");
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

       
}