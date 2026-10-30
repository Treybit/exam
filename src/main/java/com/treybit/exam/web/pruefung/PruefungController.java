package com.treybit.exam.web.pruefung;

import com.treybit.exam.domain.Pruefung;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Controller
public class PruefungController {

    //Alle Prüfungen ansehen
    @GetMapping("/pruefungen")
    public String uebersichtPruefung(Model model){

        //model.addAttribute("pruefungen", pruefungen);
        return "uebersichtpruefung";
    }

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
        model.addAttribute("pruefungForm", pruefungForm);
        System.out.println(pruefungForm.getTitel() + " " + pruefungForm.getBeschreibung());
        return "redirect:/";
    }

}
