package fnl;

import static robocode.util.Utils.normalRelativeAngleDegrees;

import robocode.AdvancedRobot;
import robocode.ScannedRobotEvent;;

public class FnlBot extends AdvancedRobot {
	boolean vorwaerts = true;
	boolean inderWand;

	public void run() {
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		setAdjustRadarForRobotTurn(true);
		if (getY() <= 50 || getX() <= 50 || getBattleFieldWidth() - getX() <= 50
				|| getBattleFieldHeight() - getY() <= 50) {
			inderWand = true;
		} else {
			inderWand = false;
		}
		setAhead(1500);
		setTurnRadarLeft(360);
		while (true) {
			if (getY() <= 50 || getX() <= 50 || getBattleFieldWidth() - getX() <= 50
					|| getBattleFieldHeight() - getY() <= 50) {
				if (inderWand == false) {
					umdrehen();
				}
				inderWand = true;
			}
			if (getY() > 50 && getX() > 50 && getBattleFieldWidth() - getX() > 50
					&& getBattleFieldHeight() - getY() > 50) {
				inderWand = false;
			}
			if (getRadarTurnRemaining() == 0.0) {
				setTurnRadarLeft(360);
			}
			execute();
		}
	}

	private void umdrehen() {
		if (vorwaerts) {
			setBack(1500);
			vorwaerts = false;
		} else {
			setAhead(1500);
			vorwaerts = true;
		}
	}

	public void onScannedRobot(ScannedRobotEvent e) {

		double winkelgegner = e.getBearing();
		setTurnRight(winkelgegner + 90);
		double absoluterWinkelGegner = getHeading() + e.getBearing();
		setTurnGunRight(normalRelativeAngleDegrees(absoluterWinkelGegner - getGunHeading()));
		setTurnRadarRight(normalRelativeAngleDegrees(absoluterWinkelGegner - getRadarHeading()));
//		System.out.println("Absolute Winkel: " + Math.abs(absoluterWinkelGegner));
		if (Math.abs(normalRelativeAngleDegrees(absoluterWinkelGegner - getGunHeading())) < 4) {
			setFire(1);
		}
		if (normalRelativeAngleDegrees(absoluterWinkelGegner - getRadarHeading()) == 0) {
			scan();
		}
	}

}
