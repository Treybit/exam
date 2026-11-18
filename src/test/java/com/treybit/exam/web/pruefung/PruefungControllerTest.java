package com.treybit.exam.web.pruefung;

import com.treybit.exam.domain.Pruefung;
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
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(PruefungController.class)
public class PruefungControllerTest {

    @Autowired
    MockMvc mvc;

    @Autowired
    MockMvcTester mockMvcTester;

    @MockitoBean
    PruefungService pruefungService;

    @Test
    @DisplayName("Die Übersichtsseite ist erreichbar und es werden die Prüfungen angezeigt und es wird geschaut, ob der Titel vorhanden ist")
    void uebersichtsseiteErreichbar() throws Exception{

        //Hier nochmal schauen, ob das wirklich ein sinnvoller test ist.
        Pruefung pruefung1 = PruefungFactory.pruefung();

        when(pruefungService.findeAllePruefungen())
                .thenReturn(new ArrayList<>(List.of(pruefung1)));

        MvcResult uebersicht = mvc.perform(get("/pruefungen"))
                .andExpect(status().isOk())
                .andReturn();

        ModelAndView modelAndView = uebersicht.getModelAndView();
        assertThat((List<Pruefung>)modelAndView.getModel().get("pruefungen"))
                .contains(pruefung1);
        assertThat(uebersicht.getResponse().getContentAsString()).contains("Testinator");
    }

    @Test
    @DisplayName("Das Formular um eine Prüfung zu erstellen ist erreichbar unter /pruefung/erstellen")
    void formularPruefungErstellenErreichbar() throws Exception {
        MvcResult pruefungForm = mvc.perform(get("/pruefung/erstellen"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("pruefungForm"))
                .andReturn();

        //hier schauen wir, ob die Seite tatsächlich lädt mit der richtigen Überschrift
        String contentAsString = pruefungForm.getResponse().getContentAsString();
        assertThat(contentAsString).contains("Prüfung erstellen");
    }

    @Test
    @DisplayName("Man kann über das Formular eine Prüfung erstellen mit POST und wird auf prüfungen weitergeleitet")
    void formularPruefungErstellenGehtDurch() throws Exception {

        //Parameter umwandeln
        mvc.perform(post("/pruefung/erstellen")
                .param("titel", "Java Pürfung")
                .param("beschreibung", "Eine tolle Pürfung")).andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/pruefungen"));
    }

    @Test
    @DisplayName("Das Formular geht nicht durch, wenn das 'beschreiben'-Feld leer ist")
    void formularPruefungErstellenGehtNichtDurch() throws Exception {
        //Parameter überprüfen und ggf. encoden
        mvc.perform(post("/pruefung/erstellen")
                        .param("titel", "Java Pürfung")
                        .param("beschreibung", ""))
                .andExpect(model().attributeHasFieldErrors("pruefungForm", "beschreibung"))
                .andExpect(status().isOk());
        //isOk, weil wir erneut auf der Seite landen, da die Fehlermeldung abgefangen wird
        //Im Controller als Rückgabetyp Model and View einsetzen, aber später
    }

    @Test
    @DisplayName("Die Seite um eine Pürfung, mit einer bestimmten UUID, anzuschauen geht auf")
    void pruefungExistiert() throws Exception{

        Pruefung pruefung = PruefungFactory.pruefung();
        UUID id = pruefung.getId();

        when(pruefungService.findePruefungMitId(id)).thenReturn(Optional.of(pruefung));

        assertThat(mockMvcTester.get().uri("/pruefung/{id}", id))
                .hasStatusOk()
                .bodyText()
                .contains("Testinator");
    }

    @Test
    @DisplayName("Die Seite um eine Pürfung, mit einer bestimmten UUID, anzuschauen geht nicht auf, da ID nicht existiert")
    void pruefungExistiertNicht() throws Exception{

        //Könnte es aus Zufall eine existsierende Prüfung aufrufen?
        //Wie kann ich konkret hier die Branch aufrufen?

        UUID unbekannteUUID = UUID.randomUUID();

        when(pruefungService.findePruefungMitId(unbekannteUUID))
                .thenReturn(Optional.empty());

        assertThat(mockMvcTester.get().uri("/pruefung/{unbekannteUUID}", unbekannteUUID))
                .hasStatus4xxClientError()
                .bodyText()
                .contains("404");
    }

    //Test fürs löschen schreiben
    @Test
    @DisplayName("Die Seite um eine Prüfung zu löschen geht auf mit einer ID und GET")
    void pruefungLoeschenGehtAuf() throws Exception{

        Pruefung pruefung = PruefungFactory.pruefung();

        when(pruefungService.findePruefungMitId(pruefung.getId())).thenReturn(Optional.of(pruefung));

        assertThat(mockMvcTester.get().uri("/pruefung/loeschen/{id}", pruefung.getId()))
                .hasStatus2xxSuccessful()
                .bodyText()
                .contains("Testinator");
    }

}
