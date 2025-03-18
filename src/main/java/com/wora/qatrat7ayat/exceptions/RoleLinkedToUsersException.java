package com.wora.qatrat7ayat.exceptions;

public class RoleLinkedToUsersException extends RuntimeException {
    public RoleLinkedToUsersException(Long roleId) {
        super("Cannot delete role with ID " + roleId + " because it is linked to users.");
    }
}
