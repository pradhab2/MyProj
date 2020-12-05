package com.park.parking.entity;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Integer id) {
        super("Entry not found with id " + id);
    }
}

