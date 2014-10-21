package org.ethan.celebritymash.model;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *	TODO Synchronization! 
 *
 */
public class CelebrityMap {
	private AtomicInteger idGenerator = new AtomicInteger();

	private HashMap<Integer, Celebrity> celebrities = new HashMap<Integer, Celebrity>();
	
	private static CelebrityMap celebrityMap = new CelebrityMap();
	
	
	private CelebrityMap() {
		Celebrity c = new Celebrity();
		c.setName("Jennifer Lawrence");
		c.setImage("http://img5.douban.com/img/celebrity/large/21507.jpg");
		this.addCelebrity(c);
		c = new Celebrity();
		c.setName("Mila Kunis");
		c.setImage("http://img5.douban.com/img/celebrity/large/31758.jpg");
		this.addCelebrity(c);
	}
	
	public static CelebrityMap getInstance() {
		return celebrityMap;
	}
	
	public int addCelebrity(Celebrity c) {
		c.setId(idGenerator.getAndIncrement());
		celebrities.put(c.getId(), c);
		return c.getId();
	}
	
	public void vs(int wid, int lid) {
		Celebrity winner = celebrities.get(wid);
		winner.setWon(winner.getWon() + 1);
		Celebrity loser = celebrities.get(lid);
		loser.setLost(loser.getLost() + 1);
	}
	
	/**
	 * Randomly returns a celebrity.
	 * @return
	 */
	public Celebrity getCelebrity() {
		Random random = new Random();
		//TODO
		return celebrities.get(random.nextInt(celebrities.size()));
	}
	
	/**
	 * Randomly returns two different celebrities.
	 * @return
	 */
	public Celebrity[] getCelebrities() {
		Celebrity[]	 results = new Celebrity[2];
		Random random = new Random();
		int id = random.nextInt(celebrities.size());
		results[0] = celebrities.get(id);
		int id2 = random.nextInt(celebrities.size());
		while(id2 == id) {
			id2 = random.nextInt(celebrities.size());
		}
		results[1] = celebrities.get(id2);
		return results;
	}
	
}
