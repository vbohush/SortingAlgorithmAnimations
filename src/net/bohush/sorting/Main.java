package net.bohush.sorting;

import javax.swing.*;

import java.awt.*;

public class Main extends JApplet {

	private static final long serialVersionUID = 1L;
	private SortPanel selectionSortPanel = new SortPanel("Selection Sort");
	private SortPanel insertionSortPanel = new SortPanel("Insertion Sort");
	private SortPanel bubbleSortPanel = new SortPanel("Bubble Sort");
	private SortPanel mergeSortPanel = new SortPanel("Merge Sort");
	private SortPanel quickSortPanel = new SortPanel("Quick Sort");
	private SortPanel heapSortPanel = new SortPanel("Heap Sort");
	

	public Main() {
		setLayout(new  GridLayout(2, 3, 0, 0));
		add(selectionSortPanel);
		add(insertionSortPanel);
		add(bubbleSortPanel);
		add(mergeSortPanel);
		add(quickSortPanel);
		add(heapSortPanel);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Sorting Algorithm Animations");
		JApplet applet = new Main();
		frame.add(applet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}