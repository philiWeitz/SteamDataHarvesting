package org.uta.steam.bl.crawler;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class AbstractWebCrawler {

	private static Logger LOG = LogManager.getLogger(AbstractWebCrawler.class);

	protected List<String> getInnerHtml(Elements elements) {
		List<String> result = new LinkedList<String>();

		if (null != elements) {

			Iterator<Element> iterator = elements.iterator();
			while (iterator.hasNext()) {
				result.add(iterator.next().text());
			}
		}

		return result;
	}

	protected Elements getElementsByClass(Element element, String className) {
		if (null != element) {
			return element.getElementsByClass(className);
		}
		return null;
	}

	protected Element getElementById(Element element, String id) {
		if (null != element) {
			return element.getElementById(id);
		}
		return null;
	}

	protected Document getHtmlFromUrl(String url) {

		try {
			return Jsoup.connect(url).get();
		} catch (IOException e) {
			LOG.error("Error parsing Steam page");
		}

		return null;
	}
}
