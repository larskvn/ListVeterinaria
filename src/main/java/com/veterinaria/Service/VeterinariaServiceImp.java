/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.Service;

import com.veterinaria.Model.VeterinariasModel;
import com.veterinaria.Repository.VeterinariRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class VeterinariaServiceImp implements VeterinariaService{

    @Autowired
    private VeterinariRepository veteriariarepo;
    @Override
    public List<VeterinariasModel> findAll() {
        return veteriariarepo.findAll();
    }

    @Override
    public VeterinariasModel add(VeterinariasModel veterinaria) {
        return veteriariarepo.save(veterinaria);
    }
}
