import java.util.Date;
import java.util.List;

import businessLogic.BLFacade;
import domain.Event;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import org.primefaces.event.SelectEvent;

public class MainBean {
	private String aukera;
	

	public String getAukera() {
		return aukera;
	}

	public void setAukera(String aukera) {
		this.aukera = aukera;
	}

	public MainBean() {
		
	}
	
	
	
	

}
