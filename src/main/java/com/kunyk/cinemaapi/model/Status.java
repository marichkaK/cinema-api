package com.kunyk.cinemaapi.model;

public enum Status {
    FREE("free"), BOOKED("booked");

    private String status;

    Status(String status) {
        this.status = status;
    }
}
