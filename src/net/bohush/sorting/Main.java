package net.bohush.sorting;

import javax.swing.*;

import java.awt.*;

public class Main extends JApplet {

	private static final long serialVersionUID = 1L;
	private SortPanel[] sortPanels = new SortPanel[6];
	private int size = 100;
	

	public Main() {
		setLayout(new  GridLayout(2, 3, 0, 0));
		int[] list = new int[size];
		for (int i = 0; i < list.length; i++) {
			list[i] = 1 + (int)(Math.random() * size);
		}
		sortPanels[0] = new SelectionSortPanel(" Selection Sort ", list, 2);
		sortPanels[1] = new InsertionSortPanel(" Insertion Sort ", list, 2);
		/*sortPanels[2] = new SortPanel(" Bubble Sort ", list);
		sortPanels[3] = new SortPanel(" Merge Sort ", list);
		sortPanels[4] = new SortPanel(" Quick Sort ", list);
		sortPanels[5] = new SortPanel(" Heap Sort ", list);*/
		
		for (int i = 0; i < sortPanels.length; i++) {
			if(sortPanels[i] != null){
				add(sortPanels[i]);	
				
			}
		}

	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Sorting Algorithm Animations");
		JApplet applet = new Main();
		frame.add(applet);
		//frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}