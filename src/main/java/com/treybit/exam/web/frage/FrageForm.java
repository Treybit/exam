package com.treybit.exam.web.frage;

import com.treybit.exam.service.markdown.MarkdownToHtml;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class FrageForm {

    MarkdownToHtml markdownToHtml = new MarkdownToHtml();

    @NotBlank(message="Überschrift darf nicht leer sein")
    private String ueberschrift;
    @NotBlank(message="Frage darf nicht leer sein")
    private String aufgabenstellung;
    @NotBlank(message="Musterlösung darf nicht leer sein")
    private String loesung;
    @NotBlank(message="Es müssen Punkte angegeben werden")
    @Positive(message = "Punkte müssen eine positive ganze Zahl sein")
    private int punkte;

    public FrageForm(String aufgabenstellung, String loesung, int punkte){
        this.aufgabenstellung = aufgabenstellung;
        this.loesung = loesung;
        this.punkte = punkte;
    }

    public String getAufgabenstellung() {
        return aufgabenstellung;
    }

    public void setAufgabenstellung(String aufgabenstellung) {
        this.aufgabenstellung = aufgabenstellung;
    }

    public String getLoesung() {
        return loesung;
    }

    public MarkdownToHtml getMarkdownToHtml() {
        return markdownToHtml;
    }

    public void setMarkdownToHtml(MarkdownToHtml markdownToHtml) {
        this.markdownToHtml = markdownToHtml;
    }

    public String getUeberschrift() {
        return ueberschrift;
    }

    public void setUeberschrift(String ueberschrift) {
        this.ueberschrift = ueberschrift;
    }

    public void setLoesung(String loesung) {
        this.loesung = loesung;
    }

    public int getPunkte() {
        return punkte;
    }

    public void setPunkte(int punkte) {
        this.punkte = punkte;
    }

}
