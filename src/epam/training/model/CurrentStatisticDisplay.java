package epam.training.model;

import epam.training.interfaces.DisplayElement;
import epam.training.interfaces.Observer;
import epam.training.interfaces.Subject;

public class CurrentStatisticDisplay implements Observer, DisplayElement{
	
	private int quantity_walls;
	private int quantity_passeges;
	private Subject labyrinth;
	
	
	public CurrentStatisticDisplay(Subject labyrinth) {
		this.labyrinth = labyrinth;
		labyrinth.registerObserver(this);
	}

	@Override
	public void update(int quantity_walls, int quantity_passeges) {
		this.quantity_walls = quantity_walls;
		this.quantity_passeges = quantity_passeges;
		display();
	}


	@Override
	public void display() {
		System.out.println("Current statistic:" + quantity_walls + " walls and " 
				+ quantity_passeges + " passeges");
		
	}
}
