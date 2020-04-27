package libraryWindowGUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class FrameAddBooks extends JFrame {

	private JPanel contentPane;
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
					FrameAddBooks frame = new FrameAddBooks();
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
	public FrameAddBooks() {
		connection = PostgresqlConnection.dbConnection();
		setTitle("Add Book");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		titleTF = new JTextField();
		titleTF.setColumns(10);
		titleTF.setBounds(191, 35, 200, 26);
		contentPane.add(titleTF);

		JLabel lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(69, 40, 88, 16);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Author");
		lblNewLabel_1.setBounds(69, 86, 82, 16);
		contentPane.add(lblNewLabel_1);

		authorTF = new JTextField();
		authorTF.setColumns(10);
		authorTF.setBounds(191, 81, 200, 26);
		contentPane.add(authorTF);

		JLabel lblNewLabel_1_1 = new JLabel("ISBN");
		lblNewLabel_1_1.setBounds(69, 138, 88, 16);
		contentPane.add(lblNewLabel_1_1);

		isbnTF = new JTextField();
		isbnTF.setColumns(10);
		isbnTF.setBounds(191, 133, 200, 26);
		contentPane.add(isbnTF);

		JButton submitBtn = new JButton("Submit");
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into librarybooks (title,author,isbn) VALUES (?,?,?)";
					PreparedStatement pst = connection.prepareStatement(query);
					pst.setString(1, titleTF.getText());
					pst.setString(2, authorTF.getText());

					String dstr= isbnTF.getText();
					if (dstr.matches("-?(0|[1-9]\\d*)")) {
						pst.setLong(3, Integer.parseInt(isbnTF.getText()));
						pst.executeUpdate();
						setVisible(false);
						// TODO ImageIcon is not working
						ImageIcon dab = new ImageIcon("/Library/images/books.png");
						JOptionPane.showMessageDialog(null, "Sucessfully added.",
								"Book in store", JOptionPane.INFORMATION_MESSAGE, dab);
					}
					else if(isbnTF.getText().length()!=0) {
						JOptionPane.showMessageDialog(null, "ISBN :- Only numbers are allowed");
					}
					else if (titleTF.getText().length() == 0 || authorTF.getText().length() == 0 || isbnTF.getText().length()==0 ) {
						JOptionPane.showMessageDialog(null, "Please enter values in respective fields !! ");
					}
					//	DefaultTableModel model = (DefaultTableModel) librarybooks.getModel();
					//	model.addRow(new Object[]{titleTF.getText(),authorTF.getText(),Integer.parseInt(isbnTF.getText())});
					//	table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (SQLException ex) {
					//DBUtilities.processException(ex);
				}
			}
		});
		submitBtn.setBounds(131, 188, 117, 29);
		contentPane.add(submitBtn);
	}
}
