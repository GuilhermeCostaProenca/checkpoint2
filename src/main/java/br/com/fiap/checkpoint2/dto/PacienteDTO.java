package br.com.fiap.checkpoint2.dto;

import java.time.LocalDate;

public record PacienteDTO(
    String nome,
    String endereco,
    String bairro,
    String email,
    String telefoneCompleto,
    LocalDate dataNascimento
) {}
