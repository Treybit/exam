package com.treybit.exam.domain;

import java.util.UUID;

public class Frage {
    private UUID id;
    private String frage;
    private String loesung;
    private float punkte;

    public Frage(UUID id, String frage, String loesung, float punkte) {
        this.id = id;
        this.frage = frage;
        this.loesung = loesung;
        this.punkte = punkte;
    }

    public UUID getId() {
        return id;
    }

    public String getFrage() {
        return frage;
    }

    public String getLoesung() {
        return loesung;
    }

    public float getPunkte() {
        return punkte;
    }
}
