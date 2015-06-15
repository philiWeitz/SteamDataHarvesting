package org.uta.steam.bl.csv;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

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
	private static final String TEXT_MARKER = "\"";
	
	private static final DateFormat sdf = 
			new SimpleDateFormat(SteamUtil.DATE_FORMAT, Locale.ENGLISH);
	
	
	public File getCsvFileByApp(SteamApp app) {
		
		try {
			File temp = File.createTempFile("tmp",null);
			temp.deleteOnExit();
			
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
				    new FileOutputStream(temp), SteamUtil.CSV_OUTPUT_FORMAT));
			
			exportAppData(out, app);
			
			out.flush();
            out.close();
            
			return temp;
		} catch (IOException e) {
			LOG.error("Error creating temporay file for csv export");
		}
		
		return null;
	}
	
	
	private void exportAppData(BufferedWriter out, SteamApp app) throws IOException {

		// write app header
		StringBuffer sb = new StringBuffer();
		sb.append(app.getName()).append(" (");
		sb.append(app.getAppId()).append(")");
		
		out.write(sb.toString());
		out.newLine();
		
		out.write(addContext(app.getDescription()));
		out.newLine();
		
		// get list of versions starting with the last version!
		List<AppVersion> versions = new LinkedList<AppVersion>(app.getVersions());
		Collections.sort(versions);

		// export versions
		out.newLine();
		exportVersionHeader(out);
		
		for(AppVersion version : versions) {
			exportVersion(out, version);
		}
		out.newLine();
		

		/********* export reviews *********/
		exportReviewHeader(out);
		
		List<Review> reviews = new LinkedList<Review>(app.getReviews());
		Collections.sort(reviews);

		for(Review review : reviews) {
			exportReview(out, review);
		}
		out.flush();

		
		/********* export data set *********/
		out.newLine();
		exportAppDataHeader(out);
		
		List<AppData> dataList = new LinkedList<AppData>(app.getData());
		Collections.sort(dataList);

		for(AppData data : dataList) {
			exportAppData(out, data);
		}
		out.flush();
		
		
		/********* export dlcs *********/
		out.newLine();
		
		for(AppDLC dlc : app.getDlcs()) {
			exportDlc(out, dlc);
		}	
		out.flush();
	}
	
	
	private void exportDlc(BufferedWriter out, AppDLC dlc) throws IOException {

		// write DLC header
		StringBuffer sb = new StringBuffer();
		sb.append(dlc.getName()).append(" (");
		sb.append(dlc.getDlcId()).append(")");
		
		out.write(sb.toString());
		out.newLine();
		
		exportReviewHeader(out);
		
		// export reviews
		List<Review> reviews = new LinkedList<Review>(dlc.getReviews());
		Collections.sort(reviews);

		for(Review review : reviews) {
			exportReview(out, review);
		}
		out.newLine();
		out.flush();
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
		sb.append(addContext(version.getTitle()));
		
		sb.append(SEPARATOR);
		sb.append(addContext(version.getContent()));	
		
		out.write(sb.toString());
		out.newLine();
	}
	
	
	private void exportReviewHeader(BufferedWriter out) throws IOException {
		StringBuffer sb = new StringBuffer();	
		
		sb.append("Created").append(SEPARATOR);
		sb.append("Version").append(SEPARATOR);	
		sb.append("Recommended").append(SEPARATOR);
		sb.append("List Name").append(SEPARATOR);		
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
		
		if(null != review.getVersion()) {
			sb.append(review.getVersion().getTitle()).append(SEPARATOR);
		} else {
			sb.append("No Version").append(SEPARATOR);
		}

		sb.append(review.isRecommended()).append(SEPARATOR);
		sb.append(review.getListName()).append(SEPARATOR);		
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
		sb.append(addContext(review.getContent()));
		
		out.write(sb.toString());
		out.newLine();
	}
	
	
	private void exportAppDataHeader(BufferedWriter out) throws IOException {
		StringBuffer sb = new StringBuffer();	
		
		out.write("App Data:");
		out.newLine();
		
		sb.append("Created").append(SEPARATOR);		
		sb.append("Price").append(SEPARATOR);
		sb.append("Positive reviews").append(SEPARATOR);		
		sb.append("Negative reviews").append(SEPARATOR);
		sb.append("Tags").append(SEPARATOR);
		
		out.write(sb.toString());
		out.newLine();
	}
	
	
	private void exportAppData(BufferedWriter out, AppData data) throws IOException {
		StringBuffer sb = new StringBuffer();

		sb.append(sdf.format(data.getCreated())).append(SEPARATOR);
		sb.append(data.getPrice()).append(SEPARATOR);	
		sb.append(data.getPositiveReviews()).append(SEPARATOR);	
		sb.append(data.getNegativeReviews()).append(SEPARATOR);			
		sb.append(addContext(data.getTags().toString()));
		
		out.write(sb.toString());
		out.newLine();
	}
	
	
	private String addContext(String context) {
		return TEXT_MARKER + context.replace(TEXT_MARKER, "'")
				.replace(SEPARATOR, ",").replace('\n', ' ') + TEXT_MARKER;
	}
}
