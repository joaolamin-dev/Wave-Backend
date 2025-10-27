package br.com.fatec.wave.service;

import br.com.fatec.wave.dto.AtualizarUsuarioDTO;
import br.com.fatec.wave.dto.CriarUsuarioDTO;
import br.com.fatec.wave.dto.UsuarioResponseDTO;
import br.com.fatec.wave.exception.ResourceNotFoundException;
import br.com.fatec.wave.model.Usuario;
import br.com.fatec.wave.repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceIMPL implements UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioServiceIMPL(UsuarioRepository repository) {
        this.repository = repository;
    }

    protected UsuarioResponseDTO convertToDTO(Usuario usuario) {
        if (usuario == null) {
            return null;
        }
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNome(), usuario.getEmail());
    }

    @Override
    @Transactional (readOnly = true)
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return repository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional (readOnly = true)
    public UsuarioResponseDTO getUsuarioById(Long id) {
        return repository.findById(id)
                .map(this::convertToDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o id: " + id));
    }

    @Override
    @Transactional
    public UsuarioResponseDTO criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        Usuario usuario = new Usuario();
        usuario.setNome(criarUsuarioDTO.getNome());
        usuario.setEmail(criarUsuarioDTO.getEmail());
        usuario.setSenha(criarUsuarioDTO.getSenha());

        if(repository.existsByEmail(criarUsuarioDTO.getEmail())) {
            throw new DataIntegrityViolationException(
                    "Email já existente na plataforma" + criarUsuarioDTO.getEmail());
        }

        Usuario usuarioSalvo = repository.save(usuario);

        return convertToDTO(usuarioSalvo);
    }


    @Override
    @Transactional
    public UsuarioResponseDTO atualizarUsuario(Long id, AtualizarUsuarioDTO atualizarUsuarioDTO) {
        Usuario usuarioExiste = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario não existe com o id: " + id));


        if (atualizarUsuarioDTO.getNome() != null && !atualizarUsuarioDTO.getNome().equals(usuarioExiste.getNome())) {
            usuarioExiste.setNome(atualizarUsuarioDTO.getNome());
        }

        if (atualizarUsuarioDTO.getEmail() != null && !atualizarUsuarioDTO.getEmail().equals(usuarioExiste.getEmail())) {
            if (repository.existsByEmail(atualizarUsuarioDTO.getEmail())) {
                throw new DataIntegrityViolationException(
                        "Email já existente na plataforma: " + atualizarUsuarioDTO.getEmail()
                );
            }
            usuarioExiste.setEmail(atualizarUsuarioDTO.getEmail());
        }

        if (atualizarUsuarioDTO.getSenha() != null && !atualizarUsuarioDTO.getSenha().isEmpty()) {
            // Lembre-se de adicionar a lógica para criptografar a senha antes de salvar
            usuarioExiste.setSenha(atualizarUsuarioDTO.getSenha());
        }

        Usuario usuarioAtualizado = repository.save(usuarioExiste);
        return convertToDTO(usuarioAtualizado);

    }

    @Override
    @Transactional
    public void deletarUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Usuário não existe com o is: " + id);
        }
        repository.deleteById(id);

    }
}
