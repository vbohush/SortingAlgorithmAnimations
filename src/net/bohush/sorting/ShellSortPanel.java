package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class ShellSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int greenColumn = -1;
	
	public ShellSortPanel(String name, int sleepTime, int width, int height) {
		super(name, sleepTime, width, height);
	}

	@Override
	public void reset() {
		redColumn = -1;
		blueColumn = -1;
		greenColumn = -1;		
	}
	
	@Override
	public void run() {
		try {
			
			int increment = list.length / 2;
			while (increment > 0) {
				for (int i = increment; i < list.length; i++) {
					redColumn = i;
					int j = i;
					int temp = list[i];
					repaint();
					Thread.sleep(3 * sleepTime);
					while (j >= increment && list[j - increment] > temp) {
						blueColumn = j - increment;
						if(increment == 1) {
							greenColumn = blueColumn - 1;
						}
						repaint();
						Thread.sleep(4 * sleepTime);
						list[j] = list[j - increment];
						j = j - increment;
					}
					repaint();
					Thread.sleep(2 * sleepTime);
					list[j] = temp;
				}
				if (increment == 2) {
					increment = 1;
				} else {
					increment *= (5.0 / 11);
				}
				
			}
			redColumn = -1;
			blueColumn = -1;	
			greenColumn = size - 1;
		} catch (InterruptedException e) {
		}
		repaint();
	}

}
