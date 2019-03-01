package ssdlc.model;

import org.owasp.esapi.ESAPI;


public class TestMdoel {
	
	public static void main(String[] args) throws Exception {
		
		/*		
		String pattern = "^(?:(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[^A-Za-z0-9])(?=.*[a-z])|(?=.*[^A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z])|(?=.*\\d)(?=.*[A-Z])(?=.*[^A-Za-z0-9]))(?!.*(.)\\1{2,})[A-Za-z0-9!~<>,;:_=?*+#.\"&§%°()\\|\\[\\]\\-\\$\\^\\@\\/]{8,32}$";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher("asdkokABC^^%$");
		System.out.println(m.matches());
		*/
				
		
		//Evil Regular
		/*
		String evil_pattern = "^(a+)+$";
		evil_pattern = "^(a|aa)+{2,7}$";
		evil_pattern = "^([a-zA-Z]+)*$";
		
		
		Pattern r = Pattern.compile(evil_pattern);
		
		String data = "asdkoASDSAdasdddddddddddddddddddddddddddddddddd%";
		System.out.println(data.length());
		
		long start = System.currentTimeMillis();		
		Matcher m = r.matcher(data);
		System.out.println(m.matches());		
		long end = System.currentTimeMillis();
		
		System.out.println( "cost time:" + ((double)(end-start)/1000) );
		*/
		
		/*
		String data = "\\x20\\x27onclick\\x3dalert\\x281\\x29\\x3b\\x2f\\x2f\\x27";
				
		String clear_js = ESAPI.encoder().encodeForJavaScript(data);
		System.out.println("clear_js:"+clear_js);
		
		
		data = "' onclick='<script>alert(123);</script>";
				
		String clear_html = ESAPI.encoder().encodeForHTML(data);
		System.out.println("clear_html:"+clear_html);
		*/
		
		/*
		String data = "<script> ";
		
		System.out.println(ESAPI.encoder().encodeForHTML(data));
		System.out.println(ESAPI.encoder().encodeForCSS(data));
		System.out.println(ESAPI.encoder().encodeForJavaScript(data));
		System.out.println(ESAPI.encoder().encodeForXML(data));
		*/
		
	}

}
