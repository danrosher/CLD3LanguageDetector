package org.apache.solr.update.processor;

import org.apache.solr.SolrTestCaseJ4;
import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.request.SolrQueryRequest;
import org.junit.Test;

public class CLD3LanguageDetectorUpdateProcessorFactoryTest extends LanguageIdentifierUpdateProcessorFactoryTestCase {

    @Override
    protected LanguageIdentifierUpdateProcessor createLangIdProcessor(ModifiableSolrParams parameters) throws Exception {

        SolrQueryRequest req = LanguageIdentifierUpdateProcessorFactoryTestCase._parser.buildRequestFrom(SolrTestCaseJ4.h.getCore(), new ModifiableSolrParams(), null);
        CLD3LanguageDetectorUpdateProcessorFactory factory = new CLD3LanguageDetectorUpdateProcessorFactory();
        factory.init(parameters.toNamedList());
        return (CLD3LanguageDetectorUpdateProcessor)factory.getInstance(req, LanguageIdentifierUpdateProcessorFactoryTestCase.resp, null);
    }

    @Test
    @Override
    public void testLangIdGlobal() throws Exception {
        ModifiableSolrParams parameters = new ModifiableSolrParams();
        parameters.add("langid.fl", "name,subject");
        parameters.add("langid.langField", "language_s");
        liProcessor = createLangIdProcessor(parameters);

        assertLang("en", "id", "1en", "name", "Lucene", "subject", "Apache Lucene is a free/open source information retrieval software library, originally created in Java by Doug Cutting. It is supported by the Apache Software Foundation and is released under the Apache Software License.");
        assertLang("sv", "id", "2sv", "name", "Maven", "subject", "Apache Maven är ett verktyg utvecklat av Apache Software Foundation och används inom systemutveckling av datorprogram i programspråket Java. Maven används för att automatiskt paketera (bygga) programfilerna till en distribuerbar enhet. Maven används inom samma område som Apache Ant men dess byggfiler är deklarativa till skillnad ifrån Ants skriptbaserade.");
        assertLang("es", "id", "3es", "name", "Lucene", "subject", "Lucene es un API de código abierto para recuperación de información, originalmente implementada en Java por Doug Cutting. Está apoyado por el Apache Software Foundation y se distribuye bajo la Apache Software License. Lucene tiene versiones para otros lenguajes incluyendo Delphi, Perl, C#, C++, Python, Ruby y PHP.");
        assertLang("ru", "id", "4ru", "name", "Lucene", "subject", "The Apache Lucene — это свободная библиотека для высокоскоростного полнотекстового поиска, написанная на Java. Может быть использована для поиска в интернете и других областях компьютерной лингвистики (аналитическая философия).");
        assertLang("de", "id", "5de", "name", "Lucene", "subject", "Lucene ist ein Freie-Software-Projekt der Apache Software Foundation, das eine Suchsoftware erstellt. Durch die hohe Leistungsfähigkeit und Skalierbarkeit können die Lucene-Werkzeuge für beliebige Projektgrößen und Anforderungen eingesetzt werden. So setzt beispielsweise Wikipedia Lucene für die Volltextsuche ein. Zudem verwenden die beiden Desktop-Suchprogramme Beagle und Strigi eine C#- bzw. C++- Portierung von Lucene als Indexer.");
    }
}
