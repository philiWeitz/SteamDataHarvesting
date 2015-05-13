package org.uta.steam.bl.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.uta.steam.bl.util.SteamUtil;
import org.uta.steam.jpa.model.AppDLC;
import org.uta.steam.jpa.model.AppData;
import org.uta.steam.jpa.model.AppVersion;
import org.uta.steam.jpa.model.Review;
import org.uta.steam.jpa.model.SteamApp;


public class CsvExporter {
	
	private static Logger LOG = LogManager.getLogger(CsvExporter.class);
	private static final String SEPARATOR = ";";
	
	private static final DateFormat sdf = 
			new SimpleDateFormat(SteamUtil.DATE_FORMAT, Locale.ENGLISH);
	
	
	public String getCsvFileByApp(SteamApp app) {
		
		try {
			File temp = File.createTempFile("tmp",null);
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(temp), SteamUtil.CSV_OUTPUT_FORMAT));
			
			exportAppData(out, app);
			
			out.flush();
            out.close();
            
			return temp.getAbsolutePath();
		} catch (IOException e) {
			LOG.error("Error creating temporay file for csv export");
		}
		
		return StringUtils.EMPTY;
	}
	
	
	private void exportAppData(BufferedWriter out, SteamApp app) throws IOException {

		StringBuffer sb = new StringBuffer();
		sb.append(app.getName()).append(" (");
		sb.append(app.getAppId()).append(")");
		
		out.write(sb.toString());
		out.newLine();
		
		exportReviewHeader(out);
	
		// sort app data
		List<AppData> dataList = new ArrayList<AppData>(app.getData());
		Collections.sort(dataList);
		
		// export reviews
		for(AppData data : dataList) {
			for(Review review : data.getReviews()) {
				exportReview(out, review);
			}
			out.flush();
		}
		
		// export versions
		out.newLine();
		exportVersionHeader(out);
		
		for(AppVersion version : app.getVersions()) {
			exportVersion(out, version);
		}
		
		// export dlcs
		out.newLine();
		
		for(AppDLC dlc : app.getDlcs()) {
			exportDlc(out, dlc);
		}		
	}
	
	
	private void exportDlc(BufferedWriter out, AppDLC dlc) throws IOException {

		StringBuffer sb = new StringBuffer();
		sb.append(dlc.getName()).append(" (");
		sb.append(dlc.getDlcId()).append(")");
		
		out.write(sb.toString());
		out.newLine();
		
		exportReviewHeader(out);
		
		// sort app data
		List<AppData> dataList = new ArrayList<AppData>(dlc.getData());
		Collections.sort(dataList);
		
		for(AppData data : dataList) {
			for(Review review : data.getReviews()) {
				exportReview(out, review);	
			}
			out.flush();
		}
	}
	
	
	private void exportVersionHeader(BufferedWriter out) throws IOException {
		StringBuffer sb = new StringBuffer();	
		
		sb.append("Published").append(SEPARATOR);
		sb.append("Title").append(SEPARATOR);		
		sb.append("Content").append(SEPARATOR);
		
		out.write(sb.toString());
		out.newLine();
	}
	
	
	private void exportVersion(BufferedWriter out, AppVersion version) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append(sdf.format(version.getPublished())).append(SEPARATOR);	
		sb.append("'").append(version.getTitle().replace("'", "\"")).append("'");
		sb.append(SEPARATOR);
		sb.append("'").append(version.getContent().replace("'", "\"")).append("'");	
		
		out.write(sb.toString());
		out.newLine();
	}
	
	
	private void exportReviewHeader(BufferedWriter out) throws IOException {
		StringBuffer sb = new StringBuffer();	
		
		sb.append("Created").append(SEPARATOR);
		sb.append("User Name").append(SEPARATOR);
		sb.append("User Id").append(SEPARATOR);
		sb.append("Play time all").append(SEPARATOR);
		sb.append("Play time last 2 weeks").append(SEPARATOR);
		sb.append("Posted").append(SEPARATOR);
		sb.append("Updated").append(SEPARATOR);
		sb.append("People Seen").append(SEPARATOR);
		sb.append("People Helpful").append(SEPARATOR);
		sb.append("Content").append(SEPARATOR);	
		
		out.write(sb.toString());
		out.newLine();
	}
	
	
	private void exportReview(BufferedWriter out, Review review) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append(sdf.format(review.getCreated())).append(SEPARATOR);
		sb.append(review.getAuthor()).append(SEPARATOR);				
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
		sb.append(review.getContent().replace("'", "\"")).append("'");
		
		out.write(sb.toString());
		out.newLine();
	}
}
