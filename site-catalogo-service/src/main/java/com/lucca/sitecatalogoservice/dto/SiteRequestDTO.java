package com.lucca.sitecatalogoservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SiteRequestDTO(
        @NotBlank(message = "O nome do site é obrigatório e não pode ser vazio.")
        @Size(min = 3, message = "O nome do site deve ter no mínimo 3 caracteres.")
        String nome,

        @NotBlank(message = "O endereço do site é obrigatório.")
        String endereco
) {
}
