package com.treybit.exam.web.pruefung;

import com.treybit.exam.service.PruefungService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(PruefungController.class)
public class PruefungControllerTest {

    @Autowired
    MockMvc mvc;

    @MockitoBean
    PruefungService pruefungService;

    @Test
    @DisplayName("Die Übersichtsseite ist erreichbar")
    void uebersichtsseiteErreichbar() throws Exception{
        MvcResult uebersicht = mvc.perform(get("/pruefungen"))
                .andExpect(status().isOk())
                .andReturn();

        String contentAsString = uebersicht.getResponse().getContentAsString();
        assertThat(contentAsString).contains("Übersicht Prüfungen");
    }

    @Test
    @DisplayName("Das Formular um eine Prüfung zu erstellen ist erreichbar unter /pruefung/erstellen")
    void formularPruefungErstellenErreichbar() throws Exception {
        MvcResult pruefungForm = mvc.perform(get("/pruefung/erstellen"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.model().attributeExists("pruefungForm"))
                .andReturn();

        //hier schauen wir, ob die Seite tatsächlich lädt mit der richtigen Überschrift
        String contentAsString = pruefungForm.getResponse().getContentAsString();
        assertThat(contentAsString).contains("Prüfung erstellen");
    }

    @Test
    @DisplayName("Man kann über das Formular eine Prüfung erstellen mit POST und wird auf prüfungen weitergeleitet")
    void formularPruefungErstellenGehtDurch() throws Exception {

        mvc.perform(post("/pruefung/erstellen")
                .param("titel", "Java Pürfung")
                .param("beschreibung", "Eine tolle Pürfung")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pruefungen"));
    }

    @Test
    @DisplayName("Das Formular geht nicht durch, wenn das 'beschreiben'-Feld leer ist")
    void formularPruefungErstellenGehtNichtDurch() throws Exception {

        mvc.perform(post("/pruefung/erstellen")
                        .param("titel", "Java Pürfung")
                        .param("beschreibung", "")).andExpect(status().isOk());
        //isOk, weil wir erneut auf der Seite landen, da die Fehlermeldung abgefangen wird
    }

}
