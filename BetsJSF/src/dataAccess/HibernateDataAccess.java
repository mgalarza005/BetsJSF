package dataAccess;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.TypedQuery;

import org.hibernate.Session;

import configuration.UtilDate;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;

public class HibernateDataAccess {
	public void HibernateDataAccess(){
	}

	//public static void main(String[] args) {
	public void initializeDB(){ 
	
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Question> result1 = session.createQuery("from Question").list();
		for (Question q:  result1){
			session.delete(q); 
		}
		List<Event> result2 = session.createQuery("from Event").list(); 

		for(Event e:    result2){
			session.delete(e);  
		}
		session.getTransaction().commit();
		System.out.println("Hibernate DBA ezabatuta egin da"); 
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();



		Calendar today = Calendar.getInstance();

		System.out.println("Eventen sorkuntza:");
		int month=today.get(Calendar.MONTH);
		month+=1;
		int year=today.get(Calendar.YEAR);

		Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
		Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
		Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
		Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
		Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
		Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
		Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
		Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
		Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
		Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

		Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
		Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
		Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
		Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
		Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
		Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));


		Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
		Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
		Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
		Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));

		Question q1=new Question();
		Question q2=new Question();
		Question q3=new Question();
		Question q4=new Question();
		Question q5=new Question();
		Question q6=new Question();


		q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
		q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
		q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
		q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
		q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
		q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);

		createAndStoreQuestion(q1);
		createAndStoreQuestion(q2);
		createAndStoreQuestion(q3);
		createAndStoreQuestion(q4);
		createAndStoreQuestion(q5);
		createAndStoreQuestion(q6);

		createAndStoreEvent(ev1);
		createAndStoreEvent(ev2);
		createAndStoreEvent(ev3);
		createAndStoreEvent(ev4);
		createAndStoreEvent(ev5);
		createAndStoreEvent(ev6);
		createAndStoreEvent(ev7);
		createAndStoreEvent(ev8);
		createAndStoreEvent(ev9);
		createAndStoreEvent(ev10);
		createAndStoreEvent(ev11);
		createAndStoreEvent(ev12);
		createAndStoreEvent(ev13);
		createAndStoreEvent(ev14);
		createAndStoreEvent(ev15);
		createAndStoreEvent(ev16);
		createAndStoreEvent(ev17);
		createAndStoreEvent(ev18);
		createAndStoreEvent(ev19);
		createAndStoreEvent(ev20);


		System.out.println("Db initialized");
		System.out.println("Gertaeren zerrenda:");
		List events = gertaerakZerrendatu();
		for (int i = 0; i < events.size(); i++) {
			Event ev = (Event) events.get(i);
			System.out.println("Id: " + ev.getEventNumber() + " Deskribapena: "
					+ ev.getDescription() + " Data: " + ev.getEventDate());
		}




		//session.getTransaction().commit();
		System.out.println("DBa   hasieratuta");  

	}

	public static void createAndStoreEvent(Event e) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(e);
		session.getTransaction().commit();
	}
	public static void createAndStoreQuestion(Question q) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(q);
		session.getTransaction().commit();
	}
	public static List<Event> gertaerakZerrendatu() {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List result = session.createQuery("from Event").list();
		session.getTransaction().commit();
		return result;
	}


	public Question createQuestion(String eventNumber, String question, float betMinimum) throws  QuestionAlreadyExist {
		//System.out.println(">> DataAccess: createQuestion=> event= "+ev+" question= "+question+" betMinimum="+betMinimum);




		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();


		session.createQuery("from Event").list();

		//Event ev = db.find(Event.class, event.getEventNumber());
		List<Event> res = session.createQuery("from Event ev where ev.eventNumber= :eventNumber").list();
		Event ev = (Event) res.get(0);
		if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));

		Question q = ev.addQuestion(question, betMinimum);

		session.save(ev);
		session.getTransaction().commit();

		return q;

	}
	public List<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		System.out.println("data HDA: "+ date);
		SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		List<Event> result = session.createQuery("from Event ev where ev.eventDate ='"+format.format(date)+"'").list();
		session.getTransaction().commit();
		return result;
	}
	public void proba() {
		System.out.println("Proba programa deitzen du");
	}
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public List<Date> getEventsMonth(Date date) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		
		System.out.println(">> DataAccess: getEventsMonth");
		List<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		List<Event> result = session.createQuery("from Event ev where ev.eventDate>='"+firstDayMonthDate+"' and ev.eventDate<='"+lastDayMonthDate+"' ").list();   
		//query.setParameter(1, firstDayMonthDate);
		//query.setParameter(2, lastDayMonthDate);
		List<Date> dates = new ArrayList(result); 
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}

}
