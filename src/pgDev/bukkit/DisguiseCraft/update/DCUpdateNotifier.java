package pgDev.bukkit.DisguiseCraft.update;

import java.util.logging.Level;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;

public class DCUpdateNotifier  implements Runnable {
	final DisguiseCraft plugin;
	CommandSender toNotify;
	
	boolean notifyNone;
	
	public DCUpdateNotifier(final DisguiseCraft plugin, CommandSender player, boolean notifyNone) {
		this.plugin = plugin;
		this.toNotify = player;
		this.notifyNone = notifyNone;
	}
	
	public DCUpdateNotifier(final DisguiseCraft plugin, CommandSender player) {
		this(plugin, player, false);
	}
	
	@Override
	public void run() {
		String latestVersion = DCUpdateChecker.getLatestVersion();
		try {
	 		if (DCUpdateChecker.isUpToDate(DisguiseCraft.pdfFile.getVersion(), latestVersion)) {
	 	 		if (notifyNone) {
	 	 		// Check if player is still online
	 	 		if (toNotify instanceof Player && !((Player) toNotify).isOnline()) {
	 			DisguiseCraft.logger.log(Level.INFO, "Player " + ((Player) toNotify).getDisplayName() + " went offline before we could tell him that there are DisguiseCraft updates");
	 	 		} else {
	 	 		toNotify.sendMessage(ChatColor.BLUE + "There are no new updates");
					}
	 	 	 	}
	 		} else {
	 		// Check if player is still online
	 		if (toNotify instanceof Player && !((Player) toNotify).isOnline()) {
	 		DisguiseCraft.logger.log(Level.INFO, "Player " + ((Player) toNotify).getDisplayName() + " went offline before we could tell him that DisguiseCraft v" + latestVersion + " is now available");
				} else {
					// Out of date
					toNotify.sendMessage(ChatColor.BLUE + "There is a new update for DisguiseCraft available: " + latestVersion);
				}
			
			}
		} catch (NumberFormatException e) {
			DisguiseCraft.logger.log(Level.WARNING, "Could not parse version updates.");
		}
	}
}
