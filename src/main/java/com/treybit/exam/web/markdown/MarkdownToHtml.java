package com.treybit.exam.web.markdown;

import com.vladsch.flexmark.util.ast.Node;
import com.vladsch.flexmark.html.HtmlRenderer;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.util.data.MutableDataSet;
import org.owasp.html.PolicyFactory;
import org.owasp.html.Sanitizers;

//Gehört das in den Web? Nur für die xyzForm Klassen wichtig theoretisch
public class MarkdownToHtml {

    public String convert(String markdown) {
        // Optionen konfigurieren
        MutableDataSet options = new MutableDataSet(); // z.B. # Überschrift -> HTML-h1 oder *Kursiv* -> HTML-em

        // Parser und Renderer bauen
        Parser parser = Parser.builder(options).build();
        HtmlRenderer renderer = HtmlRenderer.builder(options).build();

        // Markdown parsen
        Node document = parser.parse(markdown);

        String html = renderer.render(document);

        // HTML bereinigen
        PolicyFactory policy = Sanitizers.FORMATTING
                .and(Sanitizers.BLOCKS)
                .and(Sanitizers.LINKS)
                .and(Sanitizers.IMAGES);

        return policy.sanitize(html);
    }
}
