package com.wora.qatrat7ayat.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(int code, LocalDateTime timestamp, String message, Object errors) {
}
