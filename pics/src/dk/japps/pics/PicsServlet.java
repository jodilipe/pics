package dk.japps.pics;

import java.util.logging.*;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class PicsServlet extends HttpServlet {
	private Logger logger = Logger.getLogger(PicsServlet.class.getName()); 
	
	public PicsServlet() {
		long start = System.currentTimeMillis();
		initializeLogging();
		new Thread(new Runnable() {
			@Override
			public void run() {
				syncPictures();
			}
		}).start();
		logger.log(Level.FINE, "application initialized in " + (System.currentTimeMillis()-start) + " ms");
	}

	private void initializeLogging() {
    try {
//			Handler fileHandler = new FileHandler("./pics.log");
//			fileHandler.setLevel(Level.ALL);
//			fileHandler.setFormatter(new SimpleFormatter());
//			Logger.getLogger("dk.japps").addHandler(fileHandler);
			
			Handler consoleHandler = new ConsoleHandler();
			consoleHandler.setLevel(Level.ALL);
			consoleHandler.setFormatter(new SimpleFormatter());
			Logger.getLogger("dk.japps").addHandler(consoleHandler);
			
			Logger.getLogger("dk.japps").setLevel(Level.ALL);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private void syncPictures() {
		long start = System.currentTimeMillis();
		new PicsLogic().syncPictureCopies();
		logger.log(Level.FINE, "pictures synchronized in " + (System.currentTimeMillis()-start) + " ms");
	}
}
