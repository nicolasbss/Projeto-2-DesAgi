package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;



public class LogicView extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	

	
	
	// objetos da classe JCheckBox usada para criar os checkbox
    private JCheckBox in1Check;
    private JCheckBox in2Check;
    private JCheckBox outCheck;

	private Gate gate;
    
    
	public LogicView(Gate gate) {
		this.gate = gate;
		String name = gate.toString();
		
		//criacao dos objetos da classe JCheckBox usados para criar os checkbox
		in1Check = new JCheckBox();
		in2Check = new JCheckBox();
		outCheck = new JCheckBox();
		
		JLabel inLabel = new JLabel("Entrada");
		JLabel outLabel = new JLabel("Saida");
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		
		if (name == "Not") {
			add(inLabel);
			add(in1Check);
		}
		else {
			add(inLabel);
			add(in1Check);
			add(in2Check);
			
		}
		
		add(outLabel);
		add(outCheck);
		
		//estabelecendo que o subpainel reage a mudancas nos dois primeiros checkboxes
		in1Check.addActionListener(this);
		in2Check.addActionListener(this);
		
		//estabelecendo que o terceiro checkbox nao pode ser modificado
		outCheck.setEnabled(false);

		update();
	}
	
	private void update() {
		
		Source source1 = new Source();
		Source source2 = new Source();
		
		//obtendo o estado das checkboxes (true ou false/checado ou nao)
		boolean in1 = in1Check.isSelected();
		boolean in2 = in2Check.isSelected();
		
		source1.turn(in1);
		source2.turn(in2);
		
		gate.connect(0, source2);
		gate.connect(1, source1);
		boolean result;
		result = gate.read();
		//devolvendo o estado da ultima checkbox de acordo com o resultado da porta logica
		outCheck.setSelected(result);
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}
		

}
