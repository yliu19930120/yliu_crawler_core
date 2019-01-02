package crawler;


import com.yliu.crawler.core.constant.Decode;
import com.yliu.crawler.core.crawler.Crawler;
import com.yliu.crawler.core.parser.Parser;

public class CrawlerTest {
	
	public static void main(String[] args) {
		String url = "https://search.51job.com/list/040000,000000,0000,00,9,99,java,2,1.html?lang=c&stype=&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&providesalary=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
		String crawlerName = "51Job_crawler";
		 Crawler
		 .create()
		 .addUrl(url)
		 .run(5);
	}
	
}
