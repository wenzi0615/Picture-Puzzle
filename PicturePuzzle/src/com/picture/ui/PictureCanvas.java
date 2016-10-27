package com.picture.ui;

import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PictureCanvas extends JPanel implements MouseListener{
	public static int pictureID=1;
	public static int stepNum=0;
	
	Cell[] cell;
	int count=9;
	private boolean hasAddActionListener=false;
	private Rectangle nullCell;
	
	public PictureCanvas(){
		this.setLayout(null);//帧布局 按照像素
		cell=new Cell[12];
		for(int i=0;i<count;i++){
				String filename="picture/"+PictureCanvas.pictureID+"_"+(i+1)+".jpg";
				ImageIcon icon=new ImageIcon(filename);
				cell[i]=new Cell(icon);
				cell[i].setSize(165, 165);
				cell[i].setLocation((i%3)*165+2, (i/3)*165+15);
				this.add(cell[i]);
		}
		this.remove(cell[count-1]);
		nullCell = new Rectangle(332,345,165,165);
	}
	
	public void reLoadPictureAddNumber(){
		for(int i=0;i<count;i++){
			String filename="picture/"+PictureCanvas.pictureID+"_"+(i+1)+".jpg";
			ImageIcon icon=new ImageIcon(filename);
			cell[i].setIcon(icon);
			cell[i].setText(""+(i+1));
			cell[i].setVerticalTextPosition(this.getY()/2);
			cell[i].setHorizontalTextPosition(this.getX()/2);;
		}
	}
	
	public void reLoadPictureClearNumber(){
		for(int i=0;i<count;i++){
			String filename="picture/"+PictureCanvas.pictureID+"_"+(i+1)+".jpg";
			ImageIcon icon=new ImageIcon(filename);
			cell[i].setIcon(icon);
			cell[i].setText("");
		}
	}

	public void start() {
		if(!hasAddActionListener){
			for(int i=0;i<count-1;i++)
				cell[i].addMouseListener(this);
			hasAddActionListener=true;
		}
		
		while(cell[0].getBounds().x<=167&&cell[0].getBounds().y<=180){
			int nullx=nullCell.getBounds().x;
			int nully=nullCell.getBounds().y;
			
			int direction=(int)(Math.random()*4);
			
			switch(direction){
				case 0:
					nullx-=165;
					cellMove(nullx, nully, "RIGHT");
					break;//left
				case 1:
					nullx+=165;
					cellMove(nullx, nully, "LEFT");
					break;//right
				case 2:
					nully-=165;
					cellMove(nullx, nully, "DOWN");
					break;//up
				case 3:
					nully+=165;
					cellMove(nullx, nully, "UP");
					break;//down
			}
		}
	}
	
	private void cellMove(int nullx, int nully, String direction){
		for(int i=0;i<count-1;i++){
			if(cell[i].getBounds().x==nullx&&cell[i].getBounds().y==nully){
				cell[i].move(direction);
				nullCell.setLocation(nullx, nully);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {//
		// TODO Auto-generated method stub
		Cell button=(Cell) e.getSource();
		
		int clickx=button.getBounds().x;
		int clicky=button.getBounds().y;
		
		int nullx=nullCell.getBounds().x;
		int nully=nullCell.getBounds().y;
		
		if(clickx==nullx&&clicky-nully==165){//cell below
			button.move("UP");
		}else if(clickx==nullx&&clicky-nully==-165){//cell above
			button.move("DOWN");
		}else if(clickx-nullx==165&&clicky==nully){//cell on the right
			button.move("LEFT");
		}else if(clickx-nullx==-165&&clicky==nully){//cell below
			button.move("RIGHT");
		}else{
			return;
		}
		
		nullCell.setLocation(clickx,clicky);
		this.repaint();
		stepNum++;
		PictureMainFrame.step.setText("Steps: "+stepNum);
		
		if(this.isFinish()){
			JOptionPane.showMessageDialog(this, "Congraduation! "+stepNum+"steps");
			for(int i=0;i<count-1;i++)
				cell[i].removeMouseListener(this);
			hasAddActionListener=false;
		}
		
	}

	private boolean isFinish() {
		for(int i=0;i<count-1;i++){
			int x=cell[i].getBounds().x;
			int y=cell[i].getBounds().y;
			if(!((x-2)/165==(i%3)&&(y-15)/165==(i/3))){
				return false;
			}
		}
		return true;
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
