package com.cs2.veterinaria.app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.cs2.veterinaria.app.domains.model.Product;
import com.cs2.veterinaria.app.domains.services.SellerServices;
import com.cs2.veterinaria.app.ports.InputPort;

import lombok.Getter;
import lombok.Setter;

import com.cs2.veterinaria.app.adapters.inputs.utils.Utils;

@Getter
@Setter
@Component
public class SellerInput implements InputPort{

    @Autowired
    private SellerServices sellerServices;
    @Autowired
    @Lazy
    private LoginInput loginInput;

    private final String MENU = "Ingrese la opción:"
            + " \n 1. Vender un producto"
            + " \n 2. Vender un medicamento"
            + " \n 3. Salir";

    public void menu() throws Exception {
        System.out.println(MENU);
        String option = Utils.getReader().nextLine();
        switch (option) {
            case "1": {
                try {
                    sellProduct();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case "2": {
                try {
                    sellMedicine();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case "3": {
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

    private void sellProduct() throws Exception {
    
        System.out.println("Ingrese el nombre del producto:");
        String productName = Utils.getReader().nextLine();
    
        System.out.println("Ingrese el precio del producto:");
        double price = Double.parseDouble(Utils.getReader().nextLine());
    
        Product product = new Product();
        product.setProductName(productName); // Nombre del método corregido
        product.setPrice(price);
    
        sellerServices.sellProduct( product);
        System.out.println("Producto vendido exitosamente.");
    }

    private void sellMedicine() throws Exception {
        System.out.println("Ingrese el ID de la orden:");
        int orderId = Integer.parseInt(Utils.getReader().nextLine());
    
        System.out.println("Ingrese el nombre del medicamento:");
        String medicineName = Utils.getReader().nextLine();

        System.out.println("Ingrese el precio del medicamento:");
        double price = Double.parseDouble(Utils.getReader().nextLine());
    
        Product medicine = new Product();
        medicine.setProductName(medicineName); 
        medicine.setPrice(price);  
    
        sellerServices.sellMedicine(orderId, medicine);
        System.out.println("Medicamento vendido exitosamente.");
    }


}
