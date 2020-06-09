package driveMe.util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import org.apache.commons.lang.StringUtils;

import driveMe.MainRenderer;
import driveMe.constants.DriveMeConstants;
import driveMe.service.DatabaseService;

public class DriveMeUtil {
	
	DatabaseService dataBaseService;
	
	public DriveMeUtil()
	{
		dataBaseService = new DatabaseService();
	}
	
	public ImageIcon resizeImageIcon(String url, int heigth, int width) 
	{
		ImageIcon imageIcon = new ImageIcon(MainRenderer.class.getResource(DriveMeConstants.Picture.PATH + url));
		
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(heigth, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg);
	}
	
	public ImageIcon resizeImageIconURL(String vehicleUrl, int heigth, int width) throws IOException 
	{
		URL url = new URL(vehicleUrl);
		
		Image image = ImageIO.read(url); // transform it 
		Image newimg = image.getScaledInstance(heigth, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		
		ImageIcon imageIcon = new ImageIcon(newimg);
		return imageIcon;
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
	
	public JPanel clearAndSetContent(JPanel contentPanel, JPanel content, String borderLayout) 
	{
		contentPanel.removeAll();
		
		if(StringUtils.isNotBlank(borderLayout))
		{
			contentPanel.add(content, borderLayout);
		}
		else
		{
			contentPanel.add(content);
		}
		SwingUtilities.updateComponentTreeUI(contentPanel);
		contentPanel.repaint();
		contentPanel.revalidate();
		return contentPanel;	
//		
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
	
	public void clearAndSetContentForBodyPanel(JPanel bodyContentPanel, JPanel content) 
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
	
	public String getStringFromSubComponentCombobox(Component currentComponent)
	{
		if(currentComponent instanceof Container) {
			Container subCont = (Container) currentComponent;
			for(Component currSubComponent : subCont.getComponents()) {
				if(currSubComponent instanceof JComboBox) {
					JComboBox<?> brand = (JComboBox<?>) currSubComponent;
					String brandName = brand.getSelectedItem().toString();
					return brandName;
				}
			}
		}
		return "";
	}
	
	public String getStringFromSubComponentTextField(Component currentComponent)
	{
		if(currentComponent instanceof Container) {
			Container subCont = (Container) currentComponent;
			for(Component currSubComponent : subCont.getComponents()) {
				if(currSubComponent instanceof JTextField) {
					JTextField model = (JTextField) currSubComponent;
					String textFieldInput = model.getText();
					return textFieldInput;
				}
			}
		}
		return "";
	}
	
}
