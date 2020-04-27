package libraryWindowGUI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LibraryLogin {

	private JFrame frame;
	Connection connection = null;
	private JTextField textFieldUN;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		//		 try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "novax@123"))
		//		 {
		//	            System.out.println("Connected to PostgreSQL database!");
		//	            Statement statement = connection.createStatement();
		//	            System.out.println("Reading Book records...");
		//	            System.out.printf("%-30.30s  %-30.30s %-30.30s%n", "Title", "Author", "ISBN");
		//	            ResultSet resultSet = statement.executeQuery("select * from librarybooks;");
		//	            while (resultSet.next()) {
		//	                System.out.printf("%-30.30s  %-30.30s %-30.30s%n", resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("isbn"));
		//	            }
		//	            connection.close();
		//	            System.out.println("Closing connection!!");
		//	     }
		//		 catch (SQLException e)
		//		 {
		//	            System.out.println("Connection failure.");
		//	            e.printStackTrace();
		//	     }

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LibraryLogin window = new LibraryLogin();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application & connection to database.
	 */
	public LibraryLogin() {
		initialize();
		connection = PostgresqlConnection.dbConnection();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(848, 513);

		JLabel lblNewLabel = new JLabel("UserName");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel.setBounds(459, 171, 92, 16);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblNewLabel_1.setBounds(459, 241, 78, 16);
		frame.getContentPane().add(lblNewLabel_1);

		textFieldUN = new JTextField();
		textFieldUN.setBounds(584, 166, 130, 26);
		frame.getContentPane().add(textFieldUN);
		textFieldUN.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(584, 236, 130, 26);
		frame.getContentPane().add(passwordField);

		JButton btnLogin = new JButton("Login");
		Image imgok = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		btnLogin.setIcon(new ImageIcon(imgok));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from userdetails where usernames=? and passwords=?";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, textFieldUN.getText());
					pst.setString(2, new String(passwordField.getPassword()));
					ResultSet res = pst.executeQuery();
					int count = 0;
					while (res.next()) {
						count++;
					}
					if (count == 1) {
						//JOptionPane.showMessageDialog(null, "username and password are correct");
						char[] password = passwordField.getPassword();
						char[] correctPass = new char[] {'a', 'd', 'm', 'i', 'n'};
						if (textFieldUN.getText().equalsIgnoreCase("admin") && Arrays.equals(password, correctPass)) {
							frame.dispose();
							AdminLibrary adm = new AdminLibrary();
							adm.welcomemsg(textFieldUN.getText());
							adm.setVisible(true);
							JLabel displayname = new JLabel("Welcome: "+textFieldUN.getText());
							adm.getContentPane().add(displayname);
							//JOptionPane.showMessageDialog(null, "Am Admin ");
						} else {
							frame.dispose();
							UserLibrary usl = new UserLibrary();
							usl.welcomemsg(textFieldUN.getText());
							usl.setVisible(true);
							//JOptionPane.showMessageDialog(null, "Am User ");
						}
					}
					else if(textFieldUN.getText().length() == 0 && passwordField.getPassword().length == 0) {
						JOptionPane.showMessageDialog(null, "Please enter username and password !! ");
					}
					else {
						JOptionPane.showMessageDialog(null, "username or password is not correct");
					}
					res.close();
					pst.close();
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, e2);
				}
			}
		});
		btnLogin.setBounds(536, 298, 122, 36);
		frame.getContentPane().add(btnLogin);

		JLabel lblNewLabel_3 = new JLabel("");
		Image imglibr = new ImageIcon(this.getClass().getResource("/libr.png")).getImage();
		lblNewLabel_3.setIcon(new ImageIcon(imglibr));
		lblNewLabel_3.setBounds(6, 117, 416, 349);
		frame.getContentPane().add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setBounds(503, 310, 61, 16);
		frame.getContentPane().add(lblNewLabel_4);

		Panel panel = new Panel();
		panel.setBackground(UIManager.getColor("Button.disabledText"));
		panel.setBounds(13, 5, 821, 63);
		frame.getContentPane().add(panel);

		JLabel lblNewLabel_2 = new JLabel("TFS Library");
		lblNewLabel_2.setForeground(UIManager.getColor("Button.highlight"));
		panel.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Ayuthaya", Font.PLAIN, 40));
		lblNewLabel_2.setToolTipText("");
	}

	public void setVisibleLogin(boolean b) {
		// TODO Auto-generated method stub
		frame.setVisible(b);
	}
}
