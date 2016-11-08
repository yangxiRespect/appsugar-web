package org.appsugar.data.solr;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.solr.core.SolrOperations;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactory;
import org.xml.sax.SAXException;

/**
 * solr config
 * @author NewYoung
 * 2016年11月7日下午2:09:28
 */
@Configuration
@EnableSolrRepositories(multicoreSupport = true)
class ApplicationConfig {

	@Bean
	public SolrClient solrClient() throws ParserConfigurationException, IOException, SAXException {
		EmbeddedSolrServerFactory factory = new EmbeddedSolrServerFactory(
				"file:///environment/solr-6.2.1/example/example-DIH/solr");
		EmbeddedSolrServer client = factory.getSolrClient();
		return client;
	}

	@Bean
	public SolrOperations solrTemplate() throws Exception {
		return new SolrTemplate(solrClient());
	}
}