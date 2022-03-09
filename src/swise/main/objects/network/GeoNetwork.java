package swise.main.objects.network;

import sim.field.network.Network;

public class GeoNetwork extends Network {
	
	long uniqueNodeCounter = -1;

	public GeoNetwork() {
		super();
	}
	
	public GeoNetwork (boolean b) {
		super(b);
	}
	
	public long getNextNodeIndex() {
		uniqueNodeCounter = uniqueNodeCounter + 1;
		return uniqueNodeCounter;
	}
	
}