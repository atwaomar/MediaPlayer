package fr.xxathyx.mediaplayer.map.util;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.map.MapView;

import fr.xxathyx.mediaplayer.util.MapUtil;
import net.minecraft.network.protocol.Packet;

/**
 * The v26_1 class implements {@link MapUtil}, it can only be defined once if
 * the server is running under this version, see {@link MapUtilVersion}.
 *  
 * @author  Xxathyx
 * @version 1.0.0
 * @since   2026-04-08
 */

public class v26_1 implements MapUtil {
	
	public v26_1() {}
	
	@Override
	public void update(Player player, int id, byte[] buffer) {
	    
	    org.bukkit.craftbukkit.entity.CraftPlayer craftPlayer = (org.bukkit.craftbukkit.entity.CraftPlayer) player;
	    
	    try {
	        Packet<?> packet = new net.minecraft.network.protocol.game.ClientboundMapItemDataPacket(
	                new net.minecraft.world.level.saveddata.maps.MapId(id),
	                (byte) 4,
	                false,
	                new ArrayList<>(),
	                new net.minecraft.world.level.saveddata.maps.MapItemSavedData.MapPatch(0, 0, 128, 128, buffer)
	        );
	        
	        Object connection = craftPlayer.getHandle().connection;
	        
	        java.lang.reflect.Method sendMethod = connection.getClass().getMethod("send", Packet.class);
	        sendMethod.invoke(connection, packet);
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public MapView getMapView(int id) {
		return Bukkit.getMap(id);
	}
	
	@Override
	public int getMapId(MapView mapView) {
		return mapView.getId();
	}
}