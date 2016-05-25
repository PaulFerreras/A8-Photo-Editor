package picasso;

import java.awt.BorderLayout;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ImageEditorView extends JPanel {
	
	//PF: This is the main UI.
	//This is what the user sees and can interact with.
	//Contains main_frame and model

	private JFrame main_frame;
	private PictureView frame_view;
	private ImageEditorModel model;
	private ToolChooserWidget chooser_widget;
	private JPanel tool_ui_panel;
	private JPanel tool_ui;
	
	public ImageEditorView(JFrame main_frame, ImageEditorModel model) {
		this.main_frame = main_frame;
		
		//PF: Set View Layout to BorderLayout
		setLayout(new BorderLayout());
		
		//PF: Frame View is a Picture view that takes in Picture current
		//from the model.
		frame_view = new PictureView(model.getCurrent());
		
		//PF: Frame View gets placed in the center
		add(frame_view, BorderLayout.CENTER);
		
		//PF: Tool UI Panel contains UI panel of current tool
		//as well as JComboBox from Chooser Widget.
		tool_ui_panel = new JPanel();
		tool_ui_panel.setLayout(new BorderLayout());
		
		//PF: Chooser Widget is a JComboBox that holds all the tools
		chooser_widget = new ToolChooserWidget();
		
		//PF: Chooser Widget is placed NORTH in the Tool UI Panel,
		//which is then placed SOUTH in the view
		tool_ui_panel.add(chooser_widget, BorderLayout.NORTH);
		add(tool_ui_panel, BorderLayout.SOUTH);
		
		//PF: This is the actual UI Panel of the current tool
		tool_ui = null;
	}
	
	//PF: Adds controller as a ToolChoiceListener to Chooser Widget.
	//NOTE that I said Chooser Widget and NOT View or Frame View.
	public void addToolChoiceListener(ToolChoiceListener l) {
		chooser_widget.addToolChoiceListener(l);
	}
	//NOTE: This gets added to Chooser Widget's ArrayList of ToolChoiceListeners.
	
	//PF: Gets Current Tool's name.
	//Delegated to Chooser Widget's getCurrentToolName method.
	public String getCurrentToolName() {
		return chooser_widget.getCurrentToolName();
	}
	
	//PF: This is where the Tool UI gets changed visually when the tool changes. 
	//View gets revalidated, and Main Frame gets repacked.
	public void installToolUI(JPanel ui) {
		if (tool_ui != null) {
			tool_ui_panel.remove(tool_ui);
		}
		tool_ui = ui;
		tool_ui_panel.add(tool_ui, BorderLayout.CENTER);
		validate();
		main_frame.pack();
	}
	
	//PF: Adds ImageEditorController as a MouseMotion and MouseListener to the Frame View.
	//NOTE these are to the Frame View and NOT View or Chooser Widget
	@Override
	public void addMouseMotionListener(MouseMotionListener l) {
		frame_view.addMouseMotionListener(l);
	}
	
	@Override
	public void removeMouseMotionListener(MouseMotionListener l) {
		frame_view.removeMouseMotionListener(l);
	}

	@Override
	public void addMouseListener(MouseListener l) {
		frame_view.addMouseListener(l);
	}
	
	@Override
	public void removeMouseListener(MouseListener l) {
		frame_view.removeMouseListener(l);
	}
	

}
