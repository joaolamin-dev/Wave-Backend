package br.com.fatec.wave.repository;

import br.com.fatec.wave.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
}
