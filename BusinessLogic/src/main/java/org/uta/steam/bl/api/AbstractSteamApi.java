package org.uta.steam.bl.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class AbstractSteamApi {

	private static Logger LOG = LogManager.getLogger(AbstractSteamApi.class);

	// HTTP request timeout
	private static final int REQUEST_TIMEOUT = 1000;

	protected String httpGet(String urlString) {
		String result = StringUtils.EMPTY;
		HttpURLConnection httpURLConnection = null;

		try {

			// adds URL parameter
			URL url = new URL(urlString);

			httpURLConnection = (HttpURLConnection) url.openConnection();
			httpURLConnection.setConnectTimeout(REQUEST_TIMEOUT);
			httpURLConnection.setRequestMethod("GET");
			httpURLConnection.setInstanceFollowRedirects(true);

			InputStream in = new BufferedInputStream(
					httpURLConnection.getInputStream());
			InputStreamReader reader = new InputStreamReader(in);

			BufferedReader bufferedReader = new BufferedReader(reader);
			StringBuilder builder = new StringBuilder();

			String line = bufferedReader.readLine();

			while (line != null) {
				builder.append(line);
				line = bufferedReader.readLine();
			}

			in.close();
			reader.close();
			bufferedReader.close();
			httpURLConnection.getInputStream().close();

			result = builder.toString();

		} catch (IOException e) {
			LOG.error(e);
		} catch (Exception e) {
			LOG.error(e);
		} finally {
			if (null != httpURLConnection) {
				httpURLConnection.disconnect();
			}
		}

		return result;
	}
}
