package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class AndGate extends Gate {
	
	private NandGate nandFirst;
	private NandGate nandSecond;
	
	
	public AndGate() {
		super("And");
		nandFirst = new NandGate();
		nandSecond = new NandGate();
		
		
		nandSecond.connect(0, nandFirst);
		nandSecond.connect(1, nandFirst);
	}
	
	@Override
	public boolean read() {
		return nandSecond.read();
	}
	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		if (pinIndex == 0) {
			nandFirst.connect(0, emitter);
		}
		
		if (pinIndex == 1) {
			nandFirst.connect(1, emitter);
		}
	}

}
