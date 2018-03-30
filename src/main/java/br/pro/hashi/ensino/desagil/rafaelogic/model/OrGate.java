package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class OrGate extends Gate {
	
	private NandGate nandTop;
	private NandGate nandBottom;
	private NandGate nandRight;
	
	public OrGate() {
		nandTop = new NandGate();
		nandBottom = new NandGate();
		nandRight = new NandGate();
		
		nandRight.connect(0, nandTop);
		nandRight.connect(1, nandBottom);
	}
	
	@Override
	public boolean read() {
		return nandRight.read();
	}
	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		if (pinIndex == 0) {
			nandTop.connect(0, emitter);
			nandTop.connect(1, emitter);
		}
		
		if (pinIndex == 1) {
			nandBottom.connect(0, emitter);
			nandBottom.connect(1, emitter);
		}
	}
}
