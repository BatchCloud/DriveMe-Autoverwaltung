package driveMe.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import driveMe.MainRenderer;
import driveMe.constants.DriveMeConstants;

public class DriveMeUtil extends MainRenderer{
	
	public DriveMeUtil()
	{
		
	}
	
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
	
	public void clearAndSetContent(JPanel contentPanel, JPanel content, String borderLayout) 
	{
		contentPanel.removeAll();
//		contentPanel.repaint();
//		contentPanel.revalidate();
		
		if(StringUtils.isNotBlank(borderLayout))
		{
			contentPanel.add(content, borderLayout);
		}
		else
		{
			contentPanel.add(content);
		}
		SwingUtilities.updateComponentTreeUI(contentPanel);
//		contentPanel.repaint();
//		contentPanel.revalidate();
	}
	
	public void clearAndSetContent(JPanel contentPanel, JPanel content) 
	{
		contentPanel.removeAll();
//		contentPanel.repaint();
//		contentPanel.revalidate();
		
		contentPanel.add(content);
		
		SwingUtilities.updateComponentTreeUI(contentPanel);
//		contentPanel.repaint();
//		contentPanel.revalidate();
	}
	
	public void clearAndSetContentForBodyPanel(JPanel content) 
	{
		bodyContentPanel.removeAll();
//		bodyContentPanel.repaint();
//		bodyContentPanel.revalidate();
		
		bodyContentPanel.add(content);
		SwingUtilities.updateComponentTreeUI(bodyContentPanel);
//		bodyContentPanel.repaint();
//		bodyContentPanel.revalidate();
	}
	
	public JPanel createPlaceholderPanel(Dimension preferedSize)
	{
		//Placeholder for bodyContentPanel NORTH
		JPanel placeholder= new JPanel();
		placeholder.setBackground(Color.WHITE);
		placeholder.setPreferredSize(preferedSize);
		
		return placeholder;
	}

}
