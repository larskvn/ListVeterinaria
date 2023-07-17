/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.veterinaria.Service;

import com.veterinaria.Model.VeterinariasModel;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface VeterinariaService {

    
    public List<VeterinariasModel> findAll();
    
    public VeterinariasModel add(VeterinariasModel veterinaria);
 
     
}
