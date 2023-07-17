/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.veterinaria.Repository;

import com.veterinaria.Model.VeterinariasModel;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;


@Repository
public interface VeterinariRepository extends JpaRepository<VeterinariasModel, Long> {
/*@Query("SELECT v FROM VeterinariasModel v WHERE v.nombre LIKE %?1%")*/

    
    @Override
    List<VeterinariasModel> findAll();
}
