/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.veterinaria.WebSecurity;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class DatabaseWebSecurity {

	
	@Bean
	public UserDetailsManager usersCustom(DataSource dataSource) {
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.setUsersByUsernameQuery("select username, password, estatus from users where username=?");
		users.setAuthoritiesByUsernameQuery("select u.username, p.perfil from UsuarioPerfil up " + 
											"inner join Usuarios u on u.id = up.idUsuario "	+ 
											"inner join Perfiles p on p.id = up.idPerfil " + "where u.username = ?");
		return users;
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()

		// Los recursos estáticos no requieren autenticación
		.requestMatchers("/bootstrap/**", "/images/**", "/tinymce/**", "/logos/**").permitAll()

		// Las vistas públicas no requieren autenticación
		.requestMatchers("/", "/login", "/signup", "/search", "/bcrypt/**", "/about", "/form").permitAll()

		// Asignar permisos a URLs por ROLES
		.requestMatchers("/vacantes/**").hasAnyAuthority("INVITADO", "ADMINISTRADOR")
		.requestMatchers("/categorias/**").hasAnyAuthority("INVITADO", "ADMINISTRADOR")
		.requestMatchers("/usuarios/**").hasAnyAuthority("ADMINISTRADOR")

		// Todas las demás URLs de la Aplicación requieren autenticación
		.anyRequest().authenticated()

		// El formulario de Login no requiere autenticacion
		.and().formLogin().loginPage("/login").permitAll().and().logout().permitAll();

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
