package picasso;

import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ToolChooserWidget extends JPanel implements ItemListener {
	
	//PF: This contains the list of tools.
	//Contains JComboBox of tools and an ArrayList of ToolChoiceListeners.

	private JComboBox tool_choices;
	private List<ToolChoiceListener> listeners;

	//PF: This is the list of tools
	private static final String[] choices = {"Pixel Inspector", "Paint Brush"};

	public ToolChooserWidget() {
		setLayout(new BorderLayout());
		add(new JLabel("Tool: "), BorderLayout.WEST);

		//PF: List of tools gets added to the JComboBox
		tool_choices = new JComboBox(choices);
		add(tool_choices, BorderLayout.CENTER);
		tool_choices.addItemListener(this);

		//PF: ArrayList of listeners gets instantiated
		listeners = new ArrayList<ToolChoiceListener>();
	}

	//PF: Adds controller as ToolChoiceListener to the ArrayList
	public void addToolChoiceListener(ToolChoiceListener l) {
		listeners.add(l);
	}

	//PF: Everytime the item in a JComboBox is changed, This method is run.
	//It gets the name of the tool selected, and calls on the method 
	//toolChosen in all of it's ToolChoiceListeners (i.e. Controller)
	//to change the tool.
	
	//PF: It gets run twice because JComboBox has to unselect the item that was previously selected.
	//And then select the item that was chosen.
	
	//PF: Still not sure why the If Statement and the ArrayList<ToolChoiceListeners> is needed.
	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) { 
			String tool_name = (String) e.getItem();

			for (ToolChoiceListener l : listeners) {
				l.toolChosen(tool_name);
			}
		}
	}

	//PF: Gets the name of the current tool as a string
	public String getCurrentToolName() {
		return (String) tool_choices.getSelectedItem();
	}
}
