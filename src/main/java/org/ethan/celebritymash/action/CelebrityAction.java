package org.ethan.celebritymash.action;

import org.ethan.celebritymash.dao.CelebrityDao;
import org.ethan.celebritymash.model.Celebrity;
import org.ethan.celebritymash.model.Gender;

import com.opensymphony.xwork2.ActionSupport;

public class CelebrityAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Celebrity celebrity;
	
	private Gender[] genders = Gender.values();
	
	private CelebrityDao dao = CelebrityDao.getInstance();
	
	public String create() {
		if(celebrity.getImage() == null || celebrity.getImage().length() == 0) {
			String fName = celebrity.getForeignName().replace(" ", "_");
			fName = fName.replace("·", "_");
			celebrity.setImage("img/celebrity/" + fName + ".jpg");
		}
		dao.createCelebrity(celebrity);
		return SUCCESS;
	}
	
	@Override
	public String input() {
		return SUCCESS;
	}

	public Gender[] getGenders() {
		return genders;
	}

	public void setGenders(Gender[] genders) {
		this.genders = genders;
	}

	@Override
	public void validate() {
		if(celebrity.getForeignName() == null || celebrity.getForeignName().length() == 0) {
			addFieldError( "celebrity.foreignName", "foreignName is required." );
		}
		if(celebrity.getName() == null || celebrity.getName().length() == 0) {
			addFieldError( "celebrity.Name", "Name is required." );
		}
	}

	public Celebrity getCelebrity() {
		return celebrity;
	}

	public void setCelebrity(Celebrity celebrity) {
		this.celebrity = celebrity;
	}
}
