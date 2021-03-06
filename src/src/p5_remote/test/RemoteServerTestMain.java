package p5_remote.test;

import p5_remote.RemoteServer;
import processing.core.PApplet;
import processing.core.PImage;

public class RemoteServerTestMain extends PApplet {
	private static final long serialVersionUID = 4258434090200713047L;

	RemoteServer server;

	public void setup() {
		size(320, 240);
		server = new RemoteServer(this, 12345);
		server.setTimeout(1000); // ms
		server.start();
	}

	public void draw() {
		background(128, 128, 128);
		
		PImage img = server.getPImage("test_name");
		if (img != null) {
			image(img, 0, 0, width, height);
			
			// issue 1391 : https://github.com/processing/processing/issues/1391
			g.removeCache(img); 
		}
		
		text("fps=" + server.getFps("test_name"), 10, 20);
		text("bps=" + server.getBpsStr("test_name"), 10, 40);
		text("last_update_time=" + server.getLastUpdateTime("test_name"), 10, 60);
		text("is_active=" + server.isActive("test_name"), 10, 80);
		text("active_connection_num=" + server.getActiveConnectionNum(), 10, 100);
		text("average_fps=" + server.getAverageFps(), 10, 120);
		text("total_bps=" + server.getTotalBpsStr(), 10, 140);
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "p5_remote.test.RemoteServerTestMain" });
	}
}
