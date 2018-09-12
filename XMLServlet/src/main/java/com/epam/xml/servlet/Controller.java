package com.epam.xml.servlet;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.xml.medicine.Certificated;
import com.epam.xml.parser.AbstractMedicineBuilder;
import com.epam.xml.parser.MedicineBuildFactory;

@WebServlet("/")
public class Controller extends HttpServlet {

	Set<Certificated> medicines;
	
	public Controller() {
		MedicineBuildFactory factory = new MedicineBuildFactory();
		AbstractMedicineBuilder builder = factory.createBuilder("DOM");
		builder.buildSetMedicines("D:\\Programming\\Java\\training\\XmlServlet\\xml\\medicine.xml");
		medicines = new HashSet<Certificated>();
		medicines.addAll(builder.getMedicines());
		System.out.println(medicines.size());
	}
	
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    	processing(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
    	processing(req, resp);
    }
    
    private void processing(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException{
    	if("forward".equals(req.getParameter("command"))) {
    		
		    Iterator<Certificated> iter = medicines.iterator();
			int in =  Integer.parseInt(req.getParameter("number"));
			for(int i = 0; i < (in - 1); i ++) {
				iter.next();
			}
			Certificated c = iter.next();
		    req.setAttribute("name", c.getName());
		    req.setAttribute("group", c.getGroup());
		    req.setAttribute("pharm", c.getPharm());
		    req.setAttribute("valid", c.getValid());
		    req.setAttribute("form", c.getForm());
		    req.setAttribute("price", c.getPrice());
		    req.setAttribute("wrapped", c.getWrapped());
		    req.setAttribute("amount", c.getDosage().getAmount());
		    req.setAttribute("period", c.getDosage().getPeriod());
		    req.setAttribute("freq", c.getDosage().getFrequency());
		    req.setAttribute("number", c.getCertificate().getNumber());
		    req.setAttribute("issued", c.getCertificate().getIssued());
		    req.setAttribute("until", c.getCertificate().getUntil());
		    req.setAttribute("reg", c.getCertificate().getRegister());
		    req.setAttribute("analogs", c.getAnalogs());
		    
		    req.getRequestDispatcher("/WEB-INF/JSP/data.jsp").forward(req, resp);
	    }
    }
}
