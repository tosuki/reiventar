package com.reiventar.server.core.ports;

import com.reiventar.server.core.model.AppointmentKind;

public interface AppointmentKindRepository {
    AppointmentKind create(long createdBy, String name);
    AppointmentKind get(long id);
    AppointmentKind get(String name);
}
