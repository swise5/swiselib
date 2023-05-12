package swise.behaviours;

/**
 * The BehaviourFramework holds together a series of behaviours (encoded in BehaviourNodes)
 * which are linked together by a series of decisions (encoded in DecisionLinks).
 * 
 * @author swise
 *
 */
public class BehaviourFramework {
	
	public BehaviourNode entryPoint; // the point at which Agents are initialised
	
	public BehaviourNode getHomeNode(){
		return entryPoint;
	}
}