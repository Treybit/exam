package com.treybit.exam.web.pruefung;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.UUID;

public class PruefungForm {

    @NotBlank(message="Titel darf nicht leer sein")
    private String titel;
    @NotBlank(message="Beschreibung darf nicht leer sein")
    private String beschreibung;

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getTitel() {
        return titel;
    }

    public String getBeschreibung() {
        return beschreibung;
    }
}
