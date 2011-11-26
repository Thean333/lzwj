package by.dev.madhead.lzwj.gui;

import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JFrame;

import by.dev.madhead.lzwj.gui.actions.CompressAction;
import by.dev.madhead.lzwj.gui.actions.DecompressAction;

/**
 * Main window for GUI mode.
 * 
 * @author madhead
 * 
 */
public class MainFrame extends JFrame {
	private static final int DEFAULT_WIDTH = 500;
	private static final int DEFAULT_HEIGHT = 100;
	private static final String DEFAULT_CAPTION = "LZW file compressor";

	private JButton btnCompress;
	private JButton btnDecompress;
	private Action compressAction;
	private Action decompressAction;

	/**
	 * Default no-arg constructor.
	 */
	public MainFrame() {
		compressAction = new CompressAction();
		decompressAction = new DecompressAction();
		btnCompress = new JButton(compressAction);
		btnDecompress = new JButton(decompressAction);

		this.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
		centerize();
		this.setResizable(false);
		this.setTitle(DEFAULT_CAPTION);
		this.setLayout(new FlowLayout());
		this.add(btnCompress);
		this.add(btnDecompress);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Utility method for centerizing frame on screen.
	 */
	private void centerize() {
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		int screenWidth = toolkit.getScreenSize().width;
		int screenHeight = toolkit.getScreenSize().height;

		setLocation((screenWidth - getWidth()) / 2,
				(screenHeight - getHeight()) / 2);
	}
}
