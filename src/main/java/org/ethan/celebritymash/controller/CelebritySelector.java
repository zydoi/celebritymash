package org.ethan.celebritymash.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.ethan.celebritymash.dao.CelebrityDao;
import org.ethan.celebritymash.model.Celebrity;

public class CelebritySelector extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private final Logger logger = Logger.getLogger(CelebritySelector.class);
	
	private CelebrityDao dao = CelebrityDao.getInstance();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		//Handle the last competition result.
		if(req.getParameter("winner") != null) {
			int winner = Integer.valueOf(req.getParameter("winner"));
			int loser = Integer.valueOf(req.getParameter("loser"));
			logger.info("Winner: "+ winner + " and Loser: " + loser);
			dao.vs(winner, loser);
		}
		Celebrity[] cs = dao.getTwoCelebrities();
		req.setAttribute("p1", cs[0]);
		req.setAttribute("p2", cs[1]);
		cs = dao.getTopRated(10);
		req.setAttribute("top", cs);
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("main.jsp");
		dispatcher.forward(req, resp);
	}
}
