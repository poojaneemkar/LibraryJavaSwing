package libraryWindowGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class AddBooks extends JInternalFrame {
	private JTextField titleTF;
	private JTextField authorTF;
	private JTextField isbnTF;
	Connection connection = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AddBooks frame = new AddBooks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddBooks() {
		setIconifiable(true);
		setMaximizable(true);
		setResizable(true);
		setClosable(true);
		connection = PostgresqlConnection.dbConnection();
		setTitle("Add Book");
		setBounds(100, 100, 477, 338);
		getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(83, 38, 88, 16);
		getContentPane().add(lblNewLabel);

		titleTF = new JTextField();
		titleTF.setBounds(205, 33, 200, 26);
		getContentPane().add(titleTF);
		titleTF.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Author");
		lblNewLabel_1.setBounds(83, 84, 82, 16);
		getContentPane().add(lblNewLabel_1);

		authorTF = new JTextField();
		authorTF.setBounds(205, 79, 200, 26);
		getContentPane().add(authorTF);
		authorTF.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("ISBN");
		lblNewLabel_1_1.setBounds(83, 136, 88, 16);
		getContentPane().add(lblNewLabel_1_1);

		isbnTF = new JTextField();
		isbnTF.setColumns(10);
		isbnTF.setBounds(205, 131, 200, 26);
		getContentPane().add(isbnTF);

		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into librarybooks (title,author,isbn) VALUES (?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, titleTF.getText());
					pst.setString(2, authorTF.getText());
					pst.setLong(3, Integer.parseInt(isbnTF.getText()));
					pst.executeUpdate();
					System.out.print("Record is inserted");
					//					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (SQLException ex) {
					//					DBUtilities.processException(ex);
				}
			}
		});
		submitBtn.setBounds(145, 186, 117, 29);
		getContentPane().add(submitBtn);

	}
}
