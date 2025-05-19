package com.cs2.veterinaria.app.adapters.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cs2.veterinaria.app.Exceptions.BusinessException;
import com.cs2.veterinaria.app.Exceptions.InputsException;
import com.cs2.veterinaria.app.Exceptions.NotFoundException;
import com.cs2.veterinaria.app.adapters.rest.request.SellMedicineRequest;
import com.cs2.veterinaria.app.domains.model.Product;
import com.cs2.veterinaria.app.domains.services.SellerServices;

@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerServices sellerServices;

    @PostMapping("/sellProduct")
    public ResponseEntity<String> sellProduct(@RequestBody Product product) {
        try {
            sellerServices.sellProduct(null, product); 
            return new ResponseEntity<>("Producto vendido exitosamente", HttpStatus.OK);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (NotFoundException NFe) {
            return new ResponseEntity<>(NFe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/sellMedicine")
    public ResponseEntity<String> sellMedicine(@RequestBody SellMedicineRequest request) {
        try {
            sellerServices.sellMedicine(request.getOrderId(), request.getMedicine());
            return new ResponseEntity<>("Medicamento vendido exitosamente", HttpStatus.OK);
        } catch (BusinessException be) {
            return new ResponseEntity<>(be.getMessage(), HttpStatus.CONFLICT);
        } catch (InputsException ie) {
            return new ResponseEntity<>(ie.getMessage(), HttpStatus.BAD_REQUEST);
            
        } catch (NotFoundException NFe) {
            return new ResponseEntity<>(NFe.getMessage(), HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}