package com.cs2.veterinaria.app.adapters.inputs;


import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.domains.services.AdminServices;
import com.cs2.veterinaria.app.ports.InputPort;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;



import com.cs2.veterinaria.app.adapters.inputs.utils.PersonValidator;
import com.cs2.veterinaria.app.adapters.inputs.utils.UserValidator;
import com.cs2.veterinaria.app.adapters.inputs.utils.Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Component
public class AdminInput implements InputPort {
    @Autowired
    private PersonValidator personValidator;
    @Autowired
    private AdminServices adminService;
    @Autowired
    private UserValidator userValidator;
    @Autowired
    @Lazy
    private LoginInput loginInput;

    private final String MENU = "Ingrese la opcion:"
            + " \n 1. Crear Usuario."
            + " \n 2. Eliminar usuario" 
            + " \n 3. Actualizar datos de usuario"
            + " \n 4. Listar usuarios"            
            + " \n 5. Salir";

    public void menu() throws Exception {
        String option ="0";
        do{
        System.out.println(MENU);
        option = Utils.getReader().nextLine();
        switch (option) {
            case "1": {
                try {
                    createUser();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "2": {
                try {
                    deleteUser();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "3": {
                try {
                    updateUser();
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case "4": {
                try {
                    listUsers();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "5": {
                System.out.println("Hasta una proxima ocación");
                loginInput.menu();
    break;
            }
            default: {
                System.out.println("Opción no válida");
                break;
            }
        }
        }while (!option.equals("0"));
    }

    private void deleteUser() {
        try {
            System.out.println("Ingrese el ID del usuario a eliminar:");
            Long userId = Long.parseLong(Utils.getReader().nextLine());
            adminService.deleteUser(userId);
            System.out.println("Usuario eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    private void createUser() throws Exception {
        System.out.println("Ingrese el nombre del usuario:");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
    
        System.out.println("Ingrese el documento del usuario:");
        long document = personValidator.documentValidator(Utils.getReader().nextLine());

        System.out.println("Ingrese la edad:");
        int age = Integer.parseInt(Utils.getReader().nextLine());
        
        System.out.println("Ingrese el UserName:");
        String userName = userValidator.userNameValidator(Utils.getReader().nextLine());
    
        System.out.println("Ingrese la contraseña del usuario");
        String password = Utils.getReader().nextLine();

        System.out.println("Ingrese el rol del usuario");
        String role = Utils.getReader().nextLine();

        User user = new User();
        user.setDocument(document);
        user.setName(name);
        user.setAge(age);
        user.setUserName(userName);
        user.setRole(role);
        user.setPassword(password);
        adminService.registerUser(user);
    }
    private void updateUser() {
        try {
            // Solicitar el ID del usuario a actualizar
            System.out.println("Ingrese el ID del usuario a actualizar:");
            Long userId = Long.parseLong(Utils.getReader().nextLine());
    
            // Solicitar el nuevo nombre del usuario
            System.out.println("Ingrese el nuevo nombre del usuario:");
            String userName = personValidator.nameValidator(Utils.getReader().nextLine());
    
            // Solicitar el nuevo rol del usuario
            System.out.println("Ingrese el nuevo rol del usuario:");
            String role = Utils.getReader().nextLine();
    
            // Solicitar la nueva contraseña del usuario
            System.out.println("Ingrese la nueva contraseña del usuario:");
            String password = Utils.getReader().nextLine();
    
            // Crear un objeto User con los datos ingresados
            User user = new User();
            user.setUserId(userId); 
            user.setUserName(userName);
            user.setRole(role);
            user.setPassword(password);
    
            // Llamar al servicio para actualizar el usuario
            adminService.updateUser(user);
            System.out.println("Usuario actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    private void listUsers() {
        try {
            System.out.println("Lista de usuarios:");
            List<User> users = adminService.listUsers();
            for (User user : users) {
                System.out.println("ID: " + user.getUserId() + ", Nombre: " + user.getName() + ", Rol: " + user.getRole());
            }
        } catch (Exception e) {
            System.out.println("Error al listar los usuarios: " + e.getMessage());
        }
    }
            

}
            
            


