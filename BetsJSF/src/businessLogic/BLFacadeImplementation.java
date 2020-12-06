package businessLogic;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.jws.WebService;

import java.util.List;



import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import dataAccess.HibernateDataAccess;


import domain.Question;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		
		System.out.println("Creating BLFacadeImplementation instance");
		
		ConfigXML c=ConfigXML.getInstance();
/*
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}*/

		HibernateDataAccess dataAccessHiber = new HibernateDataAccess();

		dataAccessHiber.initializeDB();
		

	}


	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */

	public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{

		//The minimum bed must be greater than 0
		HibernateDataAccess dataAccessHiber = new HibernateDataAccess();
		//DataAccess dataAccessHiber = new DataAccess();
		Question qry=null;


		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


		qry=dataAccessHiber.createQuestion(event.getEventNumber().toString(),question,betMinimum);		

		//qry=dataAccessHiber.createQuestion(event,question,betMinimum);

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */

	public List<Event> getEvents(Date date)  {
		HibernateDataAccess dataAccessHiber = new HibernateDataAccess();
		//DataAccess dataAccessHiber = new DataAccess();
		System.out.println("data BLFacadeIm "+ date);
		List<Event>  events=dataAccessHiber.getEvents(date);

		return events;
	}


	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	
	 public List<Date> getEventsMonth(Date date) {
		HibernateDataAccess dataAccessHiber = new HibernateDataAccess();
		//DataAccess dataAccessHiber = new DataAccess();
		List<Date>  dates=dataAccessHiber.getEventsMonth(date);

		return dates;
	}
	 
	 public void pro(){
		 System.out.println("Proba programa atzitzen da");
	 }

/*
	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
/*
	public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();

		//initializeBD();
	}
*/

}