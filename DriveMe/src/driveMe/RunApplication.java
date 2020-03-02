package driveMe;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

import driveMe.constants.DriveMeConstants;

public class RunApplication extends MainRenderer{

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					 UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
					 UIManager.put("JTextField.background", new ColorUIResource(Color.white));
				        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(DriveMeConstants.Colour.primaryColor));
				        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
				        UIManager.put("TabbedPane.borderColor", Color.RED);
				        UIManager.put("TabbedPane.tabAreaBackground", Color.RED);
				        UIManager.put("TabbedPane.unselectedTabBackground", Color.RED);
				    MainRenderer window = new MainRenderer();
					window.mainFrame.setVisible(true);
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
