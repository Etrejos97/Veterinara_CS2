package com.cs2.veterinaria.app.adapters.inputs;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs2.veterinaria.app.adapters.inputs.utils.Utils;
import com.cs2.veterinaria.app.domains.model.User;
import com.cs2.veterinaria.app.domains.services.LoginService;
import com.cs2.veterinaria.app.ports.InputPort;

import lombok.Getter;
import lombok.Setter;

import com.cs2.veterinaria.app.adapters.inputs.utils.PersonValidator;
import com.cs2.veterinaria.app.adapters.inputs.utils.UserValidator;

@Setter
@Getter
@Component
public class LoginInput implements InputPort{

    private Map<String, InputPort> inputs;
	@Autowired
	private AdminInput adminInput;
	@Autowired
	private VeterinarianInput veterinarianInput;
	@Autowired
	private SellerInput sellerInput;
	@Autowired
	private PersonValidator personValidator;
	@Autowired
    private LoginService loginService;
	private final String MENU = "Ingrese la opcion que desea:/n 1. iniciar sesion /n 2. salir";

	public LoginInput(AdminInput adminInput, VeterinarianInput veterinarianInput, SellerInput sellerInput) {
		super();
		this.adminInput = adminInput;
		this.veterinarianInput = veterinarianInput;
		this.sellerInput = sellerInput;
		this.inputs=new HashMap<String,InputPort>();
		inputs.put("admin", adminInput);
		inputs.put("Veterinarian", veterinarianInput);
		inputs.put("Seller", sellerInput);

	}


	@Override
	public void menu() throws Exception {
		System.out.println(MENU);
		String option = Utils.getReader().nextLine();
		switch (option) {
		case "1": {
			this.login();
			return;
		}
		case "2": {
			System.out.println("Hasta una proxima ocación");
			return;
		}
		default: {
			System.out.println("ha elegido una opción invalida, se detiene la ejecucion");
			return;
		}
		}
	}

	private void login() {
		try {
			System.out.println("ingrese su usuario");
			String userName = UserValidator.userNameValidator(Utils.getReader().nextLine());
			System.out.println("ingrese su contraseña");
			String password = UserValidator.passwordValidator(Utils.getReader().nextLine());
			User user = new User();
			user.setUserName(userName);
			user.setPassword(password);
			user = loginService.login(user);
			System.out.println("el rol es " + user.getRole());
			InputPort inputPort = inputs.get(user.getRole());
			inputPort.menu();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
}




