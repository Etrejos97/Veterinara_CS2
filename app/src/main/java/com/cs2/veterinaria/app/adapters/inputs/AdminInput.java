package com.cs2.veterinaria.app.adapters.inputs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cs2.veterinaria.app.adapters.inputs.utils.PersonValidator;
import com.cs2.veterinaria.app.adapters.inputs.utils.Utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@Component
public class AdminInput {
	@Autowired
	private PersonValidator personValidator;
	

	private final String MENU = "Ingrese la opcion:"
			+ " \n 1. para crear Usuario."
			+ " \n 2. "
			+ " \n 3. "
			+ " \n 4. .";
	
	public void menu() {
		System.out.println(MENU);
		String option = Utils.getReader().nextLine();
		switch (option){
		case "1":{
			try {
				// this.createPartner();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		}
		default :
			System.out.println("opcion no valida");
		}
	}
	
}
