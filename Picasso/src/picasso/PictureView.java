package picasso;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PictureView extends Canvas implements ROIObserver {

	//PF: PictureView is the object that portrays the image to the user in view.
	//It does this using a Buffered Image and the Draw Image/Repaint? method in graphics.
	
	private ObservablePicture picture;
	private BufferedImage buffered_image;
	
	public PictureView(ObservablePicture p) {
		//PF: Constructor calls on internal "Set Picture" method
		setPicture(p);
	}

	public void setPicture(ObservablePicture p) {
		//PF: Returns out of the method if the picture being set
		//is the same as the picture already in this PictureView.
		if (picture == p) {
			return;
		}
		 
		//PF: Removes this PictureView as an observer of the previous 
		//picture, if there is already a picture in this PictureView
		if (picture != null) {
			picture.unregisterROIObserver(this);
		}
		
		//PF: Picture is set to new picture and adds this PictureView
		//as an observer of the new picture.
		//Instantiates a Buffered Image the same dimensions as the new picture.
		//Sets Size and Preferred Size.
		//Calls internal "Notify" method on region the size of the picture.
		picture = p;
		picture.registerROIObserver(this);
		buffered_image = new BufferedImage(p.getWidth(), p.getHeight(), BufferedImage.TYPE_INT_RGB);
		this.setPreferredSize(new Dimension(p.getWidth(), p.getHeight()));
		this.setSize(new Dimension(p.getWidth(), p.getHeight()));
		notify(picture, new RegionImpl(new Coordinate(0,0), new Coordinate(p.getWidth()-1,p.getHeight()-1)));		
	}
	
	//PF: Returns Picture.
	public ObservablePicture getPicture() {
		return picture;
	}
	
	//PF: Calls on Draw Image method in graphics using Buffered Image
	public void paint(Graphics g) {
		g.drawImage(buffered_image, 0, 0, this);
	}
	
	//PF: Sets Buffered Image by using two for loops (x-axis and y-axis),
	//the Get Pixel method in PictureImpl, the PixelToRGB method in Color Pixel,
	//and the SetRGB method in Buffered Image, and then repaint.
	public void notify(ObservablePicture picture, Region area) {
		for (int x=area.getLeft(); x<=area.getRight(); x++) {
			for (int y=area.getTop(); y<=area.getBottom(); y++) {
				buffered_image.setRGB(x, y, ColorPixel.pixelToRGB(picture.getPixel(x, y)));
			}
		}
		
		//PF: Repaints component
		repaint();
	}
}
