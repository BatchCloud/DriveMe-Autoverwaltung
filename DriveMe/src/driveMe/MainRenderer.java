package driveMe;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowStateListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.PopupFactory;
import javax.swing.border.MatteBorder;

import org.apache.commons.lang.StringUtils;

import driveMe.customers.model.Customer;
import driveMe.customers.service.CustomerService;
import driveMe.constants.DriveMeConstants;
import driveMe.vehicles.model.Vehicle;
import driveMe.vehicles.service.VehicleService;
import net.miginfocom.swing.MigLayout;
import java.awt.CardLayout;

import java.awt.Component;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class MainRenderer {

public JFrame mainFrame=new JFrame("DriveMe");
	
	private Color primaryColor = new Color(105,157,217);
	private Color secondColor = new Color(238,238,238);
	JLayeredPane bodyJLayeredPane;
	private boolean vehiclePageActive = true;
	
	/**
	 * @wbp.parser.entryPoint
	 */
	protected void initialize() {

		//Setup Main frame
//		mainFrame.setType(Type.POPUP);
		mainFrame.setBackground(primaryColor);
		mainFrame.getContentPane().setMinimumSize(new Dimension(20, 16));
		mainFrame.setBounds(100, 100, 1106, 661);
		mainFrame.setMinimumSize(new Dimension(1120, 600));
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		
		bodyJLayeredPane = new JLayeredPane();
		
		//Main Body Content Panel for Vehicle
		JPanel bodyContentPanel = new JPanel();
		bodyJLayeredPane.setLayer(bodyContentPanel, 1);
		bodyContentPanel.setBackground(Color.WHITE);
		bodyContentPanel.setPreferredSize(new Dimension(26, 90));
		bodyContentPanel.setLayout(new BorderLayout(0, 0));
		
		setUpHeader(bodyContentPanel);
		
		bodyContentPanel.add(vehicleContent(), "name_47788877080200");
		bodyContentPanel.setLayout(new CardLayout(0, 0));
		bodyContentPanel.add(vehicleContent(), "name_47788877080200");
		bodyContentPanel.setVisible(true);
		bodyContentPanel.setBounds(0, 0, (mainFrame.getWidth() + 100), (mainFrame.getHeight() - 130) );	
		
		bodyJLayeredPane.add(bodyContentPanel);
		
		mainFrame.getContentPane().add(bodyJLayeredPane);
		mainFrame.setVisible(true);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
            	bodyContentPanel.setBounds(0, 0, mainFrame.getWidth(), (mainFrame.getHeight() - 130) );
            }

        });
		mainFrame.addWindowStateListener(new WindowStateListener() {
			public void windowStateChanged(WindowEvent arg0) {
				bodyContentPanel.setBounds(0, 0, mainFrame.getWidth(), (mainFrame.getHeight() - 130) );
			}
		});
    
	}
	private void setUpHeader(JPanel bodyContentPanel) 
	{
		//Setup header pannel
		JPanel headerPanel = new JPanel();
		headerPanel.setPreferredSize(new Dimension(10, 90));
		headerPanel.setMinimumSize(new Dimension(10, 128));
		headerPanel.setBackground(SystemColor.windowBorder);
		mainFrame.getContentPane().add(headerPanel, BorderLayout.NORTH);
		headerPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel headerBottom = new JPanel();
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		setUpHeaderTop(bodyContentPanel,headerPanel,headerBottom);
		
		
		headerBottom.add(vehicleHeaderBottom(),BorderLayout.CENTER);
		headerPanel.add(headerBottom, BorderLayout.CENTER);
		
	}

	
	private void setUpHeaderTop(JPanel bodyContentPanel,JPanel headerPanel,JPanel headerBottom ) {
		//Setup header top to header pannel
		JPanel headerTop = new JPanel();
		headerPanel.add(headerTop, BorderLayout.NORTH);
		headerTop.setMaximumSize(new Dimension(32767, 0));
		headerTop.setMinimumSize(new Dimension(0, 0));
		headerTop.setPreferredSize(new Dimension(0, 35));
		headerTop.setBackground(primaryColor);
		
		//Set FlowLayout that buttons at headerTop are left justified
		FlowLayout headerTopFlowLayout = (FlowLayout) headerTop.getLayout();
		headerTopFlowLayout.setVgap(0);
		headerTopFlowLayout.setHgap(0);
		headerTopFlowLayout.setAlignment(FlowLayout.LEFT);
		
		//Add Buttons to header
		JButton customerButton = new JButton("Kunden");
		customerButton.setForeground(Color.WHITE);
		customerButton.setBorder(null);
		customerButton.setBackground(primaryColor);
		customerButton.setMargin(new Insets(0, 0, 0, 0));
		customerButton.setPreferredSize(new Dimension(150, 35));
		
		JButton vehicleButton = new JButton("Fahrzeuge");
		vehicleButton.setBorder(null);
		vehicleButton.setForeground(primaryColor);
		vehicleButton.setBackground(secondColor);
		vehicleButton.setMargin(new Insets(0, 0, 0, 0));
		vehicleButton.setPreferredSize(new Dimension(150, 35));
		headerTop.add(vehicleButton);
		
	
		JPanel customerContent = customerContent();
		JPanel vehicleContent = vehicleContent();
		
		JPanel customeHeaderBottom = customerHeaderBottom();
		JPanel vehicleHeaderBottom = vehicleHeaderBottom();
		
		customerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(secondColor);	
				customerButton.setForeground(primaryColor);
				vehicleButton.setBackground(primaryColor);	
				vehicleButton.setForeground(Color.WHITE);
				bodyContentPanel.removeAll();
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
				bodyContentPanel.add(customerContent);
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
				
				headerBottom.removeAll();
				headerBottom.repaint();
				headerBottom.revalidate();
				headerBottom.add(customeHeaderBottom, BorderLayout.CENTER);
				headerBottom.repaint();
				headerBottom.revalidate();
				vehiclePageActive = false;
			}
		});
		vehicleButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				customerButton.setBackground(primaryColor);	
				customerButton.setForeground(Color.WHITE);
				vehicleButton.setBackground(secondColor);	
				vehicleButton.setForeground(primaryColor);
				bodyContentPanel.removeAll();
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
				bodyContentPanel.add(vehicleContent);
				bodyContentPanel.repaint();
				bodyContentPanel.revalidate();
				
				headerBottom.removeAll();
				headerBottom.repaint();
				headerBottom.revalidate();
				headerBottom.add(vehicleHeaderBottom, BorderLayout.CENTER);
				headerBottom.repaint();
				headerBottom.revalidate();
				vehiclePageActive = true;
			}
		});
		headerTop.add(customerButton);
	}
	
	private JPanel vehicleHeaderBottom() 
	{
		JPanel headerBottom = new JPanel();
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		//Set leftSidePanel to header bottom
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(330, 10));
		headerBottom.add(leftSidePanel, BorderLayout.WEST);
		
		JButton btnFahrzeugAnlegen = new JButton("Fahrzeug Anlegen");
		btnFahrzeugAnlegen.setForeground(Color.WHITE);
		btnFahrzeugAnlegen.setPreferredSize(new Dimension(145, 25));
		btnFahrzeugAnlegen.setMargin(new Insets(0, 0, 0, 0));
		btnFahrzeugAnlegen.setBorder(null);
		btnFahrzeugAnlegen.setBackground(primaryColor);
		btnFahrzeugAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				JPanel p = new JPanel(); 
//		        p.setPreferredSize(new Dimension(400,400));
//		        p.setBackground(Color.blue); ;
//
//				p.setBounds( (mainFrame.getWidth() / 2) - 400,  (mainFrame.getHeight()/ 2) - 400 , 400,400 );
//	        	p.setVisible(true);
//	        	bodyJLayeredPane.add(p, JLayeredPane.POPUP_LAYER);
				 JPanel panel = new JPanel();
				 panel.add(new JButton("Click"));
				 panel.add(new JTextField(20));
				 panel.add(new JLabel("Label"));
				 JOptionPane.showMessageDialog(mainFrame,panel,"Information",JOptionPane.INFORMATION_MESSAGE);
			
			}
		});
		leftSidePanel.add(btnFahrzeugAnlegen);
		
		JButton btnFahrzeugsettings = new JButton("Fahrzeuge ändern");
		btnFahrzeugsettings.setPreferredSize(new Dimension(145, 25));
		btnFahrzeugsettings.setMargin(new Insets(0, 0, 0, 0));
		btnFahrzeugsettings.setForeground(Color.WHITE);
		btnFahrzeugsettings.setBorder(null);
		btnFahrzeugsettings.setBackground(new Color(105, 157, 217));
		leftSidePanel.add(btnFahrzeugsettings);

		//Set rightSidePanel to header bottom
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(200, 10));
		rightSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(rightSidePanel, BorderLayout.EAST);
		//Add compobox to rightSidePanel
		addComboBoxToPanel(rightSidePanel);
		
		//Set topSidePanel to header bottom
		JPanel topSidePanel = new JPanel();
		headerBottom.add(topSidePanel, BorderLayout.NORTH);

		//Set bottomSidePanel to header bottom		
		JPanel bottomSidePanel = new JPanel();
		headerBottom.add(bottomSidePanel, BorderLayout.SOUTH);
		
		//Set midSidePanel to header bottom
		JPanel midSidePanel = new JPanel();
		midSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(midSidePanel, BorderLayout.CENTER);
		//Set textField to midSidePanel
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(400, 25));
		textField.setSize(new Dimension(400, 24));
		textField.setColumns(50);
		textField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	            	String input = textField.getText();
	            	if(input.length() >2)
	            	{
	            		if(vehiclePageActive)
		            	{

		            	}
		            	else if(!vehiclePageActive)
		            	{
		            		
		            	}
	            	}
	            	
	            }
	    });
		midSidePanel.add(textField);
		
		return headerBottom;
	}

	private JPanel customerHeaderBottom( ) 
	{
		JPanel headerBottom = new JPanel();
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		//Set leftSidePanel to header bottom
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(200, 10));
		
		//Add compobox to rightSidePanel
		addComboBoxToPanel(leftSidePanel);
		headerBottom.add(leftSidePanel, BorderLayout.WEST);

		//Set rightSidePanel to header bottom
		JPanel rightSidePanel = new JPanel();
		rightSidePanel.setPreferredSize(new Dimension(200, 10));
		rightSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(rightSidePanel, BorderLayout.EAST);
		
		JButton btnFahrzeugAnlegen = new JButton("Benutzer Anlegen");
		btnFahrzeugAnlegen.setForeground(Color.WHITE);
		btnFahrzeugAnlegen.setPreferredSize(new Dimension(145, 25));
		btnFahrzeugAnlegen.setMargin(new Insets(0, 0, 0, 0));
		btnFahrzeugAnlegen.setBorder(null);
		btnFahrzeugAnlegen.setBackground(primaryColor);
		btnFahrzeugAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
//				JPanel p = new JPanel(); 
//		        p.setPreferredSize(new Dimension(400,400));
//		        p.setBackground(Color.blue); ;
//
//				p.setBounds( (mainFrame.getWidth() / 2) - 400,  (mainFrame.getHeight()/ 2) - 400 , 400,400 );
//	        	p.setVisible(true);
//	        	bodyJLayeredPane.add(p, JLayeredPane.POPUP_LAYER);
			
				JOptionPane.showMessageDialog(mainFrame,new JTextField());
			
			}
		});
		rightSidePanel.add(btnFahrzeugAnlegen);
		
		//Set topSidePanel to header bottom
		JPanel topSidePanel = new JPanel();
		headerBottom.add(topSidePanel, BorderLayout.NORTH);

		//Set bottomSidePanel to header bottom		
		JPanel bottomSidePanel = new JPanel();
		headerBottom.add(bottomSidePanel, BorderLayout.SOUTH);
		
		//Set midSidePanel to header bottom
		JPanel midSidePanel = new JPanel();
		midSidePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		headerBottom.add(midSidePanel, BorderLayout.CENTER);
		//Set textField to midSidePanel
		JTextField textField = new JTextField();
		textField.setPreferredSize(new Dimension(400, 25));
		textField.setSize(new Dimension(400, 24));
		midSidePanel.add(textField);
		textField.setColumns(50);
		
		return headerBottom;
				
		
	}
	
	private JPanel vehicleContent() 
	{
		JPanel  vehicleBodyContentPanel = new JPanel();
		vehicleBodyContentPanel.setBackground(Color.WHITE);
		vehicleBodyContentPanel.setLayout(new BorderLayout(0, 0));
		vehicleBodyContentPanel.setPreferredSize(new Dimension(10, 90));
		
			//Vehicle Panel Align West 
			JPanel vehiclePanelWest = new JPanel();
			
			vehiclePanelWest.setMinimumSize(new Dimension(10, 128));
			vehiclePanelWest.setPreferredSize(new Dimension(400, 10));
			vehiclePanelWest.setBackground(SystemColor.GREEN);
			vehiclePanelWest.setLayout(new BorderLayout(0, 0));

	
			JPanel scrollPane = new JPanel();
			scrollPane.setBackground(Color.WHITE);
			scrollPane.setLayout(new MigLayout("", "[320px]", "[100px]"));
			
			JScrollPane scrollPaneContainer = new JScrollPane(scrollPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPaneContainer.setBorder(null);
			scrollPaneContainer.setBounds(new Rectangle(0, 0, 0, 20));
			scrollPaneContainer.setPreferredSize(new Dimension(0, 200));
			scrollPaneContainer.getVerticalScrollBar().setUnitIncrement(13);

			ArrayList<Vehicle> vehicles = VehicleService.findVehiclesByAll();

//			if(StringUtils.isNotBlank(textFieldSearchInput) && vehiclePageActive)
//			{
//				ArrayList<Vehicle> sortedVehicles = new ArrayList<Vehicle>();
//				for(Vehicle currentVehicle : vehicles )
//				{
//					if(currentVehicle.getModel().contains(textFieldSearchInput))
//					{
//						sortedVehicles.add(currentVehicle);	
//					}
//				}
//				vehicles = sortedVehicles;
//			}
			
			int i = 0;
			for (Vehicle currentVehicle : vehicles)
			{
				String position = "cell 0 "+ i + ",grow";
				scrollPane.add(createVehiclePanel(currentVehicle), position );
				i++;
			}
			
			vehiclePanelWest.add(scrollPaneContainer, BorderLayout.CENTER);
			
			//Placeholder for vehiclePanel
			JPanel placeholderWest = new JPanel();
			placeholderWest.setBackground(Color.WHITE);
			placeholderWest.setPreferredSize(new Dimension(30, 10));
			vehiclePanelWest.add(placeholderWest, BorderLayout.WEST);
			
			//Placeholder for vehiclePanel
			JPanel placeholderCenter = new JPanel();
			placeholderCenter.setBackground(Color.WHITE);
			placeholderCenter.setPreferredSize(new Dimension(10, 10));
			vehiclePanelWest.add(placeholderCenter, BorderLayout.EAST);
			
		vehicleBodyContentPanel.add(vehiclePanelWest, BorderLayout.WEST);
		
		
			//Placeholder for bodyContentPanel NORTH
			JPanel placeholderNorth = new JPanel();
			placeholderNorth.setBackground(Color.WHITE);
			placeholderNorth.setPreferredSize(new Dimension(100, 30));
			
		vehicleBodyContentPanel.add(placeholderNorth, BorderLayout.NORTH);
			
		
			//Placeholder for bodyContentPanel SOUTH
			JPanel placeholderSouth = new JPanel();
			placeholderSouth.setBackground(Color.WHITE);
			placeholderSouth.setPreferredSize(new Dimension(30, 30));
		
		vehicleBodyContentPanel.add(placeholderSouth, BorderLayout.SOUTH);
		
		
			// Center Panel Map
			JPanel vehiclePanelCenter = new JPanel();
			vehiclePanelCenter.setMinimumSize(new Dimension(10, 128));
			vehiclePanelCenter.setPreferredSize(new Dimension(400, 10));
			vehiclePanelCenter.setBackground(SystemColor.GREEN);
			vehiclePanelCenter.setLayout(new BorderLayout(0, 0));
			
			vehiclePanelCenter.add(new MapPanel());
			
			
			
			
			//Placeholder for vehiclePanelCenter
			JPanel placeholderEAST = new JPanel();
			placeholderEAST.setBackground(Color.WHITE);
			placeholderEAST.setPreferredSize(new Dimension(30, 10));
			vehiclePanelCenter.add(placeholderEAST, BorderLayout.EAST);
			
		vehicleBodyContentPanel.add(vehiclePanelCenter, BorderLayout.CENTER);
			
		return vehicleBodyContentPanel;
	} 
	
	private JPanel customerContent() 
	{
		JPanel customerBodyContentPanel = new JPanel();
		customerBodyContentPanel.setBackground(Color.WHITE);
		customerBodyContentPanel.setLayout(new BorderLayout(0, 0));
		customerBodyContentPanel.setPreferredSize(new Dimension(10, 90));

		JPanel flowPane = new JPanel();
		flowPane.setAlignmentY(Component.TOP_ALIGNMENT);
		flowPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		flowPane.setBackground(Color.WHITE);
		flowPane.setLayout(new ModifiedFlowLayout(0));
		
		JScrollPane scrollPaneContainer = new JScrollPane(flowPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneContainer.setBorder(null);
		scrollPaneContainer.setBounds(new Rectangle(0, 0, 0, 20));
		scrollPaneContainer.setPreferredSize(new Dimension(0, 200));
		scrollPaneContainer.getVerticalScrollBar().setUnitIncrement(13);
		
		ArrayList<Customer> customer = CustomerService.findCostumerByAll();
		for (Customer currentCustomer : customer)
		{

			flowPane.add(createCostumerPanel(currentCustomer));
		}
		
		customerBodyContentPanel.add(scrollPaneContainer, BorderLayout.CENTER);
		

		//Placeholder for customerBodyContentPanel WEST
		JPanel placeholderWest = new JPanel();
		placeholderWest.setBackground(Color.WHITE);
		placeholderWest.setPreferredSize(new Dimension(30, 10));
		
		customerBodyContentPanel.add(placeholderWest, BorderLayout.WEST);
		
		//Placeholder for customerBodyContentPanel EAST
		JPanel placeholderWEST = new JPanel();
		placeholderWEST.setBackground(Color.WHITE);
		placeholderWEST.setPreferredSize(new Dimension(30, 10));

		customerBodyContentPanel.add(placeholderWEST, BorderLayout.EAST);
	
		//Placeholder for customerBodyContentPanel NORTH
		JPanel placeholderNorth = new JPanel();
		placeholderNorth.setBackground(Color.WHITE);
		placeholderNorth.setPreferredSize(new Dimension(100, 30));
		
		customerBodyContentPanel.add(placeholderNorth, BorderLayout.NORTH);
		
		//Placeholder for customerBodyContentPanel SOUTH
		JPanel placeholderSouth = new JPanel();
		placeholderSouth.setBackground(Color.WHITE);
		placeholderSouth.setPreferredSize(new Dimension(30, 30));
	
		customerBodyContentPanel.add(placeholderSouth, BorderLayout.SOUTH);

		return customerBodyContentPanel;
	}
	
	private void addComboBoxToPanel(JPanel panel)
	{
		JComboBox<String> comboBox = new JComboBox<String>();
	
		comboBox.setForeground(Color.WHITE);
		comboBox.setBorder(null);
		comboBox.setBackground(primaryColor);
		comboBox.setPreferredSize(new Dimension(145, 25));
		comboBox.addItem("Filtern");
		comboBox.addItem("test");

		panel.add(comboBox);
		
	}
	
	private JPanel createCostumerPanel(Customer customer) {
		JPanel costumerConatinerPanel = new JPanel();
		costumerConatinerPanel.setPreferredSize(new Dimension(200, 275));
		
		costumerConatinerPanel.setLayout(new BorderLayout(0, 0));
		
			JPanel settingsPanel = new JPanel();
			settingsPanel.setPreferredSize(new Dimension(10, 28));
			settingsPanel.setLayout(new BorderLayout(0, 0));
			
				JButton settingsButton = new JButton("");
				settingsButton.setIcon(resizeImageIcon("menu.png", 20, 20));
				settingsButton.setMargin(new Insets(0, 0, 0, 0));
				settingsButton.setAlignmentY(Component.TOP_ALIGNMENT);
				settingsButton.setBorder(null);
				settingsButton.setPreferredSize(new Dimension(30, 30));
				settingsPanel.add(settingsButton, BorderLayout.EAST);
				
		costumerConatinerPanel.add(settingsPanel, BorderLayout.NORTH);
		
		//Costumer Info Bottom
		JPanel customerInfoBottomPanel = new JPanel();
		customerInfoBottomPanel.setPreferredSize(new Dimension(10, 145));
		customerInfoBottomPanel.setLayout(new BorderLayout(0, 0));
		
			//Costumer Name
			JPanel ccustomerNamePanel = new JPanel();
			ccustomerNamePanel.setPreferredSize(new Dimension(10, 25));
			
				JLabel ccustomerNameLabel = new JLabel(customer.getFirstname()+ " " + customer.getLastname());
				ccustomerNameLabel.setFont (ccustomerNameLabel.getFont ().deriveFont (14.0f));
				ccustomerNamePanel.add(ccustomerNameLabel);
				
			customerInfoBottomPanel.add(ccustomerNamePanel, BorderLayout.NORTH);
		
			//Info Panel	
			JPanel customerInfoPanel = new JPanel();
			customerInfoPanel.setAlignmentY(Component.TOP_ALIGNMENT);
			customerInfoPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
			customerInfoPanel.setLayout(null);
			
				JLabel customerNumberLabel = new JLabel("Kundennummer: " + customer.getUsername());
				customerNumberLabel.setBounds(10, 0, 180, 24);
				customerInfoPanel.add(customerNumberLabel);
				
				
				//CostumerNumber
				JLabel customerAdressLabel = new JLabel("Geburtstag: " + customer.getBirthday());
				customerAdressLabel.setBounds(10, 25, 180, 14);
				customerAdressLabel.setAlignmentY(Component.TOP_ALIGNMENT);
				customerInfoPanel.add(customerAdressLabel);
		
			customerInfoBottomPanel.add(customerInfoPanel, BorderLayout.CENTER);
			
			//Button Panel
			JPanel customerButtonPanel = new JPanel();
			customerButtonPanel.setPreferredSize(new Dimension(0, 75));
			
				JButton btnNewButton = new JButton("Fahrzeug zuweisen");
				btnNewButton.setPreferredSize(new Dimension(180, 30));
				btnNewButton.setBackground(primaryColor);
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setBorder(null);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				customerButtonPanel.add(btnNewButton);
				
				JButton reserveVehicleButton = new JButton("Fahrzeug reservieren");
				reserveVehicleButton.setPreferredSize(new Dimension(180, 30));
				reserveVehicleButton.setBackground(primaryColor);
				reserveVehicleButton.setForeground(Color.WHITE);
				reserveVehicleButton.setBorder(null);
				
				customerButtonPanel.add(reserveVehicleButton);
		
			customerInfoBottomPanel.add(customerButtonPanel, BorderLayout.SOUTH);
				
		costumerConatinerPanel.add(customerInfoBottomPanel, BorderLayout.SOUTH);
		
		//Profile Image Panel
		JPanel profileImagePanel = new JPanel();
		profileImagePanel.setForeground(Color.WHITE);
		profileImagePanel.setBackground(Color.WHITE);
		costumerConatinerPanel.add(profileImagePanel, BorderLayout.CENTER);
		
		//Plac Holder WEST
		JPanel placeholderWEST = new JPanel();
		placeholderWEST.setPreferredSize(new Dimension(50, 10));
		costumerConatinerPanel.add(placeholderWEST, BorderLayout.WEST);
		
		//Plac Holder EAST
		JPanel placeholderEAST = new JPanel();
		placeholderEAST.setPreferredSize(new Dimension(50, 10));
		costumerConatinerPanel.add(placeholderEAST, BorderLayout.EAST);
		
	
		
		return costumerConatinerPanel;
	}
	
	private JPanel createVehiclePanel(Vehicle currentVehicle) 
	{
		JPanel vehiclePanel = new JPanel();
		vehiclePanel.setLayout(new BorderLayout(0, 0));
		
		JPanel innerVehiclePanel = new JPanel();
		innerVehiclePanel.setBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)));
		innerVehiclePanel.setPreferredSize(new Dimension(10, 50));
		innerVehiclePanel.setLayout(null);
			
		JLabel vehicleDetails = new JLabel(currentVehicle.getBrand() + " " + currentVehicle.getModel());
		vehicleDetails.setBounds(59, 11, 200, 14);
		innerVehiclePanel.add(vehicleDetails);
	
		JLabel vehicleLocation = new JLabel("Beinberger Weg 11, 75394 Oberreichenbach");
		vehicleLocation.setFont(new Font("Tahoma", Font.PLAIN, 9));
		vehicleLocation.setBounds(59, 25, 261, 14);
		innerVehiclePanel.add(vehicleLocation);
	
		JButton moreDetailsBtn = new JButton("");
		moreDetailsBtn.setIcon(resizeImageIcon("standort.png", 15, 15));
		moreDetailsBtn.setBorder(null);
		moreDetailsBtn.setPreferredSize(new Dimension(10, 10));
		moreDetailsBtn.setBounds(294, 11, 26, 23);
		innerVehiclePanel.add(moreDetailsBtn);
		
		JButton reservationBtn = new JButton("");
		reservationBtn.setIcon(resizeImageIcon("menu.png", 15, 15));
		reservationBtn.setBorder(null);
		reservationBtn.setPreferredSize(new Dimension(10, 10));
		reservationBtn.setBounds(269, 11, 26, 23);
		innerVehiclePanel.add(reservationBtn);
				
		vehiclePanel.add(innerVehiclePanel, BorderLayout.NORTH);

		JPanel carDetails = new JPanel();
		carDetails.setLayout(null);
		
		JLabel lblReserved = new JLabel("Keine Buchung");
		lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblReserved.setBounds(25, 25, 103, 14);
		carDetails.add(lblReserved);
		
		JLabel lblLocation = new JLabel("Aktueller Standort: " + currentVehicle.getLatitude() + " " + currentVehicle.getLongitude());
		lblLocation.setBounds(25, 11, 48, 14);
		carDetails.add(lblLocation);
			
//			JLabel lblReserved = new JLabel("Reserviert von: " + currentVehicle.getReservedFrom() + " bis: " + currentVehicle.getReservedTo());
//			lblReserved.setFont(new Font("Tahoma", Font.PLAIN, 9));
//			lblReserved.setBounds(25, 25, 103, 14);
//			carDetails.add(lblReserved);
//			
//			JLabel lblLocation = new JLabel("Aktueller Standort: ");
//			lblLocation.setBounds(25, 11, 48, 14);
//			carDetails.add(lblLocation);
				
		vehiclePanel.add(carDetails, BorderLayout.CENTER);
			
		return vehiclePanel;
	}
	
	private ImageIcon resizeImageIcon(String url, int heigth, int width) 
	{
		ImageIcon imageIcon = new ImageIcon(MainController.class.getResource(DriveMeConstants.Picture.PATH + url));
		
		Image image = imageIcon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(heigth, width,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		return imageIcon = new ImageIcon(newimg);
	}
}
