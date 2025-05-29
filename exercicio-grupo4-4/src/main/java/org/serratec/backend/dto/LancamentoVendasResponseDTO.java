package org.serratec.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LancamentoVendasResponseDTO(LocalDateTime dataVenda,BigDecimal valorVenda,String nomeVendedor) {

}
