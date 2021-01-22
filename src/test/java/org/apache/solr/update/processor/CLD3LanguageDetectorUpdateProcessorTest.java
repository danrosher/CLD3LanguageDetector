package org.apache.solr.update.processor;

import org.apache.solr.common.params.ModifiableSolrParams;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.schema.IndexSchema;
import org.junit.Test;
import org.powermock.api.mockito.PowerMockito;

import java.io.StringReader;
import java.util.List;

public class CLD3LanguageDetectorUpdateProcessorTest {

    private CLD3LanguageDetectorUpdateProcessor processor = null;

    @Test
    public void detectLanguageTest() {

        SolrQueryRequest mockReq = PowerMockito.mock(SolrQueryRequest.class);
        IndexSchema mockIndexSchema = PowerMockito.mock(IndexSchema.class);
        PowerMockito.when(mockIndexSchema.getUniqueKeyField())
            .thenReturn(null);
        PowerMockito.when(mockReq.getSchema())
            .thenReturn(mockIndexSchema);
        ModifiableSolrParams params = new ModifiableSolrParams();
        params.add(LangIdParams.FIELDS_PARAM, "mock");
        PowerMockito.when(mockReq.getParams())
            .thenReturn(params);

        processor = new CLD3LanguageDetectorUpdateProcessor(mockReq, null, null);

        assertLang("no", "Lucene er et fri/åpen kildekode programvarebibliotek for informasjonsgjenfinning, opprinnelig utviklet i programmeringsspråket Java av Doug Cutting. Lucene støttes av Apache Software Foundation og utgis under Apache-lisensen.");
        assertLang("en", "Apache Lucene is a free/open source information retrieval software library, originally created in Java by Doug Cutting. It is supported by the Apache Software Foundation and is released under the Apache Software License.");
        assertLang("sv", "Apache Maven är ett verktyg utvecklat av Apache Software Foundation och används inom systemutveckling av datorprogram i programspråket Java. Maven används för att automatiskt paketera (bygga) programfilerna till en distribuerbar enhet. Maven används inom samma område som Apache Ant men dess byggfiler är deklarativa till skillnad ifrån Ants skriptbaserade.");
        assertLang("es", "El español, como las otras lenguas romances, es una continuación moderna del latín hablado (denominado latín vulgar), desde el siglo III, que tras el desmembramiento del Imperio romano fue divergiendo de las otras variantes del latín que se hablaban en las distintas provincias del antiguo Imperio, dando lugar mediante una lenta evolución a las distintas lenguas romances. Debido a su propagación por América, el español es, con diferencia, la lengua romance que ha logrado mayor difusión.");
        assertLang("th", "บทความคัดสรรเดือนนี้ อันเนอลีส มารี อันเนอ ฟรังค์ หรือมักรู้จักในภาษาไทยว่า แอนน์ แฟรงค์ เป็นเด็กหญิงชาวยิว เกิดที่เมืองแฟรงก์เฟิร์ต ประเทศเยอรมนี เธอมีชื่อเสียงโด่งดังในฐานะผู้เขียนบันทึกประจำวันซึ่งต่อมาได้รับการตีพิมพ์เป็นหนังสือ บรรยายเหตุการณ์ขณะหลบซ่อนตัวจากการล่าชาวยิวในประเทศเนเธอร์แลนด์ ระหว่างที่ถูกเยอรมนีเข้าครอบครองในช่วงสงครามโลกครั้งที่สอง");
        assertLang("ru", "The Apache Lucene — это свободная библиотека для высокоскоростного полнотекстового поиска, написанная на Java. Может быть использована для поиска в интернете и других областях компьютерной лингвистики (аналитическая философия).");
        assertLang("de", "Lucene ist ein Freie-Software-Projekt der Apache Software Foundation, das eine Suchsoftware erstellt. Durch die hohe Leistungsfähigkeit und Skalierbarkeit können die Lucene-Werkzeuge für beliebige Projektgrößen und Anforderungen eingesetzt werden. So setzt beispielsweise Wikipedia Lucene für die Volltextsuche ein. Zudem verwenden die beiden Desktop-Suchprogramme Beagle und Strigi eine C#- bzw. C++- Portierung von Lucene als Indexer.");
        assertLang("fr", "Lucene est un moteur de recherche libre écrit en Java qui permet d'indexer et de rechercher du texte. C'est un projet open source de la fondation Apache mis à disposition sous licence Apache. Il est également disponible pour les langages Ruby, Perl, C++, PHP.");
        assertLang("nl", "Lucene is een gratis open source, tekst gebaseerde information retrieval API van origine geschreven in Java door Doug Cutting. Het wordt ondersteund door de Apache Software Foundation en is vrijgegeven onder de Apache Software Licentie. Lucene is ook beschikbaar in andere programeertalen zoals Perl, C#, C++, Python, Ruby en PHP.");
        assertLang("it", "Lucene è una API gratuita ed open source per il reperimento di informazioni inizialmente implementata in Java da Doug Cutting. È supportata dall'Apache Software Foundation ed è resa disponibile con l'Apache License. Lucene è stata successivamente reimplementata in Perl, C#, C++, Python, Ruby e PHP.");
        assertLang("pt", "Apache Lucene, ou simplesmente Lucene, é um software de busca e uma API de indexação de documentos, escrito na linguagem de programação Java. É um software de código aberto da Apache Software Foundation licenciado através da licença Apache.");
        assertLang("ca", "El català posseeix dos estàndards principals: el regulat per l'Institut d'Estudis Catalans, o estàndard general, que pren com a base l'ortografia establerta per Pompeu Fabra amb els trets gramaticals i ortogràfics característics del català central; i el regulat per l'Acadèmia Valenciana de la Llengua, estàndard d'àmbit restringit, centrat en l'estandardització del valencià i que pren com a base les Normes de Castelló, és a dir, l'ortografia de Pompeu Fabra però més adaptada a la pronúncia del català occidental i als trets que caracteritzen els dialectes valencians.");
        assertLang("be", "Наступнай буйной дзяржавай на беларускай зямлі было Вялікае княства Літоўскае, Рускае і Жамойцкае (ВКЛ). Падчас стварэння і пачатковага развіцця гэтай дзяржавы найбуйнейшым і асноўным яе цэнтрам быў Новагародак. Акрамя сучасных земляў Беларусі, у склад гэтай дзяржавы ўваходзілі таксама землі сучаснай Літвы, паўночная частка сучаснай Украіны і частка сучаснай Расіі.");
        assertLang("eo", "La vortprovizo de Esperanto devenas plejparte el la okcidenteŭropaj lingvoj, dum ĝia sintakso kaj morfologio montras ankaŭ slavlingvan influon. La morfemoj ne ŝanĝiĝas kaj oni povas ilin preskaŭ senlime kombini, kreante diverssignifajn vortojn, Esperanto do havas multajn kunaĵojn kun la analizaj lingvoj, al kiuj apartenas ekzemple la ĉina; kontraŭe la interna strukturo de Esperanto certagrade respegulas la aglutinajn lingvojn, kiel la japanan, svahilan aŭ turkan.");
        assertLang("gl", "A cifra de falantes medrou axiña durante as décadas seguintes, nun principio no Imperio ruso e na Europa oriental, logo na Europa occidental, América, China e no Xapón. Nos primeiros anos do movemento, os esperantistas mantiñan contacto por correspondencia, pero en 1905 o primeiro Congreso Universal de Esperanto levouse a cabo na cidade francesa de Boulogne-sur-Mer. Dende entón, os congresos mundiais organizáronse nos cinco continentes ano tras ano agás durante as dúas Guerras Mundiais.");
        assertLang("ro", "La momentul destrămării Uniunii Sovietice și a înlăturării regimului comunist instalat în România (1989), țara a inițiat o serie de reforme economice și politice. După un deceniu de probleme economice, România a introdus noi reforme economice de ordin general (precum cota unică de impozitare, în 2005) și a aderat la Uniunea Europeană la 1 ianuarie 2007.");
        assertLang("sk", "Boli vytvorené dva národné parlamenty - Česká národná rada a Slovenská národná rada a spoločný jednokomorový česko-slovenský parlament bol premenovaný z Národného zhromaždenia na Federálne zhromaždenie s dvoma komorami - Snemovňou ľudu a Snemovňu národov.");
        assertLang("sl", "Slovenska Wikipedija je različica spletne enciklopedije Wikipedije v slovenskem jeziku. Projekt slovenske Wikipedije se je začel 26. februarja 2002 z ustanovitvijo njene spletne strani, njen pobudnik pa je bil uporabnik Jani Melik.");
        assertLang("uk", "Народно-господарський комплекс країни включає такі види промисловості як важке машинобудування, чорна та кольорова металургія, суднобудування, виробництво автобусів, легкових та вантажних автомобілів, тракторів та іншої сільськогосподарської техніки, тепловозів, верстатів, турбін, авіаційних двигунів та літаків, обладнання для електростанцій, нафто-газової та хімічної промисловості тощо. Крім того, Україна є потужним виробником електроенергії. Україна має розвинуте сільське господарство і займає одне з провідних місць серед експортерів деяких видів сільськогосподарської продукції і продовольства (зокрема, соняшникової олії).");
        assertLang("no", "Lucene er et fri/åpen kildekode programvarebibliotek for informasjonsgjenfinning, opprinnelig utviklet i programmeringsspråket Java av Doug Cutting. Lucene støttes av Apache Software Foundation og utgis under Apache-lisensen.");
        assertLang("en", "Apache Lucene is a free/open source information retrieval software library, originally created in Java by Doug Cutting. It is supported by the Apache Software Foundation and is released under the Apache Software License.");
        assertLang("sv", "Apache Maven är ett verktyg utvecklat av Apache Software Foundation och används inom systemutveckling av datorprogram i programspråket Java. Maven används för att automatiskt paketera (bygga) programfilerna till en distribuerbar enhet. Maven används inom samma område som Apache Ant men dess byggfiler är deklarativa till skillnad ifrån Ants skriptbaserade.");
        assertLang("es", "Lucene es un API de código abierto para recuperación de información, originalmente implementada en Java por Doug Cutting. Está apoyado por el Apache Software Foundation y se distribuye bajo la Apache Software License. Lucene tiene versiones para otros lenguajes incluyendo Delphi, Perl, C#, C++, Python, Ruby y PHP.");
        assertLang("ja", "日本語（にほんご、にっぽんご）は主として、日本で使用されてきた言語である。日本国は法令上、公用語を明記していないが、事実上の公用語となっており、学校教育の「国語」で教えられる。");
        assertLang("th", "บทความคัดสรรเดือนนี้ อันเนอลีส มารี อันเนอ ฟรังค์ หรือมักรู้จักในภาษาไทยว่า แอนน์ แฟรงค์ เป็นเด็กหญิงชาวยิว เกิดที่เมืองแฟรงก์เฟิร์ต ประเทศเยอรมนี เธอมีชื่อเสียงโด่งดังในฐานะผู้เขียนบันทึกประจำวันซึ่งต่อมาได้รับการตีพิมพ์เป็นหนังสือ บรรยายเหตุการณ์ขณะหลบซ่อนตัวจากการล่าชาวยิวในประเทศเนเธอร์แลนด์ ระหว่างที่ถูกเยอรมนีเข้าครอบครองในช่วงสงครามโลกครั้งที่สอง");
        assertLang("ru", "The Apache Lucene — это свободная библиотека для высокоскоростного полнотекстового поиска, написанная на Java. Может быть использована для поиска в интернете и других областях компьютерной лингвистики (аналитическая философия).");
        assertLang("de", "Lucene ist ein Freie-Software-Projekt der Apache Software Foundation, das eine Suchsoftware erstellt. Durch die hohe Leistungsfähigkeit und Skalierbarkeit können die Lucene-Werkzeuge für beliebige Projektgrößen und Anforderungen eingesetzt werden. So setzt beispielsweise Wikipedia Lucene für die Volltextsuche ein. Zudem verwenden die beiden Desktop-Suchprogramme Beagle und Strigi eine C#- bzw. C++- Portierung von Lucene als Indexer.");
        assertLang("fr", "Lucene est un moteur de recherche libre écrit en Java qui permet d'indexer et de rechercher du texte. C'est un projet open source de la fondation Apache mis à disposition sous licence Apache. Il est également disponible pour les langages Ruby, Perl, C++, PHP.");
        assertLang("nl", "Lucene is een gratis open source, tekst gebaseerde information retrieval API van origine geschreven in Java door Doug Cutting. Het wordt ondersteund door de Apache Software Foundation en is vrijgegeven onder de Apache Software Licentie. Lucene is ook beschikbaar in andere programeertalen zoals Perl, C#, C++, Python, Ruby en PHP.");
        assertLang("it", "Lucene è una API gratuita ed open source per il reperimento di informazioni inizialmente implementata in Java da Doug Cutting. È supportata dall'Apache Software Foundation ed è resa disponibile con l'Apache License. Lucene è stata successivamente reimplementata in Perl, C#, C++, Python, Ruby e PHP.");
        assertLang("pt", "Apache Lucene, ou simplesmente Lucene, é um software de busca e uma API de indexação de documentos, escrito na linguagem de programação Java. É um software de código aberto da Apache Software Foundation licenciado através da licença Apache.");
    }

    private void assertLang(String no, String str) {
        List<DetectedLanguage> result = processor.detectLanguage(new StringReader(str));
        assert (no.equals(result.get(0)
            .getLangCode()));
    }
}