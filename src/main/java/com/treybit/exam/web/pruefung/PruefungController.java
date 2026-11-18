package com.treybit.exam.web.pruefung;

import com.treybit.exam.domain.Pruefung;
import com.treybit.exam.service.PruefungService;
import com.treybit.exam.web.exception.PruefungNichtGefundenException;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

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
        Pruefung pruefung = pruefungForm.convertToPruefung(pruefungForm);

        pruefungService.speicherePruefung(pruefung);

        return "redirect:/pruefungen";
    }

    //Prüfung aufrufen mit GET
    @GetMapping("pruefung/{id}")
    public String pruefung(Model model, @PathVariable UUID id) {
        Optional<Pruefung> pruefung = pruefungService.findePruefungMitId(id);

        if(pruefung.isEmpty()){
           throw new PruefungNichtGefundenException();
        }

        model.addAttribute("pruefung", pruefung.get());

        return "pruefung";
    }

    //Prüfung löschen mit GET und POST
    @GetMapping("/pruefung/loeschen/{id}")
    public String loeschePruefung(@PathVariable UUID id, Model model){

        Optional<Pruefung> pruefung = pruefungService.findePruefungMitId(id);

        if(pruefung.isEmpty()){
            throw new PruefungNichtGefundenException();
        }

        model.addAttribute("pruefung", pruefung.get());

        return "loeschepruefung";
    }

    @PostMapping("/pruefung/loeschen/{id}")
    public String loeschePruefung(@PathVariable UUID id) {

        pruefungService.loeschePruefungMitId(id);

        return "redirect:/pruefungen";
    }

}
