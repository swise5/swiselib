package uk.ac.ucl.swise.behaviours;

/**
 * The BehaviourFramework holds together a series of behaviours (encoded in BehaviourNodes)
 * which are linked together by a series of decisions (encoded in DecisionLinks).
 * 
 * @author swise
 *
 */
public interface BehaviourFramework {
	
//	public BehaviourNode entryPoint; 
	
	public BehaviourNode getHomeNode();
	public BehaviourNode getEntryPoint(); // the point at which Agents are initialised
	
}