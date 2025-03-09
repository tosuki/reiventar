package com.reiventar.server.core.ports;

import java.util.ArrayList;

import com.reiventar.server.core.model.Appointment;
import com.reiventar.server.core.model.AppointmentState;

public interface AppointmentRepository {
    Appointment create(long createdBy, long date, long kind, AppointmentState state, long professional, long client);
    
    ArrayList<Appointment> getByProfessional(long professionalId);
    long setState(long professionalId, AppointmentState state);
    void delete(long professionalId);
}
