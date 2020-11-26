import java.util.Date;
import java.util.List;

import org.primefaces.event.SelectEvent;

import businessLogic.BLFacade;
import domain.Event;

public class CreateQuestionBean {
	private Date data;
	private String galdera;
	private int minBet;

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	public CreateQuestionBean() {

	}

	public List<Event> getEvents(SelectEvent d1){
		BLFacade facadeBL;
		facadeBL=FacadeBean.getBusinessLogic();

		List<Event> gertaerak= facadeBL.getEvents(this.data);
		for(int i=0; i<gertaerak.size() ; i++) {
			System.out.println(gertaerak.get(i).getDescription());
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
}