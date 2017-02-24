package net.bohush.sorting;

import javafx.util.Pair;

import java.awt.*;

public class QuickSortPanel extends SortPanel {
    private static final long serialVersionUID = 1L;
    private int redColumn = -1;
    private int blueColumn = -1;
    private int cyanColumn = -1;
    private int greenColumn = -1;

    public QuickSortPanel(String name, int sleepTime, int width, int height) {
        super(name, sleepTime, width, height);
    }

    @Override
    public void reset() {
        redColumn = -1;
        blueColumn = -1;
        greenColumn = -1;
        cyanColumn = -1;
    }

    @Override
    public void run() {
        try {
            quicksort(0, list.length - 1);
        } catch (InterruptedException e) {
        }
        redColumn = -1;
        blueColumn = -1;
        cyanColumn = -1;
        greenColumn = size - 1;
        repaint();
    }

    private void quicksort(int low, int high) throws InterruptedException {
        int i = low;
        int j = high;
        Thread.sleep(sleepTime);
        repaint();
        int pivot = list[low + (high - low) / 2];
        redColumn = low + (high - low) / 2;

        while (i <= j) {
            while (list[i] < pivot) {
                i++;
                blueColumn = i;
                Thread.sleep(4 * sleepTime);
                repaint();
            }
            while (list[j] > pivot) {
                j--;
                cyanColumn = j;
                Thread.sleep(4 * sleepTime);
                repaint();
            }

            if (i <= j) {
                int temp = list[i];
                list[i] = list[j];
                list[j] = temp;
                if (i == redColumn) {
                    redColumn = j;
                } else if (j == redColumn) {
                    redColumn = i;
                }
                Thread.sleep(4 * sleepTime);
                repaint();
                i++;
                j--;
            }
        }

        if (low < j) {
            quicksort(low, j);
        }
        if (i < high) {
            quicksort(i, high);
        }
        if (low > greenColumn) {
            greenColumn = low;
            blueColumn = -1;
            cyanColumn = -1;
        }
        repaint();
    }

}
