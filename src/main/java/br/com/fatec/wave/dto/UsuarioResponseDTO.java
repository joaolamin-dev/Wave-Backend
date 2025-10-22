package br.com.fatec.wave.dto;

public class UsuarioResponseDTO {

    private Long id;

    private String nome;

    private String email;

    private String senha;

    public UsuarioResponseDTO(Long id, String nome, String email, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
