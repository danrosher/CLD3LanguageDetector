package org.apache.solr.update.processor;

import org.apache.solr.common.params.SolrParams;
import org.apache.solr.common.util.NamedList;
import org.apache.solr.request.SolrQueryRequest;
import org.apache.solr.response.SolrQueryResponse;
import org.apache.solr.util.SolrPluginUtils;

public class CLD3LanguageDetectorUpdateProcessorFactory extends
    UpdateRequestProcessorFactory {

    protected SolrParams defaults;
    protected SolrParams appends;
    protected SolrParams invariants;

    @Override
    public void init(NamedList args) {
        if (args != null) {
            Object o;
            o = args.get("defaults");
            if (o instanceof NamedList) {
                defaults = ((NamedList) o).toSolrParams();
            } else {
                defaults = args.toSolrParams();
            }
            o = args.get("appends");
            if (o instanceof NamedList) {
                appends = ((NamedList) o).toSolrParams();
            }
            o = args.get("invariants");
            if (o instanceof NamedList) {
                invariants = ((NamedList) o).toSolrParams();
            }
        }
    }

    @Override
    public UpdateRequestProcessor getInstance(SolrQueryRequest req, SolrQueryResponse rsp, UpdateRequestProcessor next) {
        // Process defaults, appends and invariants if we got a request
        if(req != null) SolrPluginUtils.setDefaults(req, defaults, appends, invariants);
        return new CLD3LanguageDetectorUpdateProcessor(req, rsp, next);
    }

}
