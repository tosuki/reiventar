package com.reiventar.server.core.repository;

import com.reiventar.server.core.model.Appointment;

public interface AppointmentRepository {
    Appointment getAppointmentByID(long id);
    Appointment createAppointment(
        long id,
        String author,
        String kind,
        String professional
    );
    void deleteAppointment(long id);
}
