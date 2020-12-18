import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
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
		//events.clear();
		BLFacade facadeBL;
		facadeBL=FacadeBean.getBusinessLogic();
		System.out.println("data QueryBean "+ this.data);
		List<Event> gertaerak= facadeBL.getEvents(this.data);
		events.clear();
		for(int i=0; i<gertaerak.size() ; i++) {
			//System.out.println(gertaerak.get(i).getDescription());
			
			
			Event e = new Event(gertaerak.get(i).getEventNumber(),gertaerak.get(i).getDescription(),gertaerak.get(i).getEventDate());
			e.setQuestions(gertaerak.get(i).getQuestions());
			events.add(e);
		}
		
		return gertaerak;
	}
	public List<Question> getQuestions2(){
		this.questions =this.event.getQuestions();
		return this.questions;
			
	}
	public List<Question> getAllQuestions() {
		BLFacade facadeBL;
		facadeBL=FacadeBean.getBusinessLogic();
		List<Event> gertaerak= facadeBL.gertaerakZerrendatu();
		System.out.println("mmmm");
		System.out.println(gertaerak.size());
		for(int i=0; i<gertaerak.size() ; i++) {
			System.out.println(gertaerak.size());
			
			Event e = new Event(gertaerak.get(i).getEventNumber(),gertaerak.get(i).getDescription(),gertaerak.get(i).getEventDate());
			e.setQuestions(gertaerak.get(i).getQuestions());
			List<Question> qs= e.getQuestions();
			
			System.out.println(qs.size() +"kkkkkkkkkkkkk");
			for (int j=0; j<qs.size(); j++) {
				
				Question q = new Question(qs.get(j).getQuestionNumber(), qs.get(j).getQuestion(), qs.get(j).getBetMinimum(), e);
				
				System.out.println("Galdera: " + q.getQuestion() +  "Zenbakia: " + q.getQuestionNumber());
				this.questions.add(q);
			}
			e.setQuestions(gertaerak.get(i).getQuestions());
			
			
		}
		return this.questions;
	}
}
