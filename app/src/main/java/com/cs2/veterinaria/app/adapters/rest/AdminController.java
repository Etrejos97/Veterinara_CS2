package com.cs2.veterinaria.app.adapters.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cs2.veterinaria.app.Exceptions.BusinessException;
import com.cs2.veterinaria.app.Exceptions.InputsException;
import com.cs2.veterinaria.app.Exceptions.NotFoundException;
import com.cs2.veterinaria.app.adapters.rest.request.UserRequest;
import com.cs2.veterinaria.app.adapters.rest.response.UserResponse;
import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.domains.services.AdminServices;
import com.cs2.veterinaria.app.adapters.rest.utils.PersonValidator;
import com.cs2.veterinaria.app.adapters.rest.utils.UserValidator;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
	private AdminServices adminservice;
    @Autowired
	private PersonValidator personValidator;
    @Autowired
	private UserValidator userValidator;
    @GetMapping("/")
	public String itsAlive() {
		return "i'm alive";
	}
	
	@GetMapping("/ping")
	public String ping() {
		return "pong";
	}


    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody UserRequest request){
        try {
            System.out.println("Request: " + request.toString());
            User user = new User();
            user.setName(personValidator.nameValidator(request.getName()));
            if(request.getDocument()==0) {
                throw new InputsException("el numero de documento no puede ser cero");
            }
            user.setDocument(personValidator.documentValidator(String.valueOf(request.getDocument())));
            if(request.getAge()==0) {
                throw new InputsException("la edad no puede ser cero");
            }
            user.setAge((request.getAge()));
            user.setUserName(userValidator.userNameValidator(request.getUserName()));
		    user.setPassword(userValidator.passwordValidator(request.getPassword()));
            user.setRole((request.getRole()));
            adminservice.registerUser(user);
            return new ResponseEntity("se ha creado el socio",HttpStatus.OK);
        }catch(BusinessException be) {
			return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
		}catch(InputsException ie) {
			return new ResponseEntity(ie.getMessage(),HttpStatus.BAD_REQUEST);
		}catch(Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
    }

    @GetMapping("/user")
    public ResponseEntity getUser() {
        try{
            List<User> users = adminservice.getUsers();
            List<UserResponse> userResponse  = new ArrayList<UserResponse>();
            for (User user : users) {
                userResponse.add(adapter(user));
            }
            return new ResponseEntity(userResponse,HttpStatus.OK);
        }catch(NotFoundException NFe) {
			return new ResponseEntity(NFe.getMessage(),HttpStatus.NOT_FOUND);
        
        }catch(BusinessException be) {
			return new ResponseEntity(be.getMessage(),HttpStatus.CONFLICT);
        }catch(Exception e) {
			return new ResponseEntity(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
          
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long userId, @RequestBody UserRequest request) {
        try {
            // Crear un objeto User con los datos actualizados
            User user = new User();
            user.setUserId(userId);
            user.setName(personValidator.nameValidator(request.getName()));
            user.setUserName(userValidator.userNameValidator(request.getUserName()));
            user.setRole(request.getRole());
            user.setPassword(userValidator.passwordValidator(request.getPassword()));

            // Llamar al servicio para actualizar el usuario
            adminservice.updateUser(user);

            return new ResponseEntity<>("Usuario actualizado exitosamente", HttpStatus.OK);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
}

    @DeleteMapping("/user/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId) {
        try {
            adminservice.deleteUser(userId);
            return new ResponseEntity<>("Usuario eliminado exitosamente", HttpStatus.OK);
        } catch (NotFoundException nfe) {
            return new ResponseEntity<>(nfe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    private UserResponse adapter(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setUserName(user.getUserName());
        userResponse.setDocument(user.getDocument());
        userResponse.setRole(user.getRole());
        return userResponse;
    }
    

}
