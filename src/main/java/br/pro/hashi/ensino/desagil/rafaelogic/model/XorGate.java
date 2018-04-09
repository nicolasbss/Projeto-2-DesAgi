package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class XorGate extends Gate {
	
	private NandGate nandLeft;
	private NandGate nandBottom;
	private NandGate nandRight;
	private NandGate nandTop;
	
	public XorGate(){
		super("Xor");
		nandLeft = new NandGate();
		nandBottom = new NandGate();
		nandRight = new NandGate();
		nandTop = new NandGate();
		
		nandTop.connect(1, nandLeft);
		nandBottom.connect(0, nandLeft);
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
			nandLeft.connect(0, emitter);

		}
		
		if (pinIndex == 1) {
			nandLeft.connect(1, emitter);
			nandBottom.connect(1, emitter);
		}
	}
}
