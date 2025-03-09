package com.reinventar.server.core.ports;

import com.reinventar.server.core.model.AppointmentKind;

public interface AppointmentKindRepository {
    AppointmentKind create(long createdBy, String name);
    AppointmentKind get(long id);
    AppointmentKind get(String name);
}
