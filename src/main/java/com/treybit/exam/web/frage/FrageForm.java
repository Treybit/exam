package com.treybit.exam.web.frage;

import com.treybit.exam.domain.Frage;
import com.treybit.exam.web.markdown.MarkdownToHtml;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.UUID;

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

    public Frage convertToFrage(FrageForm frageForm){

        //Frage als Markdown in HTML konvertieren
        String aufgabenstellungMarkdown = frageForm.getAufgabenstellung();
        String convertAufgabenstellung = markdownToHtml.convert(aufgabenstellungMarkdown);
        //Lösung als Markdown in HTML konvertieren
        String loesungMarkdown = frageForm.getLoesung();
        String convertLoesung = markdownToHtml.convert(loesungMarkdown);

        Frage frage = new Frage(UUID.randomUUID(),
                frageForm.getUeberschrift(),
                convertAufgabenstellung,
                convertLoesung,
                frageForm.getPunkte());

        return frage;

    }
}
