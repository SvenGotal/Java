import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;


//Ime klase i ime file-a moraju odgovarati po nazivu inace javac javlja error
public class Igra{
	
	public static void main(String[] args){
		
		JFrame f = new JFrame("simple window");
		f.setSize(250,250);
		f.setLocation(300,200);
		
		final JTextArea tA = new JTextArea(10,40);
		
		f.getContentPane().add(BorderLayout.CENTER, tA);
		final JButton btn = new JButton("button");
		f.getContentPane().add(BorderLayout.SOUTH, btn);
		
		btn.addActionListener(new ActionListener() {
			
			@Override public void actionPerformed(ActionEvent e){
				tA.append("Button was clicked\n");
				
			}
			
		});
		
		
		f.setVisible(true);
		
	}
	
	
	
	
	
}
