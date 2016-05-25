package picasso;

import java.awt.event.MouseEvent;

import javax.swing.JPanel;

public class PaintBrushTool implements Tool {
	
	//PF: Paints square of pixels on model
	//Contains the PaintBrushToolUI and the Model

	private PaintBrushToolUI ui;
	private ImageEditorModel model;
	//LB no longer needed: private int brush_size = 5; 
	
	public PaintBrushTool(ImageEditorModel model) {
		this.model = model;
		ui = new PaintBrushToolUI();
	}
	
	//PF: Calls on Paint At method in the Model when Mouse is pressed
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//LB replaced brush size with ui.getBrushSize()
		//PF: Paint At method takes in x and y coordinate, brush color, and brush size.
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize());
		
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
	public void mouseDragged(MouseEvent e) {
		//LB replaced brush size with ui.getBrushSize()
		model.paintAt(e.getX(), e.getY(), ui.getBrushColor(), ui.getBrushSize());
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getName() {
		return "Paint Brush";
	}

	@Override
	public JPanel getUI() {
		return ui;
	}

}
