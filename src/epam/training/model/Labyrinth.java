package epam.training.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import epam.training.interfaces.Observer;
import epam.training.interfaces.Subject;

public class Labyrinth implements Subject{
	
	public final static String symbolWall = "|";
	public final static String symbolPassage = " ";
	private List <String[]> m_cells = new ArrayList <String[]>();
	private Position entryPosition;
    private List<Position> exitPositions;
    private int maxX;
    private int maxY;
    private ArrayList<Observer> observers;
	private int quantity_walls;
	private int quantity_passeges;
    
	public static enum Block {
		ENTRY("I"), EXIT("X"), WALL("1"), PASSAGE("0");
		
	 String value;
	 Block(String value)
     {
		 this.value = value;
     }
     
	 @Override
      public String toString()
      {
          return value;
      }
    }	
	
	
    public Labyrinth() {
    	observers = new ArrayList<Observer>();
	}

    private void init() throws IOException{
    	this.exitPositions = new ArrayList<Position>();
    	this.maxX = m_cells.get(0).length - 1;
		this.maxY = m_cells.size()-1;
		setPoints();
	}
    
	private void setPoints() throws IOException{
		for(String[] cell: m_cells)
	    {
			for (int i = 0; i < cell.length; i++)
	        { 
			  String str = cell[i];	
	       
	          for (Block  block: Block.values()) {
	        	  if ( str.equals(block.toString())) {
	        	  
	        		  switch (block)
	    	          {
	    					case ENTRY:
	    						this.entryPosition = new Position(i, m_cells.indexOf(cell));
	    						break;
	    					case EXIT:
	    						exitPositions.add(new Position(i, m_cells.indexOf(cell)));
	    						break;	
	    					case WALL:
	    						this.quantity_walls++;
	    					    break;
	    					case PASSAGE:
	    						this.quantity_passeges++;
	    					    break;
	    					default: throw new IOException("Not correct data");
	    		      } 
	              }
	          }
			}
	    }
	}
	
   public List<String[]> getCells() {
		return m_cells;
	}

	public void setCells(List<String[]> m_cells) throws IOException {
		this.m_cells = m_cells;
		if (m_cells != null && !m_cells.isEmpty()) {
			init();
			dataChanged();
		}
	}

    public int getMaxX() {
		return maxX;
	}
    
    public int getMaxY() {
		return maxY;
	}

    public Position getEntryPosition() {
		return entryPosition;
	}
    
    public void setEntryPosition(Position entryPosition) {
		this.entryPosition = entryPosition;
	}

    public List<Position> getExitPositions() {
		return exitPositions;
	}

    public void setExitPosition(List<Position> exitPositions) {
		this.exitPositions = exitPositions;
	}
    
    @Override
	public void registerObserver(Observer o) {
		observers.add(o);
	}

	@Override
	public void removeObserver(Observer o) {
		int i = observers.indexOf(o);
		if (i >=0) {
			observers.remove(i);
		}
	}

	@Override
	public void notifyObservers() {
		for (int i = 0; i < observers.size(); i++) {
			Observer observer = (Observer) observers.get(i);
			observer.update(quantity_walls, quantity_passeges);
		}
	}
    
	public void dataChanged() {
		notifyObservers();
	}
	
}
	

