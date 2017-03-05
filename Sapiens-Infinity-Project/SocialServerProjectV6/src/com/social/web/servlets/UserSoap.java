package com.social.web.servlets;

import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.google.gson.Gson;
import com.social.jpa.entities.User;
import com.social.services.SocialNetworkService;

public class UserSoap {

	private Gson gsn = new Gson();

	public String getUser(String usrID) {

		SocialNetworkService sns = new SocialNetworkService();
		String res = null;

		try {
			User usr = sns.getUser(Integer.parseInt(usrID));

			JAXBContext context = JAXBContext.newInstance(User.class);
			Marshaller m = context.createMarshaller();
			// for pretty-print XML in JAXB
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
