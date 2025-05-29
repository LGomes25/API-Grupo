package org.serratec.backend.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class LancamentoVendasRequestDTO {



    @NotNull(message = "Valor da venda não pode ser nulo")
    @Positive
    private BigDecimal valorVenda;

    @NotBlank(message = "Nome do vendedor não pode ser nulo ou vazio")
    private String nomeVendedor;
	
}
