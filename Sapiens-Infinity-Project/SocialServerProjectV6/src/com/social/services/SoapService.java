package com.social.services;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import com.social.jpa.User;

public class SoapService {

	private static void jaxbObjectToXML(User emp) {

		try {
			JAXBContext JAXBuser = JAXBContext.newInstance(User.class);
			Marshaller jaxbMarshaller = JAXBuser.createMarshaller();
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			//Marshal the employees list in console
			jaxbMarshaller.marshal(JAXBuser, System.out);

			//            // Write to File
			//            m.marshal(emp, new File(FILE_NAME));
			//        } catch (JAXBException e) {
			//            e.printStackTrace();
			//        }
		}catch (Exception e) {
			// TODO: handle exception
		}


	}

}
