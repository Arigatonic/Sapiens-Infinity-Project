package com.social.web.services;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.social.jpa.entities.User;
import com.social.web.utils.ServiceConrol;

//an Example for SOAP service request.
public class UserSoap {

	public String getUser(String usrID) {

		String res = null;

		try {
			
			ServiceConrol ctrl = new ServiceConrol();
			
			User usr = ctrl.getSNS().getUser(Integer.parseInt(usrID));
			
			JAXBContext context = JAXBContext.newInstance(User.class);
			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			m.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
			java.io.StringWriter sw = new StringWriter();

			m.marshal(usr, sw);
			
			res = sw.toString();			
			
		} catch (Exception e) {
			res = e.getMessage();
			e.printStackTrace();
		}

		return res;

	}

}
