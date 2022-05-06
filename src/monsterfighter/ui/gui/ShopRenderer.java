package monsterfighter.ui.gui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import monsterfighter.core.Item;
import monsterfighter.core.Purchasable;

public class ShopRenderer implements ListCellRenderer<ArrayList<Purchasable>>{

	private final JLabel label;
	
	public ShopRenderer() {
		this.label = new JLabel();
		label.setOpaque(true);
	}
	
	@Override
	public Component getListCellRendererComponent(JList<? extends ArrayList<Purchasable>> list,
			ArrayList<Purchasable> value, int index, boolean isSelected, boolean cellHasFocus) {
        label.setText("[" + value.size() + "] " + value.get(0).buyDescription());

        if (isSelected) {
            label.setBackground(list.getSelectionBackground());
            label.setForeground(list.getSelectionForeground());
        } else {
            label.setBackground(list.getBackground());
            label.setForeground(list.getForeground());
        }

        return label;
	}
}
