package org.uta.steam.bl.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;


public class CsvExporter {
	
	private static Logger LOG = LogManager.getLogger(CsvExporter.class);
	private static final String SEPARATOR = ";";
	
	private static final DateFormat sdf = 
			new SimpleDateFormat(SteamUtil.DATE_FORMAT, Locale.ENGLISH);

	private static final String LINE_SEPARATOR = 
			System.getProperty("line.separator");
	
	
	public String getCsvFileByApp(SteamApp app) {
		
		try {
			File temp = File.createTempFile("tmp",null);
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(temp), SteamUtil.CSV_OUTPUT_FORMAT));
			
			exportApps(out, app);
			
			out.flush();
            out.close();
            
			return temp.getAbsolutePath();
		} catch (IOException e) {
			LOG.error("Error creating temporay file for csv export");
		}
		
		return StringUtils.EMPTY;
	}
	
	private void exportApps(BufferedWriter out, SteamApp app) throws IOException {

		StringBuffer sb = new StringBuffer();
		sb.append(app.getName()).append(" (");
		sb.append(app.getAppId()).append(")").append(LINE_SEPARATOR);

		sb.append("User Id").append(SEPARATOR);
		sb.append("Play time all").append(SEPARATOR);
		sb.append("Play time last 2 weeks").append(SEPARATOR);
		sb.append("Posted").append(SEPARATOR);
		sb.append("Updated").append(SEPARATOR);
		sb.append("People Seen").append(SEPARATOR);
		sb.append("People Helpful").append(SEPARATOR);
		sb.append("Content").append(SEPARATOR).append(LINE_SEPARATOR);	
		out.write(sb.toString());
		
		for(AppData data : app.getData()) {
			for(Review review : data.getReviews()) {
				sb = new StringBuffer();
				sb.append(review.getAuthorSteamId()).append(SEPARATOR);
				sb.append(review.getPlayTimeAll()).append(SEPARATOR);
				sb.append(review.getPlayTimeLast2Weeks()).append(SEPARATOR);				
				sb.append(sdf.format(review.getPosted())).append(SEPARATOR);
				
				if(null != review.getUpdated()) {
					sb.append(sdf.format(review.getUpdated()));
				}
				sb.append(SEPARATOR);
				
				sb.append(review.getPeopleSeen()).append(SEPARATOR);
				sb.append(review.getPeopleHelpful()).append(SEPARATOR);
				sb.append("'");
				sb.append(review.getContent().replace("'", "\""));
				sb.append("'").append(LINE_SEPARATOR);
				out.write(sb.toString());
			}
		}
	}
}
