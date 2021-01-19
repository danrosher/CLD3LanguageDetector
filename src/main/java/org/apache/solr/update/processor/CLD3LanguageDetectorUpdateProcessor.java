package org.apache.solr.update.processor;

import com.b8.LangDetect;
import com.b8.LangDetectResponse;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Reader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CLD3LanguageDetectorUpdateProcessor extends LanguageIdentifierUpdateProcessor {

    private static final Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public CLD3LanguageDetectorUpdateProcessor(SolrQueryRequest req, SolrQueryResponse rsp, UpdateRequestProcessor next) {
        super(req, rsp, next);
    }

    @Override
    protected List<DetectedLanguage> detectLanguage(Reader solrDocReader) {
        List<DetectedLanguage> languages = new ArrayList<>();
        String content = SolrInputDocumentReader.asString(solrDocReader);
        if (content.length() != 0) {
            LangDetect ld = new LangDetect();
            List<LangDetectResponse> langs = ld.findTopNMostFreqLangs(content,1);
            languages = langs
                .stream()
                .map(d -> new DetectedLanguage(d.getLanguage(),d.getProbability()+0D))
                .collect(Collectors.toList());
        } else {
            log.debug("No input text to detect language from, returning empty list");
        }
        return languages;
    }
}
