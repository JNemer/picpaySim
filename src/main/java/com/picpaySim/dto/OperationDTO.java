package com.picpaySim.dto;

import java.math.BigDecimal;

public record OperationDTO(BigDecimal value, Long senderId, Long receiverId) {
}
