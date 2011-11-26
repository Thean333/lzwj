package by.dev.madhead.lzwj;

import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import by.dev.madhead.lzwj.compress.LZW;
import by.dev.madhead.lzwj.gui.MainFrame;

/**
 * Main class of the application.
 * 
 * @author madhead
 * 
 */
public class Main {
	/**
	 * Application entry point.
	 * 
	 * @param args
	 *            arguments for the application.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		try {
			if (args.length < 1) {
				System.err.println("You must specify at least one option!");
				System.exit(0);
			}
			if ("g".equals(args[0])) {
				EventQueue.invokeLater(new Runnable() {

					@Override
					public void run() {
						MainFrame mf = new MainFrame();

						mf.setVisible(true);
					}
				});
			} else {
				if (args.length < 3) {
					System.err
							.println("Invalid number of command line options!");
					System.exit(0);
				}
				if ("c".equals(args[0])) {
					FileInputStream in = new FileInputStream(args[1]);
					FileOutputStream out = new FileOutputStream(args[2]);
					LZW lzw = new LZW();

					lzw.compress(in, out);
				} else if ("d".equals(args[0])) {
					FileInputStream in = new FileInputStream(args[1]);
					FileOutputStream out = new FileOutputStream(args[2]);
					LZW lzw = new LZW();

					lzw.decompress(in, out);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
