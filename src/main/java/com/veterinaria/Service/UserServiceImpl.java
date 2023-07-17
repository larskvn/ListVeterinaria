/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.Service;

import com.veterinaria.Model.UsersEntity;
import com.veterinaria.Repository.UsersRespository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UsersRespository userRepositorio;

    @Override
    public void guardar(UsersEntity usuario) {
        userRepositorio.save(usuario);
    }

    @Override
    public void eliminar(Integer idUsuario) {
        userRepositorio.deleteById(idUsuario);
    }

    @Override
    public List<UsersEntity> buscarTodos() {
        return userRepositorio.findAll();
    }

    @Override
    public UsersEntity buscarPorId(Integer idUsuario) {
        Optional<UsersEntity> optional = userRepositorio.findById(idUsuario);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    @Override
    public UsersEntity buscarPorUsername(String username) {
        return userRepositorio.findByUsername(username);
    }

    @Override
    public List<UsersEntity> buscarRegistrados() {
        return userRepositorio.findByFechaRegistroNotNull();
    }

    @Transactional
    @Override
    public int bloquear(int idUsuario) {
        int rows = userRepositorio.lock(idUsuario);
        return rows;
    }

    @Transactional
    @Override
    public int activar(int idUsuario) {
        int rows = userRepositorio.unlock(idUsuario);
        return rows;
    }

}
