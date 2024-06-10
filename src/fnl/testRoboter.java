package fnl;

import robocode.Robot;

public class testRoboter extends Robot {
	double höhe = getBattleFieldHeight();
	double breite = getBattleFieldWidth();
	boolean vorwärts = true;

	public void run() {
		ahead(1500);
		while (true) {
			if (getY() <= 50) {
				umdrehen();
			}
			if (getX() <= 50) {
				umdrehen();
			}
			for (int i = 0; i < 10; i++) {
				turnRadarRight(25);
			}
		}
	}

	private void umdrehen() {
		if (vorwärts) {
			back(1500);
			vorwärts = false;
		} else {
			ahead(1500);
			vorwärts = true;
		}
	}

//	public void onScannedRobot(ScannedRobotEvent e) {
//		double winkel = getRadarHeading() - getGunHeading();
//		if (winkel < 0) {
//			turnGunLeft(winkel * -1);
//		} else {
//			turnGunRight(winkel);
//		}
//		fire(1);
//	}

}
