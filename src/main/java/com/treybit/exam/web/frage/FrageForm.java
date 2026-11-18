package com.treybit.exam.web.frage;

import com.treybit.exam.domain.Frage;
import com.treybit.exam.web.markdown.MarkdownToHtml;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.UUID;

public class FrageForm {

    MarkdownToHtml markdownToHtml = new MarkdownToHtml();

    @NotBlank(message="Frage darf nicht leer sein")
    private String frage;
    @NotBlank(message="Musterlösung darf nicht leer sein")
    private String loesung;
    @NotBlank(message="Es müssen Punkte angegeben werden")
    @Positive(message = "Punkte müssen eine positive ganze Zahl sein")
    private int punkte;

    public FrageForm(String frage, String loesung, int punkte){
        this.frage = frage;
        this.loesung = loesung;
        this.punkte = punkte;
    }

    public String getFrage() {
        return frage;
    }

    public void setFrage(String frage) {
        this.frage = frage;
    }

    public String getLoesung() {
        return loesung;
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
        String frageMarkdown = frageForm.getFrage();
        String convertFrage = markdownToHtml.convert(frageMarkdown);
        //Lösung als Markdown in HTML konvertieren
        String loesungMarkdown = frageForm.getLoesung();
        String convertLoesung = markdownToHtml.convert(loesungMarkdown);


        Frage frage = new Frage(UUID.randomUUID(), convertFrage, convertLoesung, frageForm.getPunkte());
        return frage;

    }
}
