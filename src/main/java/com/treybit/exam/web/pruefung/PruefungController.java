package com.treybit.exam.web.pruefung;

import com.treybit.exam.domain.Pruefung;
import com.treybit.exam.service.PruefungService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.*;

@Controller
public class PruefungController {

    private final PruefungService pruefungService;

    public PruefungController(PruefungService pruefungService) {
        this.pruefungService = pruefungService;
    }

    //Alle Prüfungen ansehen
    @GetMapping("/pruefungen")
    public String uebersichtPruefung(Model model){

        model.addAttribute("pruefungen", pruefungService.findeAllePruefungen());
        return "uebersichtpruefung";
    }

    //Neue Prüfung erstellen mit GET und POST
    @GetMapping("/pruefung/erstellen")
    public String erstellePruefung(Model model){

        model.addAttribute("pruefungForm", new PruefungForm());

        return "erstellepruefung";
    }
    @PostMapping("/pruefung/erstellen")
    public String erstellePruefung(Model model, @Valid PruefungForm pruefungForm, Errors errors){

        if(errors.hasErrors()){
            return "erstellepruefung";
        }
        //Brauche ich das?
        //model.addAttribute("pruefungForm", pruefungForm);

        //Das muss noch verschoben werden, erstmal nur eine Notlösung zum Testen
        Pruefung pruefung = new Pruefung(UUID.randomUUID(),
                pruefungForm.getTitel(),
                pruefungForm.getBeschreibung(),
                LocalDateTime.now(),
                "Treybit");

        pruefungService.speicherePruefung(pruefung);

        return "redirect:/pruefungen";
    }

    //Prüfung löschen
    @GetMapping("/pruefung/loeschen/{id}")
    public String loeschePruefung(@PathVariable UUID id, Model model){

        Optional<Pruefung> pruefung = pruefungService.findePruefungMitId(id);

        model.addAttribute("pruefung", pruefung.get());

        return "loeschepruefung";
    }

    @PostMapping("/pruefung/loeschen/{id}")
    public String loeschePruefung(@PathVariable UUID id) {

        pruefungService.loeschePruefungMitId(id);

        return "redirect:/pruefungen";
    }

}
