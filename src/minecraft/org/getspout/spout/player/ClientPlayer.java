package org.getspout.spout.player;

import java.util.HashMap;
import org.getspout.spout.client.SpoutClient;
import org.getspout.spout.gui.InGameHUD;
import org.getspout.spout.gui.InGameScreen;

import net.minecraft.client.Minecraft;
import net.minecraft.src.EntityPlayer;

public class ClientPlayer extends SpoutPlayer implements ActivePlayer{
	private RenderDistance min, max;
	private InGameScreen mainScreen = new InGameScreen();
	private HashMap<Integer, String> titles = new HashMap<Integer, String>();

	public ClientPlayer(EntityPlayer player) {
		super(player);
		min = RenderDistance.TINY;
		max = RenderDistance.FAR;
	}

	@Override
	public RenderDistance getMaximumView() {
		return max;
	}

	@Override
	public RenderDistance getMinimumView() {
		return min;
	}

	@Override
	public void setMaximumView(RenderDistance distance) {
		max = distance;
	}

	@Override
	public void setMinimumView(RenderDistance distance) {
		min = distance;
	}
	
	@Override
	public RenderDistance getCurrentView() {
		return RenderDistance.getRenderDistanceFromValue(Minecraft.theMinecraft.gameSettings.renderDistance);
	}
	
	@Override
	public RenderDistance getNextRenderDistance() {
		int next = getCurrentView().getValue() - 1;
		if (next < min.getValue()) {
			next = max.getValue();
		}
		return RenderDistance.getRenderDistanceFromValue(next);
	}
	
	@Override
	public InGameHUD getMainScreen() {
		return mainScreen;
	}

	@Override
	public void showAchievement(String title, String message, int id) {
		SpoutClient.getHandle().guiAchievement.queueNotification(title, message, id);
	}

	@Override
	public String getEntityTitle(int id) {
		return titles.get(id);
	}

	@Override
	public void setEntityTitle(int id, String title) {
		titles.put(id, title);
	}
	
	@Override
	public void resetEntityTitle(int id) {
		titles.remove(id);
	}

}
