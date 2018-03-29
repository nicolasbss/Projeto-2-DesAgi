package br.pro.hashi.ensino.desagil.rafaelogic.model;

public class AndGate extends Gate {
	
	private NandGate nandfirst;
	private NandGate nandsecond;
	
	
	public AndGate() {
		nandfirst = new NandGate();
		nandsecond = new NandGate();
		
		
		nandsecond.connect(0, nandfirst);
		nandsecond.connect(1, nandfirst);
	}
	
	@Override
	public boolean read() {
		return nandsecond.read();
	}
	
	@Override
	public void connect(int pinIndex, Emitter emitter) {
		if (pinIndex == 0) {
			nandfirst.connect(0, emitter);
		}
		
		if (pinIndex == 1) {
			nandfirst.connect(1, emitter);
		}
	}

}
