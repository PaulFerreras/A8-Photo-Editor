package picasso;

public class ImageEditorModel {

	//PF: This is where the actual picture is stored.
	//All changes to the picture should be done in here.
	
	private Picture original;
	private ObservablePicture current;
	private PictureView current_view;
	
	public ImageEditorModel(Picture p) {
		//PF: The original picture holds the original version.
		//Current is just a copy of the original. 
		//*I will probably change this soon to make current a PictureView.*
		original = p;
		current = original.copy().createObservable();
		current_view = new PictureView(current);
	}

	//PF: Gets current as an observable picture.
	public ObservablePicture getCurrent() {
		return current_view.getPicture();
	}
	
	//PF: Gets Current View.
	public PictureView getCurrentView() {
		return current_view;
	}
	
	//PF: Sets current as a new picture.
	public void setCurrent(Picture p) {
		original = p;
		current = original.copy().createObservable();
		current_view.setPicture(current);
	}

	//PF: Gets Pixel at x and y value.
	//Delegated to PictureImpl.
	public Pixel getPixel(int x, int y) {
		return current.getPixel(x, y);
	}

	//PF: Paints a square around the cursor when clicked.
	//Used by PaintBrushTool
	//This is done using two for loops (x-axis and y-axis)
	//and the Set Pixel method in PictureImpl. 
	//The view is updated due to suspend and resumeObservable;
	//*I may try to change this updated so it uses observer/observable instead.
	public void paintAt(int x, int y, Pixel brushColor, int brush_size) {
		current.suspendObservable();;
		
		for (int xpos=x-brush_size+1; xpos <=x+brush_size-1; xpos++) {
			for (int ypos=y-brush_size+1; ypos <=y+brush_size-1; ypos++) {
				if (xpos >= 0 &&
					xpos < current.getWidth() &&
					ypos >= 0 &&
					ypos < current.getHeight()) {
					current.setPixel(xpos, ypos, brushColor);
				}
			}
		}
		current.resumeObservable();
	}
	
	//NOTE: Keep in mind, xpos and ypos are initialized 
	//at the upper left coordinate of the square.
	//If brush size = 1, the area of the square is 1x1,
	//   brush size = 2, the area of the square is 3x3,
	//   brush size = 3, the area of the square is 5x5,
	//...so on and so forth.
	//(brush size != 0);
}
