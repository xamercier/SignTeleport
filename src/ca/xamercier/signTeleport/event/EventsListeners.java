package ca.xamercier.signTeleport.event;

import org.bukkit.ChatColor;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import net.lectusAPI.utils.ServerUtils;

public class EventsListeners implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getClickedBlock() != null && e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			BlockState bs = e.getClickedBlock().getState();
			if (bs instanceof Sign) {
				Sign sign = (Sign) bs;
				if (sign.getLine(0).equalsIgnoreCase("[Serveur]")) {
					String srvID = sign.getLine(1);
					if (!sign.getLine(3).contains("Redemarre")) {
						ServerUtils.sendPlayerToServer(p, srvID);
					} else {
						p.sendMessage(ChatColor.RED + "Erreur: Ce serveur redemarre patiente un peu !");
					}
				}
			}
		}

	}

}
