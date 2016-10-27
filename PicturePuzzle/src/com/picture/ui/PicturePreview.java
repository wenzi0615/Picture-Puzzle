package com.picture.ui;

import java.awt.*;
import javax.swing.*;

public class PicturePreview extends JPanel{

	@Override
	protected void paintComponent(Graphics g){
		super.paintComponent(g);
		String filename="picture/"+PictureCanvas.pictureID+".jpg";
		ImageIcon icon=new ImageIcon(filename);
		Image image=icon.getImage();
		g.drawImage(image, 2, 15, 495,495,this);
		
	}
}
