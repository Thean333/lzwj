package by.dev.madhead.lzwj.gui.actions;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import by.dev.madhead.lzwj.compress.LZW;
import by.dev.madhead.lzwj.util.Constants;

/**
 * Action for performing compression from GUI.
 * 
 * @author madhead
 * 
 */
public class CompressAction extends AbstractAction {
	private static final long serialVersionUID = -8518869396858723952L;

	/**
	 * Default no-arg constructor.
	 */
	public CompressAction() {
		putValue(Action.NAME, "Compress");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fileChooser = new JFileChooser();

		fileChooser.setMultiSelectionEnabled(false);
		if (JFileChooser.APPROVE_OPTION == fileChooser.showOpenDialog(null)) {
			final String fileName = fileChooser.getSelectedFile()
					.getAbsolutePath();
			final String destFileName = fileName
					+ Constants.COMPRESSED_FILE_SUFFIX;
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						FileInputStream in = new FileInputStream(fileName);
						FileOutputStream out = new FileOutputStream(
								destFileName);
						LZW lzw = new LZW();

						lzw.compress(in, out);

						in.close();
						out.close();
						JOptionPane.showMessageDialog(null,
								"Compression succesfully finished!",
								"Compress", JOptionPane.INFORMATION_MESSAGE);
					} catch (FileNotFoundException fileNotFoundException) {
						JOptionPane.showMessageDialog(null, "File not found!",
								"Compress", JOptionPane.ERROR_MESSAGE);
					} catch (IOException ioException) {
						JOptionPane.showMessageDialog(null,
								"IO Exception while compressing", "Compress",
								JOptionPane.ERROR_MESSAGE);
					} catch (Exception unknownException) {
						JOptionPane.showMessageDialog(null,
								"Unknown exception while compressing",
								"Decompress", JOptionPane.ERROR_MESSAGE);
					}
				}
			}).start();
		}
	}
}
