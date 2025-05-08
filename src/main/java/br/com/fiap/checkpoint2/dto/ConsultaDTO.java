package br.com.fiap.checkpoint2.dto;

import java.time.LocalDateTime;

public record ConsultaDTO(
    Long profissionalId,
    Long pacienteId,
    LocalDateTime dataConsulta,
    String statusConsulta,
    Integer quantidadeHoras,
    Double valorConsulta
) {}
