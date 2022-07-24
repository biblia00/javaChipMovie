package view;

import dao.MovieDAO;

public class EventView {
	public EventView() {
		MovieDAO mdao = new MovieDAO();
		System.out.println("式式式式式式式式式式Event式式式式式式式式式式");
		System.out.println(mdao.event());
	}
}
