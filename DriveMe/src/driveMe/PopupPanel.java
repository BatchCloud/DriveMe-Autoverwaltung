package driveMe;

import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.util.Random; 
@SuppressWarnings("serial")
class PopupPanel extends JFrame implements ActionListener { 
	  // popup 
    Popup po; 
  
    // frame 
    JFrame frame;
  
    // panel 
    JPanel p; 
  
    // popupfactory 
    PopupFactory pf;
  
    // constructor 
    PopupPanel(JFrame frame,PopupFactory pf ) 
    { 
        // create a frame 
    	this.frame = frame;
  
    this.pf = pf;
  
        // create a label 
        JLabel l = new JLabel("This  is a popup menu"); 
  
        // create a new button 
        JButton b19 = new JButton("OK"); 
  
        // add action listener 
        b19.addActionListener(this); 
  
        try { 
            // set windows look and feel 
            UIManager.setLookAndFeel(UIManager. 
                  getSystemLookAndFeelClassName()); 
        } 
        catch (Exception e) { 
        } 
  
        // create a panel 
        p = new JPanel(); 
        p.setPreferredSize(new Dimension(400,400));
        p.setBackground(Color.blue); 
  
        // create a font 
        Font fo = new Font("BOLD", 1, 14); 
  
        l.setFont(fo); 
  
        // add contents to panel 
        p.add(l); 
        p.add(b19); 
  
        p.setLayout(new GridLayout(2, 1)); 
        Random random = new Random();
        int x = random.nextInt(200);
        int y = random.nextInt(200);
        // create a popup 
        po = pf.getPopup(frame, p, x, y); 
  
        // create a button 
        
        po.show(); 

    } 
  
    // if the button is pressed 
    public void actionPerformed(ActionEvent e) 
    { 
        String d = e.getActionCommand(); 
        // if ok buton is pressed hide the popup 
        if (d.equals("OK")) { 
            po.hide(); 
  
            // create a popup 
            po = pf.getPopup(frame, p, 180, 100); 
        } 
        else
            po.show(); 
    }  

} 