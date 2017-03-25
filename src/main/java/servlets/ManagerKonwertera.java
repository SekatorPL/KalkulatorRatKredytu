package servlets;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerKonwertera{
	
	private double kwotaKredytu;
	private int iloscRat;
	private double oprocentowanie;
	private double oplataStala;
	private boolean stalaRata;
	
	private HttpServletRequest httpServletRequest;
	private HttpServletResponse httpServletResponse;
	
	public ManagerKonwertera(HttpServletRequest requestHttp, HttpServletResponse responseHttp) throws IOException{
		
		httpServletRequest = requestHttp;
		httpServletResponse = responseHttp;
		
		if(poprawnoscDanych(httpServletRequest)){
			
			kwotaKredytu = Double.parseDouble(httpServletRequest.getParameter("kwotaKredytu"));
			iloscRat = Integer.parseInt(httpServletRequest.getParameter("iloscRat"));
			oprocentowanie = Double.parseDouble(httpServletRequest.getParameter("oprocentowanie"));
			
			if(httpServletRequest.getParameter("rodzajRat").equals("stala")){
				stalaRata = true;
			}
			else{
				stalaRata = false;
			}
		}
		else{
			httpServletResponse.sendRedirect("/");
		}
	}
	
	public void pokazTabele() throws IOException{
		
		String zawartoscStrony = "";
		
		zawartoscStrony += "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">";
		
		zawartoscStrony += "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><title>Kredyt</title></head><body>"
				+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">";
		
		zawartoscStrony += "<table border = 2><tr><td>Nr raty</td><td>Saldo kapitalu</td>"
				+ "<td>Odsetki kredytu</td><td>Splata kredytu</td><td>Pelna rata</td>"
				+ "<td>Pozostalo do splaty</td></tr>";
		
		double kwota = kwotaKredytu;
		double r = (double) oprocentowanie / 100 / 12;
		double rata;
		double oprocentowanie;
		
		if(stalaRata){
			
			rata = (kwota * r * Math.pow(r + 1, iloscRat)) / (Math.pow(r + 1, iloscRat) - 1);
			
			for(int i = 1; i < iloscRat; i++){
				
				oprocentowanie = kwota * r;
				
				zawartoscStrony += "<tr><td>" + i + "</td>"
						+ "<td>" + String.format("%.2f", kwota) + "</td>"
						+ "<td>" + String.format("%.2f", oprocentowanie) + "</td>"
								+ "<td>" + String.format("%.2f", rata - oprocentowanie) + "</td>";
				
				kwota = kwota - rata + oprocentowanie;
				
				zawartoscStrony += "<td>" + String.format("%.2f", rata) + "</td>"
						+ "<td>" + String.format("%.2f", kwota) + "</td></tr>";
			}
		}
		else{
			
			rata = (kwota * (1 + (iloscRat) * r)) / (iloscRat);
			rata = rata - (kwota * r);
			
			for(int i = 1; i < iloscRat; i++){
				
				oprocentowanie = kwota * r;
				
				zawartoscStrony += "<tr><td>" + i + "</td>"
						+ "<td>" + String.format("%.2f", kwota) + "</td>"
						+ "<td>" + String.format("%.2f", oprocentowanie) + "</td>"
								+ "<td>" + String.format("%.2f", rata) + "</td>";
				
				kwota = kwota - rata;
				
				zawartoscStrony += "<td>" + String.format("%.2f", rata + oprocentowanie) + "</td>"
						+ "<td>" + String.format("%.2f", kwota) + "</td></tr>";
			}
		}
		
		oprocentowanie = kwota * r;
		
		zawartoscStrony += "<tr><td>" + iloscRat + "</td>"
				+ "<td>" + String.format("%.2f", kwota) + "</td>"
				+ "<td>" + String.format("%.2f", oprocentowanie) + "</td>"
						+ "<td>" + String.format("%.2f", kwota - oprocentowanie) + "</td>"
						+ "<td>" + String.format("%.2f", kwota) + "</td><td>" + 0 + "</td></tr>"
								+ "</table></body></html>";
		
		httpServletResponse.getWriter().println(zawartoscStrony);
		httpServletResponse.setContentType("text/html");
	}
	
	public boolean poprawnoscDanych(HttpServletRequest httpServletRequest){
		
		String parametr;
		
		parametr = httpServletRequest.getParameter("kwotaKredytu");
		if(parametr == null || parametr.equals("")) return false;
		
		parametr = httpServletRequest.getParameter("iloscRat");
		if(parametr == null || parametr.equals("")) return false;
		
		parametr = httpServletRequest.getParameter("oprocentowanie");
		if(parametr == null || parametr.equals("")) return false;
		
		parametr = httpServletRequest.getParameter("rodzajRat");
		if(parametr == null || parametr.equals("")) return false;
		
		return true;
	}
	
	public double getKwotaKredytu() {
		return kwotaKredytu;
	}

	public void setKwotaKredytu(double kwotaKredytu) {
		this.kwotaKredytu = kwotaKredytu;
	}

	public int getIloscRat() {
		return iloscRat;
	}

	public void setIloscRat(int iloscRat) {
		this.iloscRat = iloscRat;
	}

	public double getOprocentowanie() {
		return oprocentowanie;
	}

	public void setOprocentowanie(double oprocentowanie) {
		this.oprocentowanie = oprocentowanie;
	}

	public double getOplataStala() {
		return oplataStala;
	}

	public void setOplataStala(double oplataStala) {
		this.oplataStala = oplataStala;
	}

	public boolean isStalaRata() {
		return stalaRata;
	}

	public void setStalaRata(boolean stalaRata) {
		this.stalaRata = stalaRata;
	}

	public HttpServletRequest getHttpServletRequest() {
		return httpServletRequest;
	}

	public void setHttpServletRequest(HttpServletRequest httpServletRequest) {
		this.httpServletRequest = httpServletRequest;
	}

	public HttpServletResponse getHttpServletResponse() {
		return httpServletResponse;
	}

	public void setHttpServletResponse(HttpServletResponse httpServletResponse) {
		this.httpServletResponse = httpServletResponse;
	}
}