/**
 * 
 */

/** AttackCommand deals with any Command relating to the attacking of an NPC or a player.
 * @author Team Red
 *
 */
class AttackCommand extends Command{
	private Item weapon;
	private boolean weaponProvided;
	private NPC victim;
	private boolean victimProvided;
	private boolean victimIsPlayer;
	
	/** Instantiates an AttackCommand using the parameter weapon
	 * 
	 * @param weapon Weapon that is being used to attack
	 * @param victim NPC that is being attacked, "player" implies that the player is being attacked
	 */
	AttackCommand(String weapon, String victim)
	{
		GameState state = GameState.instance();
		if(weapon != null)
		{
			try{
				this.weapon = state.getItemFromInventoryNamed(weapon);
			}catch(Item.NoItemException e){
				this.weapon = null;
			}
			weaponProvided = true;
		}
		else
		{
			weaponProvided = false;
		}
		if(victim == null)
		{
			victimProvided = false;
		}
		else if(!victim.equals("player"))
		{
			try{
				this.victim = state.getNPCInVicinityNamed(victim);
			}catch(NPC.NoNPCException e){
				this.victim = null;
			}
			victimIsPlayer = false;
			victimProvided = true;
		}
		else
		{
			victimIsPlayer = true;
			victimProvided = true;
		}
	}
	
	/**
	 * @override From Command
	 */
	String execute()
	{
		if(!weaponProvided && !victimProvided)
		{
			return "What do you want to attack, and with what?\n";
		}
		if(!weaponProvided && victim != null)
		{
			return "Attack "+ victim +" with what?\n";
		}
		if(weapon == null)
		{
			return "You are not carrying that item.\n";
		}
		if(!weapon.getIsAWeapon())
		{
			return "You cannot attack with that.\n";
		}
		
		if(victim == null && !victimIsPlayer)
		{
			return "You can't attack what isn't there!\n";
		}
		
		return Combat.attack(weapon, victim);
	}
}
