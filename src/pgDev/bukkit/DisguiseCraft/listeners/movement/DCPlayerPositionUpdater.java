package pgDev.bukkit.DisguiseCraft.listeners.movement;

import org.bukkit.entity.Player;

import pgDev.bukkit.DisguiseCraft.disguise.*;
import pgDev.bukkit.DisguiseCraft.DisguiseCraft;

public class DCPlayerPositionUpdater implements Runnable {
	final DisguiseCraft plugin;
	final Player player;
	final Disguise disguise;
	
	public DCPlayerPositionUpdater(DisguiseCraft plugin, Player player, Disguise disguise) {
		this.plugin = plugin;
		this.player = player;
		this.disguise = disguise;
	}

	@Override
	public void run() {
		if (!disguise.data.contains("nomove")) {
			plugin.sendMovement(player, null, player.getVelocity(), player.getLocation());
		}
	}
}
