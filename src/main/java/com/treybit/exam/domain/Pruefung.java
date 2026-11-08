package com.treybit.exam.domain;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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

    public String getErstelltVon() {
        return erstelltVon;
    }

    public List<Frage> getFragen() {
        //Eine unveränderte Liste, damit man die ursprüngliche nicht bearbeiten kann
        return List.copyOf(fragen);
    }

    public void addFrage(Frage frage) {
        fragen.add(frage);
    }

    public void removeFrage(Frage frage) {
        fragen.remove(frage);
    }
}
