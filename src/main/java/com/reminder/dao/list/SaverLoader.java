package com.reminder.dao.list;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaverLoader {

	public static final Logger LOG = LogManager.getLogger(SaverLoader.class);

	public static void serialize(Object obj, String fileName) {

		try (FileOutputStream fileStream = new FileOutputStream(fileName);
				ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);) {

			objectStream.writeObject(obj);

		} catch (IOException e) {
			LOG.warn("Could not serialize {}", e);
		}

	}

	public static Object deserialize(String fileName) {
		Object result = null;

		try (FileInputStream fileStream = new FileInputStream(fileName);
				ObjectInputStream objectStream = new ObjectInputStream(fileStream);) {

			result = objectStream.readObject();

		} catch (IOException e) {
			LOG.warn("Could not deserialize {}", e);
		} catch (ClassNotFoundException ex) {
			LOG.warn("Could not deserialize {}", ex);
		}
		return result;

	}

	public static void unmarshal() {
		try {
			LOG.info("Unmarshalling events, users");
			JAXBContext jaxbEventContext = JAXBContext.newInstance(EventRepo.class);
			JAXBContext jaxbUserContext = JAXBContext.newInstance(UserRepo.class);

			Unmarshaller jaxbEventUnmarshaller = jaxbEventContext.createUnmarshaller();
			Unmarshaller jaxbUserUnmarshaller = jaxbUserContext.createUnmarshaller();

			EventRepo.setInstance(((EventRepo) jaxbEventUnmarshaller.unmarshal(new File("EVENTS.xml"))));
			UserRepo.setInstance(((UserRepo) jaxbUserUnmarshaller.unmarshal(new File("USERS.xml"))));

		} catch (PropertyException e) {
			LOG.warn("Problem encountered while setting properties {}", e);
		} catch (JAXBException ex) {
			LOG.warn("Problem encountered while unmarshalling objects {}", ex);
		}
	}

	public static void marshal() {
		try {
			LOG.info("Marshaling events, users");
			JAXBContext jaxbEventContext = JAXBContext.newInstance(EventRepo.class);
			JAXBContext jaxbUserContext = JAXBContext.newInstance(UserRepo.class);

			Marshaller jaxbEventMarshaller = jaxbEventContext.createMarshaller();
			Marshaller jaxbUserMarshaller = jaxbUserContext.createMarshaller();

			jaxbEventMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbUserMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbEventMarshaller.marshal(EventRepo.getInstance(), new File("EVENTS.xml"));
			LOG.info("Succesfully marshalled events");

			jaxbUserMarshaller.marshal(UserRepo.getInstance(), new File("USERS.xml"));
			LOG.info("Succesfully marshalled users");

		} catch (PropertyException e) {
			LOG.warn("Problem encountered while setting properties {}", e);
		} catch (JAXBException ex) {
			LOG.warn("Problem encountered while marshalling objects {}", ex);
		}

	}

}
