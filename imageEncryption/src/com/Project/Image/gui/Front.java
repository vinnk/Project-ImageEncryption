package com.Project.Image.gui;

import com.Project.Image.accept.*;
import com.Project.Image.border.*;
import javax.swing.*;							//for JFrame
import java.awt.*;								//for font
import java.io.*;

public class Front {
	public Front(){
		JFrame frame= new JFrame();
		final JLabel label= new JLabel();
		//JPanel panel= new JPanel();
		Font font= new Font("Roboto", Font.BOLD, 25);
		
		frame.setVisible(true);
		frame.setSize(600,500);
		frame.setTitle("Confidential Image");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		
		JButton button_en= new JButton();
		button_en.setText("Encryption");
		button_en.setFont(font);
		
		JButton button_de= new JButton();
		button_de.setText("Decryption");
		button_de.setFont(font);
		
		frame.add(button_en);
		frame.add(button_de);
		frame.setLayout(new FlowLayout());							//layout
		
		button_en.addActionListener(e->{							//button to encrypt
			System.out.println("button clicked");
			JFileChooser fileChooser= new JFileChooser();
			
			fileChooser.addChoosableFileFilter(new ImageFilter());
			fileChooser.setAcceptAllFileFilterUsed(false);
			
			int option= fileChooser.showOpenDialog(null);						//to open the window to select the file
			
			if(option== JFileChooser.APPROVE_OPTION) {
				File file= fileChooser.getSelectedFile();				//to store selected file
				label.setText("File Selected; "+file.getName());
				
				try {
					FileInputStream fin= new FileInputStream(file);		//to read the contents of the file
					byte[] data= new byte[fin.available()];				//to read the data
					
					fin.read(data);										//reading
					fin.close();										//closing the reader
					
					Border border= new Border();						//algorithm
					byte[] d= border.add(data,1);
					
					/*int i=0;											//to iterate through the data array, for modification
					for(byte b : data) {								//reading the data byte by byte
						System.out.println(b);
						data[i]= (byte)(b^50);
						i++;
					}*/
					
					FileOutputStream fout= new FileOutputStream(file);		
					fout.write(d);										//writing changes back to the file
					fout.close();
					
					JOptionPane.showMessageDialog(null, "Image Encrypted");			//message after completion
				}
				catch(Exception E) {
					System.out.println(E);
				}
			}
			else																//if selected file is not an image
				JOptionPane.showMessageDialog(null, "Open command canceled");
				
		});
		
		button_de.addActionListener(e->{							//button to decrypt
			System.out.println("button clicked");
			JFileChooser fileChooser= new JFileChooser();
			
			fileChooser.addChoosableFileFilter(new ImageFilter());		//apply filter on selected file
			fileChooser.setAcceptAllFileFilterUsed(false);				//all file types are not selected
			
			int option= fileChooser.showOpenDialog(null);				//-----open window to select file from, the code returned is stored
			
			if(option== JFileChooser.APPROVE_OPTION) {					//if the code matches the approve condition-->accept and work further
				File file= fileChooser.getSelectedFile();
				label.setText("File Selected: "+file.getName());
				
				try {
					FileInputStream fin= new FileInputStream(file);
					byte[] data= new byte[fin.available()];
					fin.read(data);
					fin.close();
					
					int i=0;
					for(byte b : data) {
						System.out.println(b);
						data[i]= b;
						i++;
					}
					FileOutputStream fout= new FileOutputStream(file);
					fout.write(data);
					fout.close();
					
					JOptionPane.showMessageDialog(null, "Image Decrypted");
				}
				catch(Exception E) {
					System.out.println(E);
				}
			}
			else 																	//else reject
				JOptionPane.showMessageDialog(null, "Open command canceled");
			
		});
		
		//frame.add(label);
		//frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
}
