package pgDev.bukkit.DisguiseCraft.listeners.attack;

import java.util.concurrent.ConcurrentLinkedQueue;

import pgDev.bukkit.DisguiseCraft.DisguiseCraft;

public class AttackProcessor implements Runnable {
	DisguiseCraft plugin;
	
	public ConcurrentLinkedQueue<PlayerAttack> queue = new ConcurrentLinkedQueue<PlayerAttack>();
	private int amount = 0;
	
	public AttackProcessor(DisguiseCraft plugin) {
		this.plugin = plugin;
	}
	
	public synchronized void incrementAmount() {
		amount++;
	}
	
	private synchronized int flushAmount() {
		int output = amount;
		amount = 0;
		return output;
	}

	@Override
	public void run() {
		int polls = flushAmount();
		for (int i=0; i < polls; i++) {
			PlayerAttack attack = queue.poll();
			attack.attacker().attack(attack.victim());
		}
	}
}
