package com.veterinaria.Repository;

import com.veterinaria.Model.UsersEntity;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsersRespository extends JpaRepository<UsersEntity, Integer> {

    UsersEntity findByUsername(String username);

    List<UsersEntity> findByFechaRegistroNotNull();

    @Modifying
    @Query("UPDATE UsersEntity u SET u.estatus=0 WHERE u.id = :paramIdUsuario")
    int lock(@Param("paramIdUsuario") int idUsuario);

    @Modifying
    @Query("UPDATE UsersEntity u SET u.estatus=1 WHERE u.id = :paramIdUsuario")
    int unlock(@Param("paramIdUsuario") int idUsuario);

}
