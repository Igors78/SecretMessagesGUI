import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JSlider;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class SecretMessagesGUI extends JFrame {
	private JTextField txtKey;
	private JTextArea txtIn;
	private JTextArea txtOut;
	private JSlider slider;

	public String encode(String message, int keyVal) {
		String output = "";
		char key = (char) keyVal;
		for (int x = 0; x < message.length(); x++) {
			char input = message.charAt(x);
			if (input >= 'A' && input <= 'Z') {
				input += key;
				if (input > 'Z')
					input -= 26;
				if (input < 'A')
					input += 26;
			} else if (input >= 'a' && input <= 'z') {
				input += key;
				if (input > 'z')
					input -= 26;
				if (input < 'a')
					input += 26;
			} else if (input >= '0' && input <= '9') {
				input += (keyVal % 10);
				if (input > '9')
					input -= 10;
				if (input < '0')
					input += 10;
			}
			output += input;
		}
		return output;
	}

	public SecretMessagesGUI() {
		getContentPane().setBackground(new Color(176, 196, 222));
		setTitle("Secret Message App");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		txtIn = new JTextArea();
		txtIn.setWrapStyleWord(true);
		txtIn.setLineWrap(true);
		txtIn.setBackground(new Color(255, 255, 255));
		txtIn.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtIn.setBounds(12, 12, 625, 132);
		getContentPane().add(txtIn);

		txtOut = new JTextArea();
		txtOut.setWrapStyleWord(true);
		txtOut.setLineWrap(true);
		txtOut.setBackground(new Color(255, 255, 255));
		txtOut.setFont(new Font("Dialog", Font.PLAIN, 18));
		txtOut.setBounds(12, 233, 625, 132);
		getContentPane().add(txtOut);

		txtKey = new JTextField();
		txtKey.setFont(new Font("Dialog", Font.BOLD, 12));
		txtKey.setHorizontalAlignment(SwingConstants.CENTER);
		txtKey.setText("7");
		txtKey.setBounds(350, 175, 60, 19);
		getContentPane().add(txtKey);
		txtKey.setColumns(10);

		JLabel lblKey = new JLabel("Key:");
		lblKey.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKey.setBounds(262, 177, 70, 15);
		getContentPane().add(lblKey);

		JButton btnEncodedecode = new JButton("Encode/Decode");
		btnEncodedecode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String message = txtIn.getText();
					int key = Integer.parseInt(txtKey.getText());
					String output = encode(message, key);
					txtOut.setText(output);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Please enter a whole number value for the encryption key.");
					txtKey.requestFocus();
					txtKey.selectAll();
				}
			}
		});
		btnEncodedecode.setBounds(422, 172, 215, 25);
		getContentPane().add(btnEncodedecode);
		
		slider = new JSlider();
		slider.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				txtKey.setText("" + slider.getValue());
				String message = txtIn.getText();
				int key = slider.getValue();
				String output = encode(message, key);
				txtOut.setText(output);
			}
		});
		slider.setValue(7);
		slider.setPaintTicks(true);
		slider.setMajorTickSpacing(13);
		slider.setMinorTickSpacing(1);
		slider.setMinimum(-26);
		slider.setMaximum(26);
		slider.setPaintLabels(true);
		slider.setBackground(new Color(176, 196, 222));
		slider.setBounds(23, 171, 255, 53);
		getContentPane().add(slider);
	}

	public static void main(String[] args) {
		SecretMessagesGUI theApp = new SecretMessagesGUI();
		theApp.setSize(new java.awt.Dimension(650, 400));
		theApp.setVisible(true);

	}
}
