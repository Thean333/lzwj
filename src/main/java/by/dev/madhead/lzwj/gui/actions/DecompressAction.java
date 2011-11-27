package by.dev.madhead.lzwj.gui.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import by.dev.madhead.lzwj.compress.LZW;
import by.dev.madhead.lzwj.util.Constants;

/**
 * Action for performing decompression from GUI.
 * 
 * @author madhead
 * 
 */
public class DecompressAction extends AbstractAction {
	private static final long serialVersionUID = -1216000901898029741L;

	/**
	 * Default no-arg constructor.
	 */
	public DecompressAction() {
		putValue(Action.NAME, "Decompress");
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser sourceFileChooser = new JFileChooser();

		sourceFileChooser.setFileFilter(new FileFilter() {

			@Override
			public String getDescription() {
				return "lzwj compressed file";
			}

			@Override
			public boolean accept(File f) {
				if ((f.getName().endsWith(Constants.COMPRESSED_FILE_SUFFIX)) || (f.isDirectory())) {
					return true;
				}
				return false;
			}
		});
		sourceFileChooser.setMultiSelectionEnabled(false);
		if (JFileChooser.APPROVE_OPTION == sourceFileChooser
				.showOpenDialog(null)) {
			final String fileName = sourceFileChooser.getSelectedFile()
					.getAbsolutePath();
			JFileChooser destFileChooser = new JFileChooser();
			if (JFileChooser.APPROVE_OPTION == destFileChooser
					.showSaveDialog(null)) {
				final String destFileName = destFileChooser.getSelectedFile()
						.getAbsolutePath();
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							FileInputStream in = new FileInputStream(fileName);
							FileOutputStream out = new FileOutputStream(
									destFileName);
							LZW lzw = new LZW();

							lzw.decompress(in, out);

							in.close();
							out.close();
							JOptionPane.showMessageDialog(null,
									"Decompression succesfully finished!",
									"Decompress",
									JOptionPane.INFORMATION_MESSAGE);
						} catch (FileNotFoundException fileNotFoundException) {
							JOptionPane.showMessageDialog(null,
									"File not found!", "Decompress",
									JOptionPane.ERROR_MESSAGE);
						} catch (IOException ioException) {
							JOptionPane.showMessageDialog(null,
									"I/O exception while decompressing",
									"Decompress", JOptionPane.ERROR_MESSAGE);
						} catch (Exception unknownException) {
							JOptionPane.showMessageDialog(null,
									"Unknown exception while decompressing",
									"Decompress", JOptionPane.ERROR_MESSAGE);
						}
					}
				}).start();
			}
		}
	}
}
