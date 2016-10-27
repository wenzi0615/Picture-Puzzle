package com.picture.ui;

import javax.swing.*;

public class Cell extends JButton{

	public Cell(Icon icon) {
		super(icon);
		this.setSize(165, 165);
	}

	public Cell(String text, Icon icon) {
		super(text, icon);
		this.setSize(165, 165);
		this.setHorizontalTextPosition(CENTER);
		this.setVerticalTextPosition(CENTER);
	}

	public void move(String direction) {
		// TODO Auto-generated method stub
		switch(direction){
		case "RIGHT":
			this.setLocation(this.getBounds().x+165, this.getBounds().y);
			break;
		case "LEFT":
			this.setLocation(this.getBounds().x-165, this.getBounds().y);
			break;
		case "DOWN":
			this.setLocation(this.getBounds().x, this.getBounds().y+165);
			break;
		case "UP":
			this.setLocation(this.getBounds().x, this.getBounds().y-165);
			break;
	}
	}

}
