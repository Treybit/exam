package com.treybit.exam.web.pruefung;

import com.treybit.exam.domain.Pruefung;

import java.time.LocalDateTime;
import java.util.UUID;

//Factory Methode (nochmal anschauen)
public class PruefungFactory {

    public static Pruefung pruefung() {
        return new Pruefung(
                UUID.randomUUID(),
                "Testinator",
                "Test",
                LocalDateTime.now(),
                "Treybit"
        );
    }
}
