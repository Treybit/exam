package com.treybit.exam.domain;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

//AggregatRoot //Benötige ich ein PrüfungsBuilder??
public class Pruefung {
    private UUID id;
    private String titel;
    private String beschreibung;
    private LocalDateTime erstelltAm;
    private String erstelltVon;

    private List<Frage> fragen = new ArrayList<>();


    public Pruefung(UUID id, String titel, String beschreibung, LocalDateTime erstelltAm, String erstelltVon) {
        this.id = id;
        this.titel = titel;
        this.beschreibung = beschreibung;
        this.erstelltAm = erstelltAm;
        this.erstelltVon = erstelltVon;
    }

    public UUID getId() {
        return id;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public LocalDateTime getErstelltAm() {
        return erstelltAm;
    }

    public String getErstelltAmToString() {
        return erstelltAm.format(
                DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm")
        );
    }

    public String getErstelltVon() {
        return erstelltVon;
    }

    //Alles mit dem Objekt Fragen:

    public int getAnzahlFragen(){
        return fragen.size();
    }

    public List<Frage> getFragen() {

        return fragen.stream().map(Frage::new).toList();
    }

    public void addFrage(UUID id, String ueberschrift, String aufgabenstellung, String loesung, int punkte) {
        fragen.add(new Frage(id, ueberschrift,aufgabenstellung, loesung, punkte));
    }

    public void removeFrage(UUID id) {

        //Fehlerbehandlung
        Frage frage = fragen.stream().filter(f -> f.getId().equals(id)).findFirst().orElseThrow();
        fragen.remove(frage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return id.equals(((Pruefung) o).id);
    }

    @Override
    public String toString() {
        return "Pruefung{" +
                "fragen=" + fragen +
                ", titel='" + titel + '\'' +
                '}';
    }

    @Override
    public int hashCode() { return Objects.hash(id); }
}
