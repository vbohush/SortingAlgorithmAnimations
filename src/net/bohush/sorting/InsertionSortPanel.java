package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class InsertionSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int greenColumn = -1;
	
	public InsertionSortPanel(String name, int sleepTime, int width, int height) {
		super(name, sleepTime, width, height);
	}

	@Override
	public void reset() {
		redColumn = -1;
		greenColumn = -1;		
	}

	@Override
	public void run() {
		try {
			for (int i = 1; i < list.length; i++) {
				greenColumn = i;
				redColumn = greenColumn;
				int k;
				for (k = i - 1; k >= 0 && list[k] > list[k + 1]; k--) {
					Thread.sleep(3 * sleepTime);
					repaint();
					redColumn = k + 1;
					repaint();
					Thread.sleep(4 * sleepTime);
					int tmp = list[k + 1]; 
					list[k + 1] = list[k];
					list[k] = tmp;
				}
				redColumn = k + 1;
				repaint();
			}
			redColumn = -1;
		} catch (InterruptedException e) {
		}
		repaint();
	}

}
