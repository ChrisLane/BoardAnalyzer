package boardanalyzer.ui;

import boardanalyzer.board_logic.Hold;
import boardanalyzer.utils.Vector2;

import java.awt.GridLayout;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HoldSelectionSettings extends JPanel {
	ArrayList<JCheckBox> m_hold_type_checkboxes;
	private JButton m_save_hold_button ;
	private JButton m_delete_hold_button;
	private JButton m_suggest_type_button;
	private JButton m_suggest_direction_button;
	private JLabel m_direction_label;
	private JLabel m_size_label;
	private Hold m_new_hold;
	public HoldSelectionSettings(
			JButton save_hold_button, 
			JButton delete_hold_button, 
			JButton suggest_type_button, 
			JButton suggest_direction_button) {
		super(new GridLayout(9, 1));
		m_hold_type_checkboxes = new ArrayList<JCheckBox>();
		for (Hold.Type hold : Hold.Type.values()) {
			JCheckBox cb = new JCheckBox(hold.toString());
			m_hold_type_checkboxes.add(cb);
			add(cb);
		}

		m_new_hold = new Hold();
		
		m_direction_label = new JLabel("Direction: --");
		add(m_direction_label);
		
		m_size_label = new JLabel("Size: --");
		add(m_size_label);
		
		m_save_hold_button = save_hold_button;
		add(m_save_hold_button);
		
		m_delete_hold_button = delete_hold_button;
		add(m_delete_hold_button);
		
		add(Box.createVerticalGlue());
		
		m_suggest_type_button = suggest_type_button;
		add(m_suggest_type_button);
		
		m_suggest_direction_button = suggest_direction_button;
		add(m_suggest_direction_button);
		
		disableAll();
	}
	
	public boolean isCrimp() {
		return m_hold_type_checkboxes.get(Hold.Type.CRIMP.ordinal()).isSelected();
	}
	
	public boolean isJug() {
		return m_hold_type_checkboxes.get(Hold.Type.JUG.ordinal()).isSelected();
	}
	
	public boolean isSloper() {
		return m_hold_type_checkboxes.get(Hold.Type.SLOPER.ordinal()).isSelected();
	}
	
	public boolean isPocket() {
		return m_hold_type_checkboxes.get(Hold.Type.POCKET.ordinal()).isSelected();
	}
	
	public boolean isPinch() {
		return m_hold_type_checkboxes.get(Hold.Type.PINCH.ordinal()).isSelected();
	}
	
	public boolean isFoot() {
		return m_hold_type_checkboxes.get(Hold.Type.FOOT.ordinal()).isSelected();
	}
	
	public double getDirection() {
		return m_new_hold.direction();
	}
	
	public Vector2 getHoldSize() {
		return m_new_hold.size();
	}
	
	public Vector2 getHoldPosition() {
		return m_new_hold.position();
	}

	public Hold getNewHold() {return m_new_hold;};
	
	public void setToHoldType(Hold.Type type) {
		setCrimp(true);
		setJug(false);
		setSloper(false);
		setPocket(false);
		setFoot(false);
		setPinch(false);

		switch (type) {
			case CRIMP -> setCrimp(true);
			case JUG -> setJug(true);
			case SLOPER -> setSloper(true);
			case POCKET -> setPocket(true);
			case FOOT -> setFoot(true);
			case PINCH -> setPinch(true);
		}
	}
	
	public void setCrimp(boolean b) {
		m_hold_type_checkboxes.get(Hold.Type.CRIMP.ordinal()).setSelected(b);
	}
	
	public void setJug(boolean b) {
		m_hold_type_checkboxes.get(Hold.Type.JUG.ordinal()).setSelected(b);
	}
	
	public void setSloper(boolean b) {
		m_hold_type_checkboxes.get(Hold.Type.SLOPER.ordinal()).setSelected(b);
	}
	
	public void setPocket(boolean b) {
		m_hold_type_checkboxes.get(Hold.Type.POCKET.ordinal()).setSelected(b);
	}
	
	public void setPinch(boolean b) {
		m_hold_type_checkboxes.get(Hold.Type.PINCH.ordinal()).setSelected(b);
	}
	
	public void setFoot(boolean b) {
		m_hold_type_checkboxes.get(Hold.Type.FOOT.ordinal()).setSelected(b);
	}
	
	public void setDirection(double rad) {
		m_new_hold.setDirection(rad);
		double adjusted_degs = Math.toDegrees(rad - (3 * Math.PI)/2 + (4 * Math.PI)) % 360;
		DecimalFormat formatted_num = new DecimalFormat("#.##");
		m_direction_label.setText("Direction: " + formatted_num.format(adjusted_degs) + "°");
	}
	
	public void setHoldSize(
			Vector2 size, 
			Hold old_hold) {
		m_new_hold.setSize(size);
		DecimalFormat formatted_num = new DecimalFormat("#.##");
		m_size_label.setText("Size: (" +
				formatted_num.format(m_new_hold.size().x) + ", " +
				formatted_num.format(m_new_hold.size().y) + ")");
	}

	public void setPosition(Vector2 pos) {
		m_new_hold.setPosition(pos);
	}
	
	public void disableAll() {
		for (JCheckBox cb : m_hold_type_checkboxes) {
			cb.setEnabled(false);
		}
		m_save_hold_button.setEnabled(false);
		m_delete_hold_button.setEnabled(false);
		m_suggest_type_button.setEnabled(false);
		m_suggest_direction_button.setEnabled(false);
		m_direction_label.setText("Direction: --°");
		m_size_label.setText("Size: --");
	}
	
	public void enableAll() {
		for (JCheckBox cb : m_hold_type_checkboxes) {
			cb.setEnabled(true);
		}
		m_delete_hold_button.setEnabled(true);
		m_save_hold_button.setEnabled(true);
		m_suggest_type_button.setEnabled(true);
		m_suggest_direction_button.setEnabled(true);
	}
}
