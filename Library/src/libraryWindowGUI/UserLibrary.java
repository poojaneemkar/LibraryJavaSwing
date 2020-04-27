package libraryWindowGUI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

public class UserLibrary extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Connection connection = null;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	private JLabel welcomeuser;
	private JLabel openbooks;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UserLibrary frame = new UserLibrary();
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
	public UserLibrary() {
		connection = PostgresqlConnection.dbConnection();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(848, 513);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		openbooks = new JLabel("");
		Image imglibr = new ImageIcon(this.getClass().getResource("/books.png")).getImage();
		openbooks.setIcon(new ImageIcon(imglibr));
		openbooks.setBounds(349, 363, 141, 111);
		contentPane.add(openbooks);

		panel = new JPanel();
		panel.setBackground(Color.GRAY);
		panel.setBounds(0, 462, 848, 29);
		contentPane.add(panel);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(40, 138, 757, 187);
		scrollPane.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnNewButton_1 = new JButton("View Books");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "select * from librarybooks";
					PreparedStatement pst = connection.prepareStatement(query);
					ResultSet rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(358, 39, 117, 29);
		contentPane.add(btnNewButton_1);

		btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				LibraryLogin ll = new LibraryLogin();
				ll.setVisibleLogin(true);
			}
		});
		btnNewButton.setBounds(6, 6, 117, 29);
		contentPane.add(btnNewButton);


	}

	public void welcomemsg(String text) {
		// TODO Auto-generated method stub
		welcomeuser = new JLabel("New label");
		welcomeuser.setBounds(674, 11, 149, 24);
		welcomeuser.setText("Welcome " + text);
		contentPane.add(welcomeuser);
	}
}
