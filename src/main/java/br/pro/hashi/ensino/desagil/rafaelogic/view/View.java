package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.view.LogicView;



public class View extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	

	private JComboBox<Gate> menu;
	private LogicView logicView;
	
	public View(LinkedList<Gate> model) {
		
		menu = new JComboBox<>();
		
		for(Gate gate: model) {
			menu.addItem(gate);
		}
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(menu);
		
		addLogicView(0);
		
		menu.addActionListener(this);
	}

	private void addLogicView(int index) {
		Gate gate = menu.getItemAt(index);
		logicView = new LogicView(gate);
		add(logicView);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		remove(logicView);
		int index = menu.getSelectedIndex();
		addLogicView(index);

		// Linha necessária para evitar um bug gráfico. Não se preocupe em entendê-la.
		((JFrame) SwingUtilities.getRoot(this)).pack();
	}
	
	

}
