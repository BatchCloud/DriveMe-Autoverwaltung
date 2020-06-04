package driveMe;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.event.MouseInputListener;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.cache.FileBasedLocalCache;
import org.jxmapviewer.input.CenterMapListener;
import org.jxmapviewer.input.PanKeyListener;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;


public final class MapPanel extends JXMapViewer{
	
	public MapPanel() {
	// Create a TileFactoryInfo for OpenStreetMap
		TileFactoryInfo info = new OSMTileFactoryInfo();
		DefaultTileFactory tileFactory = new DefaultTileFactory(info);
		// Setup local file cache
		File cacheDir = new File(System.getProperty("user.home") + File.separator + ".jxmapviewer2");
		
		tileFactory.setLocalCache(new FileBasedLocalCache(cacheDir, false));
	
		
		this.setTileFactory(tileFactory);
		GeoPosition frankfurt = new GeoPosition(48.885900, 8.732760);
		// Set the focus
		this.setZoom(4);
		this.setAddressLocation(frankfurt);
	
		
		MouseInputListener mia = new PanMouseInputListener(this);
		this.addMouseListener(mia);
		this.addMouseMotionListener(mia);
		this.addMouseListener(new CenterMapListener(this));
		this.addMouseWheelListener(new ZoomMouseWheelListenerCursor(this));
		this.addKeyListener(new PanKeyListener(this));

//	
//		SelectionAdapter sa = new SelectionAdapter(this);
//		SelectionPainter sp = new SelectionPainter(sa);
//		this.addMouseListener(sa);
//		this.addMouseMotionListener(sa);
//		this.setOverlayPainter(sp);
	}
	
	
}
