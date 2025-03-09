package com.reinventar.server.core.model;

public enum AppointmentState {
    CONFIRMED(1), 
    UNCONFIRMED(2), 
    DONE(3);

    private final int AppointmentStateId;

    AppointmentState(int AppointmentStateId) {
        this.AppointmentStateId = AppointmentStateId;
    }

    public int getAppointmentStateId() {
        return AppointmentStateId;
    }
}
