package view;

import dao.MovieDAO;

public class EventView {
	public EventView() {
		MovieDAO mdao = new MovieDAO();
		System.out.println("��������������������Event��������������������");
		System.out.println(mdao.event());
	}
}
