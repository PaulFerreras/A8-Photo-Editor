package picasso;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageEditor {
	//PF: Where the main method is located
	public static void main(String[] args) throws IOException {
		//PF: Reads in Picture from URL
		Picture f = PictureImpl.readFromURL("http://www.cs.unc.edu/~kmp/kmp.jpg");

		//PF: Sets up Main Frame
		JFrame main_frame = new JFrame();
		main_frame.setTitle("Assignment 8 Image Editor");
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//PF: Creates Model, View, and Controller
		ImageEditorModel model = new ImageEditorModel(f);
		ImageEditorView view = new ImageEditorView(main_frame, model);
 		ImageEditorController controller = new ImageEditorController(view, model);
		
 		//PF: Top panel that contains everything.
 		//Takes in View.
		JPanel top_panel = new JPanel();
		top_panel.setLayout(new BorderLayout());
		top_panel.add(view, BorderLayout.CENTER);
		main_frame.setContentPane(top_panel);

		//PF: Pack pushes everything together. Main Frame is made visible.
		main_frame.pack();
		main_frame.setVisible(true);
	}
}