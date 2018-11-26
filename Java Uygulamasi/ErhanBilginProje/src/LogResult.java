import java.util.HashMap;
import java.util.Map.Entry;
import java.sql.*;

public class LogResult {
	
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/parsedatabase?useSSL=false";
    
    static final String USER = "root";
    static final String PASS = "";
	
	
	private Connection conn = null;
	
	public String fileName;
	public String uniqeIp;
	public String uniqeIcerik;
	public String toplamVeri;
	public String mostUser;
	public String mostContent;
	public HashMap<String,HashMap<String,String>> rateBitrate = new HashMap<String,HashMap<String,String>>();
	public HashMap<String,String> rateBrowser = new HashMap<String,String>();
	public HashMap<String,String> rateHttpStatus = new HashMap<String,String>();
	
	public enum logResultType {uniqeIp , uniqeIcerik , toplamVeri , mostUser , mostContent , rateBitrate , rateBrowser, rateHttpStatus};
	
	public void PushDatabase(String filePath){
		if(ConnectDatase() == 1){
			this.fileName = filePath.replaceAll("[\\w+:\\\\.]*\\\\\\b","");
				CheckSameRecordAndDeleteRecord();
				PushParseTable();
				PushBitrateTable();
				PushBrowserTable();
				PushHttpStatusTable();
		}
			
	}
	
	public int ConnectDatase(){
		
		try{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return 1;
		}catch (Exception e) {
			// TODO: handle exception
			return 0;
		}
	}
	
	public void CheckSameRecordAndDeleteRecord(){
		try{
			PreparedStatement deleteRecord = conn.prepareStatement(
			"delete parse_tbl,bitrate_tbl,browser_tbl,http_status_tbl "
			+ " from parse_tbl "
			+ "	inner join bitrate_tbl on parse_tbl.file_name = bitrate_tbl.file_name"
			+ " inner join browser_tbl on parse_tbl.file_name = browser_tbl.file_name"
			+ "	inner join http_status_tbl on parse_tbl.file_name = http_status_tbl.file_name"
			+ " where parse_tbl.file_name = '"+fileName+"'" );
			deleteRecord.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public void PushParseTable(){
		try {
			PreparedStatement parse_table = conn.prepareStatement(
					"insert into parse_tbl(file_name,unique_ip,unique_content,total_data,most_user_ip) values (?,?,?,?,?)");
			
			parse_table.setString(1, fileName);
			parse_table.setInt(2, Integer.valueOf(uniqeIp));
			parse_table.setInt(3, Integer.valueOf(uniqeIcerik));
			parse_table.setDouble(4, Double.valueOf(toplamVeri));
			parse_table.setString(5,mostUser);
			parse_table.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void PushBitrateTable(){
		try {
			PreparedStatement bitrate_table = conn.prepareStatement(
					"insert into bitrate_tbl(file_name,bitrate_type,bitrate,bitrate_rate) values (?,?,?,?)");
			
			for(Entry<String,HashMap<String, String>> iceriktipi : rateBitrate.entrySet())
			{
				for(Entry<String,String> icerikBitrate : iceriktipi.getValue().entrySet())
				{
					bitrate_table.setString(1, fileName);
					bitrate_table.setString(2,iceriktipi.getKey());
					bitrate_table.setInt(3, Integer.valueOf(icerikBitrate.getKey()));
					bitrate_table.setString(4, icerikBitrate.getValue());
					bitrate_table.addBatch();
				}	
			}
			
			int [] numUpdates = bitrate_table.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void PushBrowserTable(){
		try {
			PreparedStatement browser_table = conn.prepareStatement(
					"insert into browser_tbl(file_name,browser_name,browser_rate) values (?,?,?)");
			
			for(Entry<String,String> browser : rateBrowser.entrySet())
			{
				browser_table.setString(1, fileName);
				browser_table.setString(2,browser.getKey());
				browser_table.setString(3,browser.getValue());
				browser_table.addBatch();
			}		
			int [] numUpdates = browser_table.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void PushHttpStatusTable(){
		try {
			PreparedStatement http_status_table = conn.prepareStatement(
					"insert into http_status_tbl(file_name,status_type,status_rate) values (?,?,?)");
			
			for(Entry<String,String> httpStatus : rateHttpStatus.entrySet())
			{
				http_status_table.setString(1, fileName);
				http_status_table.setInt(2,Integer.valueOf(httpStatus.getKey()));
				http_status_table.setString(3, httpStatus.getValue());
				http_status_table.addBatch();
			}		
			int [] numUpdates = http_status_table.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Override
	public String toString(){

		return NumericCalculationResult(logResultType.uniqeIp) +
				"\n" + NumericCalculationResult(logResultType.uniqeIcerik) +
				"\n" + NumericCalculationResult(logResultType.toplamVeri) +
				"\n" + NumericCalculationResult(logResultType.mostUser) +
				"\n" + NumericCalculationResult(logResultType.mostContent) +
				"\n" + NumericCalculationResult(logResultType.rateBitrate) +
				"\n" + NumericCalculationResult(logResultType.rateBrowser) +
				"\n" + NumericCalculationResult(logResultType.rateHttpStatus);
	}
	
	public String NumericCalculationResult(logResultType resultType){
		String returnString = null;
		switch (resultType) {
		case uniqeIp:
			returnString = "Kaç adet unique ip den izleme yapýlmýþ? : "+ uniqeIp;
			break;
		case uniqeIcerik:
			returnString = "Kaç unique içerik izlenmiþ? : " + uniqeIcerik;
			break;
		case toplamVeri:
			returnString = "Toplam ne kadar veri gönderilmiþ? : " + toplamVeri;
			break;
		case mostUser:
			returnString = "En çok izleme yapan kullanýcý : " + mostUser;
			break;
		case mostContent:
			returnString = "En çok izlenen içerik : " + mostContent;
			break;
		case rateBitrate:
			returnString = getRateBitratetoString();
			break;
		case rateBrowser:
			returnString = getRatetoString("Ýzleme yapýlan browserlarýn oraný \n", rateBrowser);
			break;
		case rateHttpStatus:
			returnString = getRatetoString("HTTP status oraný  \n",rateHttpStatus );
			break;
		default:
			returnString = "Bir Hata Oluþtu";
			break;
		}
		return returnString;
	}
	
	public String getRateBitratetoString()
	{
		String returnString = "";
		for(Entry<String,HashMap<String, String>> iceriktipi : rateBitrate.entrySet())
		{
			returnString += "Bitrate Oranlarý ("+ iceriktipi.getKey() +")\n";
			for(Entry<String,String> icerikBitrate : iceriktipi.getValue().entrySet())
				returnString += icerikBitrate.getKey() + " = %"+ icerikBitrate.getValue()+"\n";
		}
		return returnString;
	}
	
	public String getRatetoString(String str, HashMap<String, String> hashMap) {
		for(Entry<String,String> hash : hashMap.entrySet())
			str += hash.getKey() + " = %"+ hash.getValue() + "\n";
		return str;
	}

}