package vlcj.tutorial2;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import uk.co.caprica.vlcj.component.EmbeddedMediaPlayerComponent;

public class Tutorial2B {

	private final EmbeddedMediaPlayerComponent mediaPlayerComponent;

	public static void main(final String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new Tutorial2B(args);
			}
		});
	}

	private Tutorial2B(String[] args) {
		JFrame frame = new JFrame("vlcj Tutorial");

		mediaPlayerComponent = new EmbeddedMediaPlayerComponent();

		frame.setContentPane(mediaPlayerComponent);

		frame.setLocation(100, 100);
		frame.setSize(1050, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		String filePath = args.length == 0 ? "data/test2.mp4" : args[0];
		
		//filePath = "rtp://@230.0.0.1:5555";
		mediaPlayerComponent.getMediaPlayer().playMedia(filePath);
	}
}