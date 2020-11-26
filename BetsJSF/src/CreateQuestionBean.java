import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

public class CreateQuestionBean {
	private Date data;
	private String galdera;
	private int minBet;
	private Question q;
	private List<Event> events=new ArrayList<Event>();
	private Event event;
	
	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
		
	}

	public Question getQ() {
		return q;
	}

	public void setQ(Question q) {
		this.q = q;
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
	public CreateQuestionBean() {

	}

	public List<Event> getEvents2(SelectEvent d1){
		BLFacade facadeBL;
		facadeBL=FacadeBean.getBusinessLogic();

		List<Event> gertaerak= facadeBL.getEvents(this.data);
		for(int i=0; i<gertaerak.size() ; i++) {
			System.out.println(gertaerak.get(i).getDescription());
			Event e = new Event(gertaerak.get(i).getEventNumber(),gertaerak.get(i).getDescription(),gertaerak.get(i).getEventDate());
			e.setQuestions(gertaerak.get(i).getQuestions());
			events.add(e);
		}
		return gertaerak;
	}

	public String getGaldera() {
		return galdera;
	}

	public void setGaldera(String galdera) {
		this.galdera = galdera;
	}

	public int getMinBet() {
		return minBet;
	}

	public void setMinBet(int minBet) {
		this.minBet = minBet;
	}
	
	public void createQuestion(Event event) throws EventFinished, QuestionAlreadyExist {
		
		BLFacade facadeBL;
		facadeBL=FacadeBean.getBusinessLogic();
		if(!(this.galdera== "")) {
			facadeBL.createQuestion(event, this.galdera, this.minBet);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Galdera sortu da!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Galdera inputa ezin da null izan!"));
		}
		
	}
}