package net.bohush.sorting;

import java.awt.Color;
import java.awt.Graphics;

public class CombSortPanel extends SortPanel {
	private static final long serialVersionUID = 1L;
	private int redColumn = -1;
	private int blueColumn = -1;
	private int greenColumn = -1;
	
	public CombSortPanel(String name, int sleepTime, int width, int height) {
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
			
		    int gap = list.length;
		    boolean swapped = true;
		    boolean sorted = true;
		    while (gap > 1 || swapped) {
		        if (gap > 1) {
		            gap = (int) (gap / 1.3);
		        }
		        swapped = false;
		        sorted = true;
		        for (int i = 0; i + gap < list.length; i++) {
		        	redColumn = i;
		        	blueColumn = i + gap;
					Thread.sleep(4 * sleepTime);
		            if (list[i] > list[i + gap]) {
		                int t = list[i];
		                list[i] = list[i + gap];
		                list[i + gap] = t;
		    			repaint();
		    			Thread.sleep(4 * sleepTime);
		                swapped = true;		                
		            }
		        	if((sorted) && (i > 0)) {
		        		if (list[i] > list[i - 1]) {
		        			greenColumn = i;
		        		} else {
			        		greenColumn = -1;
			        		sorted = false;	        			
		        		}
		        	}
		        	repaint();
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
