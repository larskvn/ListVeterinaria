package com.veterinaria.Service;

import com.veterinaria.Model.UsersEntity;
import java.util.List;

public interface UserService {

    void guardar(UsersEntity usuario);

    void eliminar(Integer idUsuario);

    List<UsersEntity> buscarTodos();

    List<UsersEntity> buscarRegistrados();

    UsersEntity buscarPorId(Integer idUsuario);

    UsersEntity buscarPorUsername(String username);

    int bloquear(int idUsuario);

    int activar(int idUsuario);

}
