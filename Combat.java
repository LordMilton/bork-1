/**
 * 
 */

/** Combat deals with attacks between NPCs and players. It calculates damage, accuracy, and damages players and NPCs
 * appropriately
 * @author Team Red
 *
 */
class Combat {
	
	//Raise this to 1 for perfect accuracy, Lower to 0 for perfect inaccuracy
	static final double ACCURACY = .8; //Can be removed if accuracy is added to weapons
	
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
		boolean hit = false;
		if(Math.random() < ACCURACY)
		{
			hit = true;
		}
		
		if(hit)
		{
			if(victim == null)
			{
				GameState.instance().changeHealth(weapon.getDamage());
				return "You are hit with a "+ weapon.getPrimaryName() +".";
			}
			else
			{
				victim.changeHealth(weapon.getDamage());
				return "You hit the "+ victim.getName() +" with your "+ weapon.getPrimaryName() +".";
			}
		}
		else
		{
			if(victim == null)
			{
				return "Your opponent attacks you and misses.";
			}
			else
			{
				return "You attack the "+ victim.getName() +" with your "+ weapon.getPrimaryName() +" but you miss.";
			}
		}
	}
}
