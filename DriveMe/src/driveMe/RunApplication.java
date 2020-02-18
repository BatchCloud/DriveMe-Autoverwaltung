package driveMe;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.plaf.ColorUIResource;

public class RunApplication extends MainController{

	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					 UIManager.put("ComboBox.background", new ColorUIResource(Color.white));
					 UIManager.put("JTextField.background", new ColorUIResource(Color.white));
				        UIManager.put("ComboBox.selectionBackground", new ColorUIResource(Color.lightGray));
				        UIManager.put("ComboBox.selectionForeground", new ColorUIResource(Color.black));
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
