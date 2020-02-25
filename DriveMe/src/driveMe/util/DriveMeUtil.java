package driveMe.util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.lang.StringUtils;

import driveMe.MainRenderer;
import driveMe.constants.DriveMeConstants;

public class DriveMeUtil {
	
	
	public ImageIcon resizeImageIcon(String url, int heigth, int width) 
	{
		ImageIcon imageIcon = new ImageIcon(MainRenderer.class.getResource(DriveMeConstants.Picture.PATH + url));
		
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(heigth, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg);
	}
	
	public void addComboBoxToPanel(JPanel panel)
	{
		JComboBox<String> comboBox = new JComboBox<String>();
	
		comboBox.setForeground(Color.WHITE);
		comboBox.setBorder(null);
		comboBox.setBackground(DriveMeConstants.Colour.primaryColor);
		comboBox.setPreferredSize(new Dimension(145, 25));
		comboBox.addItem("Filtern");
		comboBox.addItem("test");

		panel.add(comboBox);
	}
	
	public void clearAndSetContent(JPanel bodyContentPanel, JPanel content, String borderLayout) 
	{
		bodyContentPanel.removeAll();
		bodyContentPanel.repaint();
		bodyContentPanel.revalidate();
		
		if(StringUtils.isNotBlank(borderLayout))
		{
			bodyContentPanel.add(content, borderLayout);
		}
		else
		{
			bodyContentPanel.add(content);
		}
		
		bodyContentPanel.repaint();
		bodyContentPanel.revalidate();
	}

	
}
