package com.wora.qatrat7ayat.models.enumes;

public enum Status {
    APPROVED, PENDING, REJECTED;

    public Status next() {
        return switch (this) {
            case PENDING -> APPROVED;
            case APPROVED -> REJECTED;
            case REJECTED -> PENDING;
        };
    }
}
