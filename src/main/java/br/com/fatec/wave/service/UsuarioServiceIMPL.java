package br.com.fatec.wave.service;

import br.com.fatec.wave.dto.AtualizarUsuarioDTO;
import br.com.fatec.wave.dto.CriarUsuarioDTO;
import br.com.fatec.wave.dto.UsuarioResponseDTO;

import java.util.List;

public class UsuarioServiceIMPL implements UsuarioService {
    @Override
    public List<UsuarioResponseDTO> getAllUsuarios() {
        return List.of();
    }

    @Override
    public UsuarioResponseDTO getUsuarioById(Long id) {
        return null;
    }

    @Override
    public UsuarioResponseDTO criarUsuario(CriarUsuarioDTO criarUsuarioDTO) {
        return null;
    }

    @Override
    public UsuarioResponseDTO atualizarUsuario(AtualizarUsuarioDTO atualizarUsuarioDTO) {
        return null;
    }

    @Override
    public void deletarUsuario(Long id) {

    }
}
