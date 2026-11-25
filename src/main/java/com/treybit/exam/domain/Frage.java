package com.treybit.exam.domain;

import java.util.Objects;
import java.util.UUID;

//Entität
class Frage {
    private UUID id;
    private String ueberschrift;
    private String aufgabenstellung;
    private String loesung;
    private int punkte;

    public Frage(UUID id, String ueberschrift, String aufgabenstellung, String loesung, int punkte) {

        this.id = id;
        this.ueberschrift = ueberschrift;
        this.aufgabenstellung = aufgabenstellung;
        this.loesung = loesung;
        this.punkte = punkte;
    }

    public Frage(Frage frage){
        this.id = frage.id;
        this.ueberschrift = frage.ueberschrift;
        this.aufgabenstellung = frage.aufgabenstellung;
        this.loesung = frage.loesung;
        this.punkte = frage.punkte;
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

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public void setAufgabenstellung(String aufgabenstellung) {
        this.aufgabenstellung = aufgabenstellung;
    }

    public void setLoesung(String loesung) {
        this.loesung = loesung;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

    @Override
    public String toString() {
        return "Frage{" +
                "id=" + id +
                ", ueberschrift='" + ueberschrift + '\'' +
                ", aufgabenstellung='" + aufgabenstellung + '\'' +
                ", loesung='" + loesung + '\'' +
                ", punkte=" + punkte +
                '}';
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
