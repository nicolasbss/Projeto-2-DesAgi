package br.pro.hashi.ensino.desagil.rafaelogic.view;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;
import javax.swing.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JColorChooser;

import br.pro.hashi.ensino.desagil.rafaelogic.model.Gate;
import br.pro.hashi.ensino.desagil.rafaelogic.model.Source;



public class LogicView extends SimplePanel implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;
	

	
	
	// objetos da classe JCheckBox usada para criar os checkbox
    private JCheckBox in1Check;
    private JCheckBox in2Check;

	private Gate gate;
	
	private Color color;
	private Image image;
	private boolean led_on;
    
    
	public LogicView(Gate gate) {
		super(400,310);
		this.gate = gate;
		
		//criacao dos objetos da classe JCheckBox usados para criar os checkbox
		in1Check = new JCheckBox();
		in2Check = new JCheckBox();
		
		
		
		
		if (gate.getSize() == 1) {
			add(in1Check, 40, 165, 17, 17);
		}
		if (gate.getSize() == 2) {
			add(in1Check, 40, 127, 17, 17);
			add(in2Check, 40, 205, 17, 17);
			
		}
		
		//estabelecendo que o subpainel reage a mudancas nos dois primeiros checkboxes
		in1Check.addActionListener(this);
		in2Check.addActionListener(this);
		
		color = Color.BLACK;
		
		String path = "/" + gate.toString() + ".png";
		URL url = getClass().getResource(path);
		image = new ImageIcon(url).getImage();

		update();
		

		
		addMouseListener(this);
	}
	
	private void update() {
		
		Source source1 = new Source();
		Source source2 = new Source();
		
		if(gate.getSize() == 1) {
			
			//obtendo o estado da checkbox (true ou false/checado ou nao)
			boolean in1 = in1Check.isSelected();
			
			//ligando a fonte
			source1.turn(in1);
			
			gate.connect(0, source1);
			
		}
		
		if(gate.getSize() == 2) {
			
			//obtendo o estado das checkboxes (true ou false/checado ou nao)
			boolean in1 = in1Check.isSelected();
			boolean in2 = in2Check.isSelected();
			
			//ligando as fontes
			source1.turn(in1);
			source2.turn(in2);
			
			gate.connect(0, source1);
			gate.connect(1, source2);
			
		}
		
		
		//devolvendo o estado da ultima checkbox de acordo com o resultado da porta logica
		if(gate.read() == false){
			color = Color.BLACK;
			led_on = false;
			repaint();
		}
		if(gate.read() == true){
			color = Color.RED;
			led_on = true;
			repaint();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
		update();
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {

		int x = event.getX();
		int y = event.getY();
		//codigo para determinar se o clique foi dentro ou fora do circulo
		double click = Math.sqrt(Math.pow(335-x, 2)+Math.pow(174-y, 2));
		
		if(click <= 10 && led_on == true) {
			color = JColorChooser.showDialog(this, null, color);
			repaint();
		}
	}
	@Override
	public void mousePressed(MouseEvent event) {
	}
	@Override
	public void mouseReleased(MouseEvent event) {
	}
	@Override
	public void mouseEntered(MouseEvent event) {
	}
	@Override
	public void mouseExited(MouseEvent event) {
	}
	
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);


		g.drawImage(image, 43, 100, 300, 150, null);

		// Desenha um circulo cheio
		g.setColor(color);
		g.fillOval(325, 164, 20, 20);

		getToolkit().sync();
    }
		

}
