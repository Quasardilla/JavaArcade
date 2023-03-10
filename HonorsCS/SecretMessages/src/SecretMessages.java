import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class SecretMessages {
	public static void main(String[] args) throws Exception {
		ImageIcon icon = new ImageIcon(SecretMessages.class.getResource("padlock.png"));
		boolean again = true;
		Clipboard c = Toolkit.getDefaultToolkit().getSystemClipboard();
		
		JOptionPane.showMessageDialog(null, "Welcome to the Cxesar Sypher!", "Message Encoder", JOptionPane.QUESTION_MESSAGE, icon);
		
		while(again == true) {
			
			String[] choices = { "Encrypt a Message", "Decrypt a Message" };
			String choice = (String) JOptionPane.showInputDialog(null,"Encrypt or Decrypt?","Main Menu",JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			if(choice == choices[0]) {
				JOptionPane.showMessageDialog(null, encrypt(JOptionPane.showInputDialog(null,"What message do you want to encrypt?", c.getData(DataFlavor.stringFlavor))));
			} else JOptionPane.showMessageDialog(null, decrypt(JOptionPane.showInputDialog(null,"What message do you want to decrypt?", c.getData(DataFlavor.stringFlavor))));
			JOptionPane.showMessageDialog(null, "Message copied to clipboard. Use ⌘ + V to paste your message.");
			
			int againint = JOptionPane.showConfirmDialog(null, "Do you want to run again?", "Rerunner", JOptionPane.YES_NO_OPTION);
			if (againint == JOptionPane.YES_OPTION) {
			    again = true;
			} else {
			    again = false;
			    System.exit(0);
			}
		}
		
		
	}
	
	public static String encrypt(String in) {
		String sshift = JOptionPane.showInputDialog(null, "Insert key");
		int shift = Integer.valueOf(sshift);
		
		String message = "";
		char ch;
		
		for(int i = 0; i < in.length(); i++) {
			ch = (char) ((int) in.charAt(i) + shift);
					//* Integer.valueOf(sshift.charAt(1)));
			message+= ch;
		}
		
		copy(message, null);
		return message;
	}
	
	public static String decrypt(String in) {
		int shift = Integer.valueOf(JOptionPane.showInputDialog(null, "Insert key"));
		String message = "";
		char ch;
		
		for(int i = 0; i< in.length(); i++) {
			ch = (char) ((int) in.charAt(i) - shift);
			message += ch;
		}
		
		copy(message, null);
		return message;
	}
	
	public static void copy(String s, ClipboardOwner owner) {

	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    Transferable transferable = new StringSelection(s);
	    clipboard.setContents(transferable, owner);
	    
	}
}
