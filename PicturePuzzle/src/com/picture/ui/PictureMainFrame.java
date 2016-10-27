package com.picture.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class PictureMainFrame extends JFrame{

	private JRadioButton addNumInfo;
	private JRadioButton clearNumInfo;
	private PictureCanvas canvas;
	private PicturePreview preview;
	private JComboBox<String> box;
	private JTextField name;
	public static JTextField step;
	private JButton start;

	public PictureMainFrame(){
		init();
		addCompoent();
		addPreviewImage();
		addActionListener();
	}

	private void addActionListener() {
		addNumInfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				canvas.reLoadPictureAddNumber();
			}
		});
		
		clearNumInfo.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				canvas.reLoadPictureClearNumber();
			}
		});
		
		box.addItemListener(new ItemListener(){

			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				int num=box.getSelectedIndex();
				PictureCanvas.pictureID=num+1;
				preview.repaint();
				
				canvas.reLoadPictureClearNumber();
				name.setText("Picture: "+box.getSelectedItem());
				PictureCanvas.stepNum=0;;
				step.setText("Steps: "+PictureCanvas.stepNum);
				
				clearNumInfo.setSelected(true);
			}
			
		});
		
		start.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PictureCanvas.stepNum=0;
				step.setText("Steps: "+PictureCanvas.stepNum);
//				System.out.println("111111111");
				canvas.start();
			}
		});
	}

	private void addPreviewImage() {
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1, 2));
		
		canvas = new PictureCanvas();
		canvas.setBorder(new TitledBorder("Puzzle"));
		
		preview = new PicturePreview();
		preview.setBorder(new TitledBorder("Preview"));
		
		panel.add(canvas,BorderLayout.WEST);
		panel.add(preview,BorderLayout.EAST);	
		
		this.add(panel,BorderLayout.CENTER);
	}

	private void addCompoent() {
		JPanel panel=new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setLayout(new GridLayout(1,2));
		
		JPanel leftPanel=new JPanel();
		leftPanel.setBorder(new TitledBorder("Button"));
		leftPanel.setBackground(Color.GRAY);
		
		addNumInfo = new JRadioButton("Tips",false);
		clearNumInfo = new JRadioButton("Clear",true);
		ButtonGroup buttonGroup=new ButtonGroup();
		JLabel choose =new JLabel("		Choose Pic: ");
		String[] items={"a","b"};
		box = new JComboBox<String>(items);
		start = new JButton("Start");
		buttonGroup.add(addNumInfo);
		buttonGroup.add(clearNumInfo);
		
		leftPanel.add(addNumInfo);
		leftPanel.add(clearNumInfo);
		leftPanel.add(choose);
		leftPanel.add(box);
		leftPanel.add(start);
		
		JPanel rightPanel=new JPanel();
		rightPanel.setBorder(new TitledBorder("Status"));
		rightPanel.setBackground(Color.GRAY);
		rightPanel.setLayout(new GridLayout(1,2));
		name = new JTextField("Picture: a");
		step = new JTextField("Steps: 0");
		name.setEditable(false);
		step.setEditable(false);
		
		rightPanel.add(name, BorderLayout.WEST);
		rightPanel.add(step, BorderLayout.EAST);
		
		panel.add(leftPanel, BorderLayout.WEST);
		panel.add(rightPanel, BorderLayout.EAST);
		this.add(panel, BorderLayout.NORTH);
	}

	private void init() {
		this.setTitle("Jigsaw Puzzle");
		this.setSize(1000, 600);
		this.setLocation(150, 60);
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//close and exit
	}

}
