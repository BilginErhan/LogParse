import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.ParseConversionEvent;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

public class LogModel {
	
	LogModel log;
	
	private String pattern_line;
	
	public String ip;
	public String eventZamani;
	public String requestMehod;
	public String icerikAdres;
	public String icerikAdi;
	public long icerikBitrate;
	public String icerikTipi;
	public String httpStatus;
	public long totalSentByte;
	public String userAgent;
	public String cacheStatus;
	
	private Pattern pattern;
	private Matcher matcher;
	
	public String temp;
	public String temp1;
	
//	public LogModel(logModel){
//		
//	
//	}
	
	@Override
	public String toString(){
		return 	ip+"\n"+
				eventZamani+"\n"+
				requestMehod+"\n"+
				icerikAdres+"\n"+
				icerikAdi+"\n"+
				icerikBitrate+"\n"+
				icerikTipi+"\n"+
				httpStatus+"\n"+
				totalSentByte+"\n"+
				userAgent+"\n"+
				cacheStatus;
		
	}
	
	
	public LogModel parcala(String line){
		
//		Find Ýp
		pattern_line = "\\b\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\b";
		pattern = Pattern.compile(pattern_line);
		matcher = pattern.matcher(line);
		if(matcher.find())
			setIp(matcher.group().toString());
		
//		Find Event zamaný
		pattern_line = "\\[\\b\\d{1,3}\\/\\w*\\/\\d{4}\\:\\d{2}:\\d{2}\\:\\d{2}\\ \\+\\d{4}\\]";
		pattern = Pattern.compile(pattern_line);
		matcher = pattern.matcher(line);
		if(matcher.find())
			setEventZamani(matcher.group().toString());
		
//		Find Request Method
		pattern_line = "GET|POST|HEAD|PUT|DELETE|CONNECT|OPTIONS|TRACE";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find())
			setRequestMehod(matcher.group().toString());
		
//		Find içerik adresi
		pattern_line = "\\/\\w+\\/\\w+\\/[\\w+._]*\\/[\\w+(\\/=)]*\\)";
		matcher = Pattern.compile(pattern_line).matcher(line);
//		setIcerikAdres(matcher.group().toString().substring(0,matcher.group().toString().length()-1)); old
		if(matcher.find())
			setIcerikAdres(matcher.group().toString());
		
//		Find içerik adý
		pattern_line = "ID\\w+";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find())
			setIcerikAdi(matcher.group().toString());
		
//		find içerik bitrate
		pattern_line = "\\(\\d*\\)";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find()){
			temp = matcher.group().toString().substring(1,matcher.group().toString().length()-6);
			if(temp.equals(""))
			{
				temp = matcher.group().toString().substring(1,matcher.group().toString().length()-5) + "0000";
			}else
				temp+="00000";
			setIcerikBitrate(Long.parseLong(temp));
		}
		
//		Find içerik tipi
		pattern_line = "Fragments\\(\\w+";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find())
		{
			temp = matcher.group().toString();
			temp = temp.replaceAll("Fragments\\(","");
			setIcerikTipi(temp);
		}
		
//		Find Http Status
		pattern_line = " \\d* ";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find()){
			temp = matcher.group().toString().trim();
			setHttpStatus(temp);
		}
		
//		Find total Send Byte
		pattern_line =  " [\\d]* \"";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find()){
			temp = matcher.group().toString();
			temp = temp.replaceAll("\"","");
			temp = temp.trim();
			
			setTotalSendeByte(Long.parseLong(temp));
		}
		
//		User Agent
		pattern_line = "\\\" \\\"[\\w+\\/. (;:),]*\\/\\b";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find()){
			temp = matcher.group().toString();
			temp  = temp.replaceAll("\\/[\\w+\\/. (;:),]*", "");
			temp = temp.replaceAll("[\\\" ]*\"","");
			setUserAgent(temp);
		}
		
//		Cache Status
		pattern_line = "cs=\\w+";
		matcher = Pattern.compile(pattern_line).matcher(line);
		if(matcher.find())
			temp = matcher.group().toString().substring(3,matcher.group().toString().length());
		int i = 2; //2 ise hatalý veri
		if(temp.equals("HIT"))
			i = 1;
		else if (temp.equals("MISS")) {
			i=0;
		}
		setCacheStatus(temp);
		
		
		return log;
	}

	
	public String getIp() {
		return ip;
	}


	private void setIp(String ip) {
		this.ip = ip;
	}


	public String getEventZamani() {
		return eventZamani;
	}


	private void setEventZamani(String eventZamani) {
		this.eventZamani = eventZamani;
	}


	public String getRequestMehod() {
		return requestMehod;
	}


	private void setRequestMehod(String requestMehod) {
		this.requestMehod = requestMehod;
	}


	public String getIcerikAdres() {
		return icerikAdres;
	}


	private void setIcerikAdres(String icerikAdres) {
		this.icerikAdres = icerikAdres;
	}


	public long getIcerikBitrate() {
		return icerikBitrate;
	}


	private void setIcerikBitrate(long icerikBitrate) {
		this.icerikBitrate = icerikBitrate;
	}


	public String getIcerikTipi() {
		return icerikTipi;
	}


	private void setIcerikTipi(String icerikTipi) {
		this.icerikTipi = icerikTipi;
	}


	public String getHttpStatus() {
		return httpStatus;
	}


	private void setHttpStatus(String httpStatus) {
		this.httpStatus = httpStatus;
	}


	public long getTotalSendeByte() {
		return totalSentByte;
	}


	private void setTotalSendeByte(long totalSendeByte) {
		this.totalSentByte = totalSendeByte;
	}


	public String getUserAgent() {
		return userAgent;
	}


	private void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}


	public String getCacheStatus() {
		return cacheStatus;
	}


	private void setCacheStatus(String cacheStatus) {
		this.cacheStatus = cacheStatus;
	}
	
	public String getIcerikAdi() {
		return icerikAdi;
	}


	private void setIcerikAdi(String icerikAdi) {
		this.icerikAdi = icerikAdi;
	}

}
