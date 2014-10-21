package org.ethan.celebritymash.action;

import org.ethan.celebritymash.dao.CelebrityDao;
import org.ethan.celebritymash.model.Celebrity;

import com.opensymphony.xwork2.ActionSupport;


public class SelectAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	private Celebrity celebrityL;
	
	private Celebrity celebrityR;
	
	private Celebrity winner;
	
	private Celebrity loser;

	private Celebrity[] topRated;
	
	private CelebrityDao dao = CelebrityDao.getInstance();
	
	public String select() {
		dao.vs(winner.getId(), loser.getId());
		return SUCCESS;
	}

	public String input() {
		Celebrity[] cs = dao.getTwoCelebrities();
		setCelebrityL(cs[0]);
		setCelebrityR(cs[1]);
		setTopRated(dao.getTopRated(10));
		return SUCCESS;
	}
	
	public Celebrity getCelebrityL() {
		return celebrityL;
	}

	public void setCelebrityL(Celebrity celebrityL) {
		this.celebrityL = celebrityL;
	}

	public Celebrity getCelebrityR() {
		return celebrityR;
	}

	public void setCelebrityR(Celebrity celebrityR) {
		this.celebrityR = celebrityR;
	}

	public Celebrity getWinner() {
		return winner;
	}

	public void setWinner(Celebrity winner) {
		this.winner = winner;
	}

	public Celebrity[] getTopRated() {
		return topRated;
	}

	public void setTopRated(Celebrity[] topRated) {
		this.topRated = topRated;
	}
	
	public Celebrity getLoser() {
		return loser;
	}

	public void setLoser(Celebrity loser) {
		this.loser = loser;
	}
	
}
