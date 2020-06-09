package driveMe.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import driveMe.MainRenderer;
import driveMe.ModifiedFlowLayout;
import driveMe.constants.DriveMeConstants;
import driveMe.customers.model.Customer;
import driveMe.customers.service.CustomerService;
import driveMe.vehicles.model.Vehicle;
import driveMe.vehicles.service.VehicleService;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class CustomerRenderer extends MainRenderer{

	
	JPanel flowPane;
	private CustomerService customerService= new CustomerService();
	ArrayList<Customer> allCustomer = CustomerService.findAllCustomers();
//	private DriveMeUtil driveMeUtil = new DriveMeUtil();
	
	public CustomerRenderer() {
//		driveMeUtil = new DriveMeUtil();
	}
	
	public JPanel getCustomerContent(ArrayList<Customer> customers) 
	{
		JPanel customerBodyContentPanel = new JPanel();
		customerBodyContentPanel.setBackground(Color.WHITE);
		customerBodyContentPanel.setLayout(new BorderLayout(0, 0));
		customerBodyContentPanel.setPreferredSize(new Dimension(10, 90));

		flowPane = new JPanel();
		flowPane.setAlignmentY(Component.TOP_ALIGNMENT);
		flowPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		flowPane.setBackground(Color.WHITE);
		flowPane.setLayout(new ModifiedFlowLayout(0));
		
		JScrollPane scrollPaneContainer = new JScrollPane(flowPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneContainer.setBorder(null);
		scrollPaneContainer.setBounds(new Rectangle(0, 0, 0, 20));
		scrollPaneContainer.setPreferredSize(new Dimension(0, 200));
		scrollPaneContainer.getVerticalScrollBar().setUnitIncrement(13);
		
		for (Customer currentCustomer : customers)
		{
			flowPane.add(createCustomerPanel(currentCustomer));
		}
		
		customerBodyContentPanel.add(scrollPaneContainer, BorderLayout.CENTER);

		customerBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(30, 10)), BorderLayout.WEST);

		customerBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(30, 10)), BorderLayout.EAST);
		
		customerBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(100, 30)), BorderLayout.NORTH);
		
		customerBodyContentPanel.add(driveMeUtil.createPlaceholderPanel(new Dimension(30, 30)), BorderLayout.SOUTH);

		return customerBodyContentPanel;
	}
	
	private JPanel createCustomerPanel(Customer customer) {
		JPanel customerConatinerPanel = new JPanel();
		customerConatinerPanel.setPreferredSize(new Dimension(200, 275));
		
		customerConatinerPanel.setLayout(new BorderLayout(0, 0));
		
			JPanel settingsPanel = new JPanel();
			settingsPanel.setPreferredSize(new Dimension(10, 28));
			settingsPanel.setLayout(new BorderLayout(0, 0));
			
				JButton settingsButton = new JButton("");
				settingsButton.setIcon(driveMeUtil.resizeImageIcon("menu.png", 20, 20));
				settingsButton.setMargin(new Insets(0, 0, 0, 0));
				settingsButton.setAlignmentY(Component.TOP_ALIGNMENT);
				settingsButton.setBorder(null);
				settingsButton.setPreferredSize(new Dimension(30, 30));
				settingsPanel.add(settingsButton, BorderLayout.EAST);
				
		customerConatinerPanel.add(settingsPanel, BorderLayout.NORTH);
		
		//Customer Info Bottom
		JPanel customerInfoBottomPanel = new JPanel();
		customerInfoBottomPanel.setPreferredSize(new Dimension(10, 145));
		customerInfoBottomPanel.setLayout(new BorderLayout(0, 0));
		
			//Customer Name
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
				
				
				//CustomerNumber
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
				btnNewButton.setBackground(DriveMeConstants.Colour.primaryColor);
				btnNewButton.setForeground(Color.WHITE);
				btnNewButton.setBorder(null);
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(mainFrame, new JLabel("Zurzeit sind Keine Fahrzeuge Verfügbar"));
					}
				});
				customerButtonPanel.add(btnNewButton);
				
				JButton reserveVehicleButton = new JButton("Fahrzeug reservieren");
				reserveVehicleButton.setPreferredSize(new Dimension(180, 30));
				reserveVehicleButton.setBackground(DriveMeConstants.Colour.primaryColor);
				reserveVehicleButton.setForeground(Color.WHITE);
				reserveVehicleButton.setBorder(null);
				reserveVehicleButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(mainFrame, new JLabel("Zurzeit sind Keine Fahrzeuge Verfügbar"));
					}
				});
				customerButtonPanel.add(reserveVehicleButton);
		
			customerInfoBottomPanel.add(customerButtonPanel, BorderLayout.SOUTH);
				
		customerConatinerPanel.add(customerInfoBottomPanel, BorderLayout.SOUTH);
		
		//Profile Image Panel
		JPanel profileImagePanel = new JPanel();
			JLabel userImage = new JLabel("");
			userImage.setIcon(driveMeUtil.resizeImageIcon("userimage.png", 80, 80));
			userImage.setBounds(5,5,50,40);
		profileImagePanel.add(userImage);
//		profileImagePanel.setForeground(Color.);
//		profileImagePanel.setBackground(Color.WHITE);
		customerConatinerPanel.add(profileImagePanel, BorderLayout.CENTER);
		
		//Plac Holder WEST
		JPanel placeholderWEST = new JPanel();
		placeholderWEST.setPreferredSize(new Dimension(50, 10));
		customerConatinerPanel.add(placeholderWEST, BorderLayout.WEST);
		
		//Plac Holder EAST
		JPanel placeholderEAST = new JPanel();
		placeholderEAST.setPreferredSize(new Dimension(50, 10));
		customerConatinerPanel.add(placeholderEAST, BorderLayout.EAST);
		
		return customerConatinerPanel;
	}
	
	public JPanel customerHeaderBottom(JFrame mainFrame, boolean vehiclePageActive) 
	{
		JPanel headerBottom = new JPanel();
		headerBottom.setPreferredSize(new Dimension(10, 20));
		headerBottom.setMinimumSize(new Dimension(10, 27));
		headerBottom.setBackground(DriveMeConstants.Colour.secondColor);
		headerBottom.setLayout(new BorderLayout(0, 0));
		
		//Set leftSidePanel to header bottom
		JPanel leftSidePanel = new JPanel();
		leftSidePanel.setPreferredSize(new Dimension(200, 10));
		
		//Add compobox to rightSidePanel
		//driveMeUtil.addComboBoxToPanel(leftSidePanel);
		JPanel placeholder = new JPanel();
		placeholder.setPreferredSize(new Dimension(145, 25));
		leftSidePanel.add(placeholder);
		
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
		btnFahrzeugAnlegen.setBackground(DriveMeConstants.Colour.primaryColor);
		btnFahrzeugAnlegen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				applyNewCustomer(mainFrame);
				
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
		textField.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyPressed(KeyEvent e) {
	        	
	            	String input = textField.getText();
	            	if(input.length() > 0)
	            	{
	            		if(vehiclePageActive)
		            	{
	            			searchCustomer(input);
		            	}
	            	} else {
	            		
	            		refreshCustomersPanel(allCustomer);
	            	}
	       
	            }
	    });
		
		return headerBottom;
	}
	
	
	private void searchCustomer(String searchInput)
	{
		ArrayList<Customer> filteredCustomers = null;
		ArrayList<Customer> allCustomers = CustomerService.findAllCustomers();
		
		if(allCustomers != null && StringUtils.isNotBlank(searchInput))
		{
			filteredCustomers = new ArrayList<>();
			for(Customer currentCustomer : allCustomers)
			{
				if(currentCustomer.getUsername().toLowerCase().contains(searchInput.toLowerCase()))
				{
					filteredCustomers.add(currentCustomer);					
				}
			}
		}

		refreshCustomersPanel(filteredCustomers);
	}
	
	public void refreshCustomersPanel(ArrayList<Customer> filteredCustomers)
	{
		flowPane.removeAll();
		for (Customer currentCustomer : filteredCustomers)
		{
			flowPane.add(createCustomerPanel(currentCustomer));
		}

		flowPane.repaint();		

	}
	private void applyNewCustomer(JFrame mainFrame) {
		JPanel panel = setVehicleFormToPanel();
		
		int exit = JOptionPane.showConfirmDialog(mainFrame, panel, "Benutzer anlegen", JOptionPane.DEFAULT_OPTION);

		if (exit == JOptionPane.YES_OPTION){
			Component[] filledComponents = panel.getComponents();
			if(ArrayUtils.isNotEmpty(filledComponents))
			{
				Customer newCustomer = new Customer();
				for(Component currentComponent : filledComponents) {
					
					if(DriveMeConstants.Database.Customer.Firstname.equals(currentComponent.getName())){
						String fistnameValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						String fistname = "Firstname";
						if(fistnameValue.length() > 0)
						{
							fistname = fistnameValue;
						}

						newCustomer.setFirstname(fistname);
					}
					else if(DriveMeConstants.Database.Customer.Lastname.equals(currentComponent.getName())){
						String lastnameValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						String lastname = "Lastname";
						if(lastnameValue.length() > 0)
						{
							lastname = lastnameValue;
						}

						newCustomer.setLastname(lastname);								
					}
					else if(DriveMeConstants.Database.Customer.Username.equals(currentComponent.getName())){
						String usernameValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						String username = "Username";
						if(usernameValue.length() > 0)
						{
							username = usernameValue;
						}

						newCustomer.setUsername(username);								
					}
					else if(DriveMeConstants.Database.Customer.Birthday.equals(currentComponent.getName())){
						String bdayValue = driveMeUtil.getStringFromSubComponentTextField(currentComponent);
						Date bday = new Date();
						if(bdayValue.length() > 0)
						{
							try {
								bday = new SimpleDateFormat("dd.MM.yyyy").parse(bdayValue);
							} catch (ParseException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

						newCustomer.setBirthday(bday);								
					}

				}
				boolean vehicleSaved = customerService.saveCustomer(newCustomer);
				if(vehicleSaved) {
					System.out.println("Jucheii!");
					//TODO
					ArrayList<Customer> allCustomer = CustomerService.findAllCustomers();
					refreshCustomersPanel(allCustomer);
				}
				else {
					System.out.println("Something went wrong while saving the customer!");
				}
			} 
		}
		
	}

	

	private JPanel setVehicleFormToPanel() {
		JPanel panel = new JPanel();
		panel.setLayout(new GridLayout(0, 1));

		//Firstname input
		JPanel firstNameInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		firstNameInput.add(new JLabel("Vorname:"));
		firstNameInput.add(new JTextField(10));
		firstNameInput.setName(DriveMeConstants.Database.Customer.Firstname);
		panel.add(firstNameInput);
		
		//Firstname input
		JPanel lastnameNameInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		lastnameNameInput.add(new JLabel("Nachname:"));
		lastnameNameInput.add(new JTextField(10));
		lastnameNameInput.setName(DriveMeConstants.Database.Customer.Lastname);
		panel.add(lastnameNameInput);
		
		//username input
		JPanel userNameInput = new JPanel(new FlowLayout(FlowLayout.LEFT));
		userNameInput.add(new JLabel("Username:"));
		userNameInput.add(new JTextField(10));
		userNameInput.setName(DriveMeConstants.Database.Customer.Username);
		panel.add(userNameInput);
		
		//brithday input
		UtilDateModel model = new UtilDateModel();
		JDatePanelImpl datePanel = new JDatePanelImpl(model);
		JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
		datePicker.setName(DriveMeConstants.Database.Customer.Birthday);
		datePicker.setLayout(new FlowLayout(FlowLayout.LEFT));
		panel.add(datePicker);
		

		return panel;
	}
	
}
