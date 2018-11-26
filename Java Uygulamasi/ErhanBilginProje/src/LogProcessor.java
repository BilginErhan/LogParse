
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.Collections;

public class LogProcessor {
	
	private static LogProcessor instance = null;
	public HashMap<String, Integer> uniqeIp = new HashMap<String, Integer>();
	public HashMap<String, Integer> uniqeIcerik = new HashMap<String,Integer>();
	public long toplamVeri = 0;
	public HashMap<String, Integer> enCokIzlemeYapanKullanici = new HashMap<String,Integer>();
	public HashMap<String, Integer> enCokIzlenenIcerik = new HashMap<String,Integer>();
	public HashMap<String,HashMap<Long, Integer>> izlenenBitrateOrani = new HashMap<String,HashMap<Long,Integer>>();
	public HashMap<String, Integer> izlemeYapilanBrowserOrani = new HashMap<String,Integer>();
	public HashMap<String, Integer> httpStatusOrani = new HashMap<String,Integer>();
	
	public enum logResultType {uniqeIp , uniqeIcerik , toplamVeri , mostUser , mostContent , rateBitrate , rateBrowser, rateHttpStatus};
	public HashMap<String,HashMap<String,String>> resultArray = new HashMap<String,HashMap<String,String>>();
	LogResult logResult = new LogResult();
	
	
	public static LogProcessor getInstance(LogModel log) {
	      if(instance == null) {
	         instance = new LogProcessor();
	      }
//	      instance.regularLine(log);
	      instance.setAndCalculateLog(log);
	      return instance;
	   }
	
	private String PatternMatch(String logLine,String patternLine){
		 Matcher matcher = null;
		 
		 matcher = Pattern.compile(patternLine).matcher(logLine);
		 if(matcher.find())
			 return matcher.group().toString();
		 else
			 return "";
	}
	
	private void setAndCalculateLog(LogModel log){
		CalculateUniqeIp(log);
		CalculateUniqeIcerik(log);
		CalculateToplamVeri(log);
		CalculateEnCokIzlemeYapanKullanici(log);
		CalculateEnCokIzlenenIcerik(log);
		CalculateIzlenenBitrateOrani(log);
		CalculateIzlemeYapilanBrowserOrani(log);
		CalculateHttpStatusOrani(log);
		
		
	}
	
	private void CalculateUniqeIp(LogModel log){
		uniqeIp.put(log.ip, null);
	}
	
	private void CalculateUniqeIcerik(LogModel log){
		uniqeIcerik.put(log.icerikAdi, null);
	}
	
	private void CalculateToplamVeri(LogModel log){
		toplamVeri += log.totalSentByte;
	}
	
	private void CalculateEnCokIzlemeYapanKullanici(LogModel log){
		if(enCokIzlemeYapanKullanici.get(log.ip) == null)
			enCokIzlemeYapanKullanici.put(log.ip,1);
		else
			enCokIzlemeYapanKullanici.put(log.ip,enCokIzlemeYapanKullanici.get(log.ip)+1);
	}
	
	private void CalculateEnCokIzlenenIcerik(LogModel log){
		if(enCokIzlenenIcerik.get(log.icerikAdi) == null)
			enCokIzlenenIcerik.put(log.icerikAdi,1);
		else
			enCokIzlenenIcerik.put(log.icerikAdi,enCokIzlenenIcerik.get(log.icerikAdi)+1);
	}
	
	private void CalculateIzlenenBitrateOrani(LogModel log){
		
		if(izlenenBitrateOrani.get(log.icerikTipi) == null){
			izlenenBitrateOrani.put(log.icerikTipi, new HashMap<Long,Integer>());
			izlenenBitrateOrani.get(log.icerikTipi).put(log.icerikBitrate, 1);
		}
		else if(izlenenBitrateOrani.get(log.icerikTipi).get(log.icerikBitrate) == null){
			izlenenBitrateOrani.get(log.icerikTipi).put(log.icerikBitrate, 1);
		}else{
			izlenenBitrateOrani.get(log.icerikTipi).put(log.icerikBitrate, izlenenBitrateOrani.get(log.icerikTipi).get(log.icerikBitrate)+1);
		}
		
	}
	
	private void CalculateIzlemeYapilanBrowserOrani(LogModel log){
		if(izlemeYapilanBrowserOrani.get(log.userAgent) == null)
			izlemeYapilanBrowserOrani.put(log.userAgent, 1);
		else
			izlemeYapilanBrowserOrani.put(log.userAgent, izlemeYapilanBrowserOrani.get(log.userAgent)+1);
	}
	
	private void CalculateHttpStatusOrani(LogModel log){
		if(httpStatusOrani.get(log.httpStatus) == null)
			httpStatusOrani.put(log.httpStatus, 1);
		else
			httpStatusOrani.put(log.httpStatus, httpStatusOrani.get(log.httpStatus)+1);
	}
	
	public LogResult result(double toplamSatir){
		

		logResult.uniqeIp = String.valueOf(uniqeIp.size());
		logResult.uniqeIcerik = String.valueOf(uniqeIcerik.size());
		toplamVeri = (long) (((toplamVeri/1024.0)/1024.0)/1024.0);//convert gb
		logResult.toplamVeri = String.valueOf(toplamVeri);
		
		logResult.mostUser = Collections.max(enCokIzlemeYapanKullanici.entrySet(), 
													(entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
		
		logResult.mostContent = Collections.max(enCokIzlenenIcerik.entrySet(), 
													(entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
		
		
		
		
		for(Entry<String,HashMap<Long, Integer>> iceriktipi : izlenenBitrateOrani.entrySet())
		{
			logResult.rateBitrate.put(iceriktipi.getKey().toString(), new HashMap<String,String>());
			for(Entry<Long,Integer> icerikBitrate : iceriktipi.getValue().entrySet()){
				logResult.rateBitrate.get(iceriktipi.getKey().toString()).put(icerikBitrate.getKey().toString(), 
						String.valueOf(((double)(icerikBitrate.getValue())/toplamSatir*100.0)));
			}
		}
		
		for (Entry<String, Integer> entry : izlemeYapilanBrowserOrani.entrySet())
			logResult.rateBrowser.put(entry.getKey().toString(), 
					String.valueOf(((double)(entry.getValue())/toplamSatir*100.0)));
		
		for(Entry<String,Integer> entry : httpStatusOrani.entrySet())
			logResult.rateHttpStatus.put(entry.getKey().toString(),
					String.valueOf(((double)(entry.getValue())/toplamSatir*100.0)));
		
		return logResult;
		
	}
	
	

}
