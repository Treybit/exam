package com.treybit.exam.domain;

import java.util.Objects;
import java.util.UUID;

//Entität
public class Frage {
    private UUID id;
    private String ueberschrift;
    private String aufgabenstellung;
    private String loesung;
    private int punkte;

    public Frage(UUID id, String ueberschrift, String frage, String loesung, int punkte) {

        this.id = id;
        this.ueberschrift = ueberschrift;
        this.aufgabenstellung = frage;
        this.loesung = loesung;
        this.punkte = punkte;
    }

    public UUID getId() {
        return id;
    }

    public String getAufgabenstellung() {
        return aufgabenstellung;
    }

    public String getLoesung() {
        return loesung;
    }

    public float getPunkte() {
        return punkte;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((Frage) o).id);
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
