/**
 * 
 */

/** Combat deals with attacks between NPCs and players. It calculates damage, accuracy, and damages players and NPCs
 * appropriately
 * @author Team Red
 *
 */
class Combat {
	
	/** Causes an attack upon the specified NPC (or the player) with the specified Item
	 * 
	 * @param weapon Item being used to attack victim, not checked if the Item is actually defined as a weapon (this
	 * should be checked before calling attack)
	 * @param victim NPC being attacked, if null then the player is being attacked
	 * @return A message stating whether or not the parameter victim was hit and with the proper person depending if
	 * the parameter victim is an NPC or the player
	 */
	public static String attack(Item weapon, NPC victim)
	{
		
	}
}
