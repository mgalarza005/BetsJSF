import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import org.primefaces.event.SelectEvent;

public class QueryQuestionsBean {
	private Date data;
	private List<Event> events=new ArrayList<Event>();
	private List<Question> questions=new ArrayList<Question>();
	private Event event;

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
		System.out.println("Description: " + this.event.getDescription());
		getQuestions2();
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public QueryQuestionsBean() {
		
	}
	public QueryQuestionsBean(Date d) {
		
		this.data = d;
	}
	
	public void onDateSelect(SelectEvent event) {
		this.data=(Date)event.getObject();
		System.out.println(data);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage("Data aukeratua: "+ this.data.toString()));
	}
	
	
	
	public List<Event> getEvents2(SelectEvent d1){
		events.clear();
		BLFacade facadeBL;
		facadeBL=FacadeBean.getBusinessLogic();
		System.out.println("Eventuak");
		List<Event> gertaerak= facadeBL.getEvents(this.data);
		for(int i=0; i<gertaerak.size() ; i++) {
			System.out.println(gertaerak.get(i).getDescription());
			
			
			Event e = new Event(gertaerak.get(i).getEventNumber(),gertaerak.get(i).getDescription(),gertaerak.get(i).getEventDate());
			e.setQuestions(gertaerak.get(i).getQuestions());
			events.add(e);
		}
		/*
		for(int i=0; i<events.size() ; i++) {
			System.out.println(events.get(i).getDescription());
		}*/
		return gertaerak;
	}
	public List<Question> getQuestions2(){
		//BLFacade facadeBL;
		//facadeBL=FacadeBean.getBusinessLogic();
		//System.out.println("Eventuak");
		/*
		for(int i=0; i<gertaerak.size() ; i++) {
			System.out.println(gertaerak.get(i).getDescription());
			
			
			Event e = new Event(gertaerak.get(i).getEventNumber(),gertaerak.get(i).getDescription(),gertaerak.get(i).getEventDate());
			e.setQuestions(gertaerak.get(i).getQuestions());
			events.add(e);
		}*/
		this.questions =this.event.getQuestions();
		
		return this.questions;
			
	}
}
