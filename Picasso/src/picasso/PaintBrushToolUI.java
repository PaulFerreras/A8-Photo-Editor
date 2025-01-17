package picasso;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class PaintBrushToolUI extends JPanel implements ChangeListener {
	
	//PF: UI of PaintBrushTool.
	//Contains JSliders for rgb value and (LB) brush size.
	//As well as a Color Preview to show which color is currently set.

	private JSlider red_slider;
	private JSlider green_slider;
	private JSlider blue_slider;
	private PictureView color_preview;
	//LB brush slider
	private JSlider brush_size_slider;
	//LB brush size 
	private int brush_size;
	
	public PaintBrushToolUI() {
		//PF: set to GridLayout(0x1) or a Vertical Layout
		setLayout(new GridLayout(0,1));
		
		//PF: Color Chooser Panel is instantiated. 
		//Set to BorderLayout.
		JPanel color_chooser_panel = new JPanel();
		color_chooser_panel.setLayout(new BorderLayout());
		
		//PF: Slider Panel is instantiated. Set to GridLayout(0x1).
		JPanel slider_panel = new JPanel();
		slider_panel.setLayout(new GridLayout(0,1));
		
		//PF: Red Slider and Red Slider is instantiated.
		//Set to BorderLayout.
		JPanel red_slider_panel = new JPanel();
		JLabel red_label = new JLabel("Red:");
		red_slider_panel.setLayout(new BorderLayout());
		red_slider_panel.add(red_label, BorderLayout.WEST);
		red_slider = new JSlider(0,100);
		red_slider.addChangeListener(this);
		red_slider_panel.add(red_slider, BorderLayout.CENTER);

		//PF: Green Slider Panel and Green Slider is instantiated.
		//Set to BorderLayout.
		JPanel green_slider_panel = new JPanel();
		JLabel green_label = new JLabel("Green:");
		green_slider_panel.setLayout(new BorderLayout());
		green_slider_panel.add(green_label, BorderLayout.WEST);
		green_slider = new JSlider(0,100);
		green_slider.addChangeListener(this);
		green_slider_panel.add(green_slider, BorderLayout.CENTER);

		//PF: Blue Slider Panel and Blue Slider is instantiated.
		//Set to BorderLayout.
		JPanel blue_slider_panel = new JPanel();
		JLabel blue_label = new JLabel("Blue: ");
		blue_slider_panel.setLayout(new BorderLayout());
		blue_slider_panel.add(blue_label, BorderLayout.WEST);
		blue_slider = new JSlider(0,100);
		blue_slider.addChangeListener(this);
		blue_slider_panel.add(blue_slider, BorderLayout.CENTER);
		
		
		//LB this sets the brush slider.
		//Set to BorderLayout.
		JPanel brush_size_slider_panel = new JPanel();
		JLabel brush_size_label = new JLabel("Size: ");
		brush_size_slider_panel.setLayout(new BorderLayout());
		brush_size_slider_panel.add(brush_size_label, BorderLayout.WEST);
		brush_size_slider = new JSlider(0,100, 5);
		brush_size_slider.addChangeListener(this);
		brush_size_slider_panel.add(brush_size_slider, BorderLayout.CENTER);

		// Assumes green label is widest and asks red and blue label
		// to be the same.
		Dimension d = green_label.getPreferredSize();
		red_label.setPreferredSize(d);
		blue_label.setPreferredSize(d);
		brush_size_label.setPreferredSize(d);//LB 
		
		//PF: Each individual slider panel is added to the Slider Panel
		slider_panel.add(red_slider_panel);
		slider_panel.add(green_slider_panel);
		slider_panel.add(blue_slider_panel);
		//LB adds the brush size slider to the slider panel
		slider_panel.add(brush_size_slider_panel);
		
		//PF: Slider Panel is added to Color Chooser Panel in the CENTER
		color_chooser_panel.add(slider_panel, BorderLayout.CENTER);

		//PF: Color Preview is instantiated and added to the Color Chooser Panel in the EAST
		//ColorPreview is a PictureImpl that is made up of 50x50 pixels.
		color_preview = new PictureView(new ObservablePictureImpl(new PictureImpl(50,50)));
		color_chooser_panel.add(color_preview, BorderLayout.EAST);

		//pf: Color Chooser Panel is added to PaintBrushToolUI
		add(color_chooser_panel);
		
		//LB gets the brush size value
		brush_size = brush_size_slider.getValue();
		stateChanged(null);
	}
	
	//PF: Everytime a slider moves, this method gets called.

	@Override
	public void stateChanged(ChangeEvent e) {
		//LB gets the brush size value 
		brush_size =  brush_size_slider.getValue();
		
		//PF: Color Preview changes color using two for loops (x-axis and y-axis)
		//and the Set Pixel method in PictureImpl. 
		//RGB values come from the red, green, and blue slider.
		//The Color Preview get updated using the suspend and resume observable method.
		Pixel p = new ColorPixel(red_slider.getValue()/100.0,
				                 green_slider.getValue()/100.0,
				                 blue_slider.getValue()/100.0);
		ObservablePicture preview_frame = color_preview.getPicture();
		preview_frame.suspendObservable();
		for (int x=0; x<preview_frame.getWidth(); x++) {
			for (int y=0; y<preview_frame.getHeight(); y++) {
				preview_frame.setPixel(x, y, p);
			}
		}
		preview_frame.resumeObservable();
	}
		
	
	
	//LB gets the Brush size 
	public int getBrushSize() {
		return brush_size;
	}
	
	//PF: gets the Brush Color
	public Pixel getBrushColor() {
		return color_preview.getPicture().getPixel(0,0);
	}
}
