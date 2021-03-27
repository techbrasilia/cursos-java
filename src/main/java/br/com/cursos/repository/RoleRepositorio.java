package br.com.cursos.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.cursos.model.ERole;
import br.com.cursos.model.Role;

public interface RoleRepositorio extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(ERole roleUser);

}
