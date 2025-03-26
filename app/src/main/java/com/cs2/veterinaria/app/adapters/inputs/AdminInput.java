package com.cs2.veterinaria.app.adapters.inputs;

import com.cs2.veterinaria.app.domains.model.Person;
import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.domains.services.adminServices;
import com.cs2.veterinaria.app.ports.InputPort;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;


import com.cs2.veterinaria.app.adapters.inputs.utils.PersonValidator;
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
    private adminServices adminService;

    private final String MENU = "Ingrese la opcion:"
            + " \n 1. para crear Usuario."
            + " \n 2. eliminar usuario" 
            + " \n 3. Actualizar datos de usuario"
            + " \n 4. .Registrar Dueño de mascota"
            + " \n 5. .Actualizar mascota de dueño"
            + " \n 6. .Listar usuarios"
            + " \n 6. .Listar dueños"
            + " \n 7. .";

    public void menu() {
        System.out.println(MENU);
        String option = Utils.getReader().nextLine();
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
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "4": {
                try {
                    registerOwner();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "5": {
                try {
                    deleteOwner();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "6": {
                try {
                    listUsers();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            case "7": {
                try {
                    listOwners();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            default: {
                System.out.println("Opción no válida");
            }
        }
    }
    private void createUser() throws Exception {
        System.out.println("Ingrese el nombre del usuario:");
        String name = personValidator.nameValidator(Utils.getReader().nextLine());
    
        System.out.println("Ingrese el documento del usuario:");
        long document = personValidator.documentValidator(Utils.getReader().nextLine());
    
        System.out.println("Ingrese la contraseña del usuario");
        String password = Utils.getReader().nextLine();

        System.out.println("Ingrese el rol del usuario");
        String role = Utils.getReader().nextLine();

        User user = new User();
        user.setDocument(document);

        user.setName(name);
        user.setRole(role);
        user.setPassword(password);
        adminService.registerUser(user);
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

    private void updateUser() {
        try {
            System.out.println("Ingrese el ID del usuario a actualizar:");
            Long userId = Long.parseLong(Utils.getReader().nextLine());
    
            System.out.println("Ingrese el nuevo nombre del usuario:");
            String name = personValidator.nameValidator(Utils.getReader().nextLine());
    
            System.out.println("Ingrese el nuevo rol del usuario:");
            String role = Utils.getReader().nextLine();
    
            System.out.println("Ingrese la nueva contraseña del usuario:");
            String password = Utils.getReader().nextLine();
    
            User user = new User();
            user.setUserId(userId);
            user.setName(name);
            user.setRole(role);
            user.setPassword(password);
    
            adminService.updateUser(user);
            System.out.println("Usuario actualizado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
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

            adminService.registerOwner(person);
            System.out.println("Dueño registrado exitosamente.");
            } catch (Exception e) {
                System.out.println("Error al registrar el dueño: " + e.getMessage());
                }
    }

    private void deleteOwner() {
        try {
            System.out.println("Ingrese el documento del dueño a eliminar:");
            Long document = personValidator.documentValidator(Utils.getReader().nextLine());
            adminService.deleteOwner(document);
            System.out.println("Dueño eliminado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el dueño: " + e.getMessage());
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

}
            
            


