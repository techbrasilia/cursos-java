package br.com.cursos.config;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.cursos.model.Curso;
import br.com.cursos.model.ERole;
import br.com.cursos.model.Role;
import br.com.cursos.model.Usuario;
import br.com.cursos.repository.CursoRepository;
import br.com.cursos.repository.RoleRepositorio;
import br.com.cursos.repository.UsuarioRepositorio;

@Configuration
@Profile("dev")
public class CarregaBaseDeDados {

	@Autowired
	private CursoRepository repository;
	
	@Autowired
	private RoleRepositorio roleRepo;

	@Autowired
	private UsuarioRepositorio userRepo;

	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Bean
	CommandLineRunner carregaBanco() {
		return args -> {
			Curso curso1 = new Curso("Curso de Java Avançado", 150.00);
			Curso curso2 = new Curso("Curso de PHP Básico", 99.00);
			Curso curso3 = new Curso("Curso de Ruby On Rails", 199.00);
			Curso curso4 = new Curso("Curso de JavaScript", 59.00);
			Curso curso5 = new Curso("Curso de React", 559.00);

			repository.save(curso1);
			repository.save(curso2);
			repository.save(curso3);
			repository.save(curso4);
			repository.save(curso5);
			
			roleRepo.save(new Role(ERole.ROLE_ADMIN));
			roleRepo.save(new Role(ERole.ROLE_USER));

			Usuario user = new Usuario("admin", passwordEncoder.encode("skill252536"));
			user.setRoles(Set.of(roleRepo.findByName(ERole.ROLE_ADMIN).orElse(null)));

			userRepo.save(user);
		};
	}
}
