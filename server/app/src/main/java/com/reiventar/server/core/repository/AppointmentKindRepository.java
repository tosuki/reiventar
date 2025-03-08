package com.reiventar.server.core.repository;

import com.reiventar.server.core.model.AppointmentKind;

public interface AppointmentKindRepository {
    AppointmentKind getAppointmentKindById(long id);
    void createAppointmentKind(
        long id,
        String name,
        String author,
        long createdAt,
        long updatedAt
    );
    void deleteAppointmentKind(long id);
}
