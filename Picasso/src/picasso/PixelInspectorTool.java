package picasso;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PixelInspectorTool implements Tool {
	
	//PF: Gets x and y coordinates
	//of pixel and its rgb values.
	//Contains the PixelInspectorUI and the Model.

	private PixelInspectorUI ui;
	private ImageEditorModel model;
	
	public PixelInspectorTool(ImageEditorModel model) {
		this.model = model;
		ui = new PixelInspectorUI();
	}
	
	//PF: Calls on Set Info method in PixelInspectorUI when Mouse is pressed
	
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
		}
		catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Pixel Inspector";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		try {
			ui.setInfo(e.getX(), e.getY(), model.getPixel(e.getX(), e.getY()));
		}
		catch (Exception ex) {
			// Click may have been out of bounds. Do nothing in this case.
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
