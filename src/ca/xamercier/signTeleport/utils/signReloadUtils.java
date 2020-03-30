package ca.xamercier.signTeleport.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;

import net.lectusAPI.MainLectusApi;

public class signReloadUtils {

	@SuppressWarnings("unused")
	public static void signReload() {

		for (Chunk chunk : Bukkit.getWorld("world").getLoadedChunks()) {
			for (BlockState bs : chunk.getTileEntities()) {
				if (bs instanceof Sign) {
					Sign sign = (Sign) bs.getBlock().getState();
					if (sign.getLine(0).equalsIgnoreCase("[Serveur]")) {
						String srvType;
						String srvPort;
						if (sign.getLine(1).contains("_")) {
							String[] nameANDport;
							nameANDport = sign.getLine(1).split("_");
							srvType = nameANDport[0];
							srvPort = nameANDport[1];
						} else {
							continue;
						}
						int srvPortInt = Integer.parseInt(srvPort);
						String state = MainLectusApi.getInstance().getSql().getState(srvPortInt);
						if (state.equalsIgnoreCase("WAITING")) {
							sign.setLine(2, ChatColor.GREEN + "En attente");
							sign.update();
						} else if (state.equalsIgnoreCase("STARTING") || state.equalsIgnoreCase("RUNNING")) {
							sign.setLine(2, ChatColor.RED + "En cour");
							sign.update();
						} else if (state.equalsIgnoreCase("FINISH")) {
							sign.setLine(2, ChatColor.DARK_RED + "Redemarre");
							sign.update();
						} else {
							sign.setLine(2, ChatColor.DARK_RED + "ERREUR");
							sign.update();
						}
					} else {
						continue;
					}

				}
			}
		}

	}

}
