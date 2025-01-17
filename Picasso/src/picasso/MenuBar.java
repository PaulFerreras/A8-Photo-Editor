package picasso;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class MenuBar extends JMenuBar {

	//PF: Made new class Menu Bar just to place all menu related items in one place.
	//Menu Items work just like JButtons using ActionListeners and Action Commands.
	//Contains menus and menu items.

	private JMenu file_menu;
	private JMenuItem open_menu_item;
	private JMenuItem save_menu_item;

	public MenuBar(ImageEditorView view) {
		//PF: Menus are initialized
		file_menu = new JMenu("File");

		//PF: Menu items are initialized
		open_menu_item = new JMenuItem("Open", 'o');
		save_menu_item = new JMenuItem("Save", 's');

		//PF: Menu items action commands are set
		open_menu_item.setActionCommand("Open");
		save_menu_item.setActionCommand("Save");

		//PF: ImageEditorController is added to each menu item as an ActionListener
		open_menu_item.addActionListener(view);
		save_menu_item.addActionListener(view);
		
		//PF: HotKeys are added to the menu items
		open_menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		save_menu_item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

		//PF: Menu items are added to their appropriate Menus
		file_menu.add(open_menu_item);
		file_menu.add(save_menu_item);

		//PF: Menus are added to menu bar
		add(file_menu);
	}
	
}
