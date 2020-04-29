/**
 * Here all events will be handled.
 * 
 * @version 1.0
 * @author Arjan de Haan (Vepnar)
 */
package vepnar.bettermobs;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

// Events
import org.bukkit.event.player.PlayerMoveEvent;

public class MobListener implements Listener {

	Main javaplugin;

	/**
	 * Initialize the Bettermobs event listener
	 * 
	 * @param m the JavaPlugin class or main file
	 */
	public MobListener(Main m) {

		javaplugin = m;
	}
	
	/**
	 * Handle all PlayerMoveEvent's. and call events who are enabled in the configuration file.
	 * Only do this rarely because this is quite cpu heavy when there are a lot of active players.
	 */
	@EventHandler
	public void onEntityInteractEvent(PlayerMoveEvent e) {
		int rand = (int) (Math.random() * 50); // Reduce lag
		if(rand != 1 || javaplugin.disabled) return;
		
		for (EventClass event : javaplugin.eventList) {
			if (!event.canBeCalled(e))
				continue;
			event.callEvent(e);
		}
		
	}
	
	

}