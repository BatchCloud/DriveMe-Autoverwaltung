package driveMe;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class RunApplication extends MainController{

	public static void main(String[] args) {
		
		Color primaryColor = new Color(105,157,217);
		Color secondColor = new Color(238,238,238);
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					 UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
					 UIManager.put("JTextField.background", new ColorUIResource(Color.white));
				        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(primaryColor));
				        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.WHITE));
				        UIManager.put("TabbedPane.borderColor", Color.RED);
				        UIManager.put("TabbedPane.tabAreaBackground", Color.RED);
				        UIManager.put("TabbedPane.unselectedTabBackground", Color.RED);
					MainController window = new MainController();
					window.mainFrame.setVisible(true);
					window.initialize();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
