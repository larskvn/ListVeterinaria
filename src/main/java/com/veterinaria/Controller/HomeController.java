/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.Controller;

import com.veterinaria.Model.Perfil;
import com.veterinaria.Model.UsersEntity;
import org.springframework.ui.Model;
import com.veterinaria.Model.VeterinariasModel;
import com.veterinaria.Service.UserService;

import com.veterinaria.Service.VeterinariaServiceImp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class HomeController {

    @Autowired
    VeterinariaServiceImp veterianariaservicio;

    @Autowired
    UserService UserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping("/")
    public String home(Model modelo) {
        List<VeterinariasModel> listar = veterianariaservicio.findAll();
        modelo.addAttribute("listar", listar);
        return "/index";
    }

    @RequestMapping("/form")
    public String campoForm(Model modelo) {
        VeterinariasModel veterinaria = new VeterinariasModel();
        modelo.addAttribute("veterinaria", veterinaria);
        return "formulario";
    }

    @RequestMapping(value = "/guardar", method = RequestMethod.POST)
    public String add(@ModelAttribute("veterinaria") VeterinariasModel veterinaria) {
        veterianariaservicio.add(veterinaria);
        return "redirect:/";
    }

    /*@GetMapping("/index")
    public String mostrarIndex(Authentication authentication, HttpSession session) {

        // Como el usuario ya ingreso, ya podemos agregar a la session el objeto usuario.
        String username = authentication.getUsername();

        for (GrantedAuthority rol : authentication.getAuthorities()) {
            System.out.println("ROL: " + rol.getAuthority());
        }

        if (session.getAttribute("usuario") == null) {
            UsersEntity usuario = UserService.buscarPorUsername(username);
            //System.out.println("Usuario: " + usuario);
            session.setAttribute("usuario", usuario);
        }

        return "redirect:/";
    }*/

    @GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }

    /**
     * Método que guarda en la base de datos el usuario registrado
     *
     * @param usuario
     * @param attributes
     * @return
     */
    @PostMapping("/signup")
    public String guardarRegistro(UsersEntity usuario, RedirectAttributes attributes) {
        // Recuperamos el password en texto plano
        String pwdPlano = usuario.getPassword();
        // Encriptamos el pwd BCryptPasswordEncoder
        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
        // Hacemos un set al atributo password (ya viene encriptado)
        usuario.setPassword(pwdEncriptado);
        usuario.setEstatus(1); // Activado por defecto
        usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor

        // Creamos el Perfil que le asignaremos al usuario nuevo
        Perfil perfil = new Perfil();
        perfil.setId(2); // Perfil USUARIO
        usuario.agregar(perfil);

        /**
         * Guardamos el usuario en la base de datos. El Perfil se guarda
         * automaticamente
         */
        UserService.guardar(usuario);

        attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");

        return "redirect:/login";
    }


    /*@GetMapping("/formregistro")
    public String registrarse(UsersEntity usuario) {
        return "registroForm";
    }

    /**
     * Método que guarda en la base de datos el usuario registrado
     *
     * @param usuario
     * @param attributes
     * @return
     */
 /*@PostMapping("/formregistro")
    public String guardarRegistro(UsersEntity usuario, RedirectAttributes attributes) {
        // Recuperamos el password en texto plano
        String pwdPlano = usuario.getPassword();
        // Encriptamos el pwd BCryptPasswordEncoder
        String pwdEncriptado = passwordEncoder.encode(pwdPlano);
        // Hacemos un set al atributo password (ya viene encriptado)
        usuario.setPassword(pwdEncriptado);
        usuario.setEstatus(1); // Activado por defecto
        usuario.setFechaRegistro(new Date()); // Fecha de Registro, la fecha actual del servidor

        // Creamos el Perfil que le asignaremos al usuario nuevo
        Perfil perfil = new Perfil();
        perfil.setId(2); // Perfil USUARIO
        usuario.agregar(perfil);

        /**
         * Guardamos el usuario en la base de datos. El Perfil se guarda
         * automaticamente
     */
 /*UserService.guardar(usuario);

        attributes.addFlashAttribute("msg", "Has sido registrado. ¡Ahora puedes ingresar al sistema!");

        return "redirect:/login";
}*/

 /* @GetMapping("/formregistro")
    public String mostrarFormularioRegistro(Model model) {
        UsersEntity usuarios = new UsersEntity();
        model.addAttribute("usuario", usuarios);
        return "registroForm";
    }*/

 /* @PostMapping("/registro")
    public String registrarUsuario(UsersEntity usuario) {
        usuarioService.add(usuario);
        return "redirect:/login?logout";
    }*/
 /* @GetMapping("/login")
    public String mostrarLogin() {
        return "formLogin";
    }*/

 /* @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/login";
    }*/
}
