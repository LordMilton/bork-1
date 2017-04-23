/**
 * 
 */

/** AttackCommand deals with any Command relating to the attacking of an NPC or a player.
 * @author Team Red
 *
 */
class AttackCommand extends Command{
	private Item weapon;
	private NPC victim;
	
	/** Instantiates an AttackCommand using the parameter weapon
	 * 
	 * @param weapon Weapon that is being used to attack
	 * @param victim NPC that is being attacked, null implies that the player is being attacked
	 */
	AttackCommand(Item weapon, NPC victim)
	{
		this.weapon = weapon;
		this.victim = victim;
	}
	
	/**
	 * @override From Command
	 */
	String execute()
	{
		if(weapon == null)
		{
			return "Attack "+ victim +" with what?\n";
		}
		if(!weapon.getIsAWeapon())
		{
			return "You cannot attack with that.";
		}
		
		return Combat.attack(weapon, victim);
	}
}
