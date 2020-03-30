package ca.xamercier.signTeleport;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import ca.xamercier.signTeleport.event.EventsListeners;
import ca.xamercier.signTeleport.utils.signReloadUtils;

public class SignTeleport extends JavaPlugin {

	private static SignTeleport instance;

	public static SignTeleport getInstance() {
		return instance;
	}

	@SuppressWarnings("deprecation")
	public void onEnable() {
		super.onEnable();
		instance = this;
		Bukkit.getPluginManager().registerEvents(new EventsListeners(), this);
		Bukkit.getScheduler().runTaskTimer(this, new BukkitRunnable() {
			@Override
			public void run() {
				signReloadUtils.signReload();
			}
		}, 0, 40);
	}

	public void onDisable() {
		super.onDisable();
	}

}
