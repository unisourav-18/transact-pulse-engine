package com.amex.transactpulse.dto;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record TransactionRequest(
    @NotBlank(message = "transactionId is mandatory")
    String transactionId,

    @NotBlank(message = "accountId is mandatory")
    @Pattern(regexp = "^[0-9]{10,16}$", message = "accountId must be 10-16 digits")
    String accountId,

    @NotNull(message = "amount is required")
    @DecimalMin(value = "0.01", message = "amount must be greater than zero")
    BigDecimal amount,

    @NotBlank(message = "currency is mandatory")
    @Size(min = 3, max = 3, message = "currency must be a 3-letter ISO code")
    String currency,

    @NotBlank(message = "merchantCategoryCode is mandatory")
    String merchantCategoryCode
) {}