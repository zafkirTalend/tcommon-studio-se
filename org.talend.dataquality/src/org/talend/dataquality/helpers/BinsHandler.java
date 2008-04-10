// ============================================================================
//
// Copyright (C) 2006-2007 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.dataquality.helpers;

/**
 * DOC scorreia class global comment. Detailled comment
 */
public class BinsHandler {

    /**
     * Method "generateBins". The size of the returned array is nbBins -1. We consider that the first bin is made of the
     * values < min and the last bin is made of the values > max.
     * 
     * @param min the first value of the bin interval
     * @param max the last value of the bin interval
     * @param nbBins the number of bins
     * @return an ordered (ascending) array of value defining the intervals.
     */
    public static double[] generateBins(double min, double max, int nbBins) {
        int nbIntervals = nbBins - 1;
        double[] bins = new double[nbIntervals];
        double step = (max - min) / (nbIntervals - 1);
        for (int i = 0; i < nbIntervals; i++) {
            bins[i] = min + i * step;
        }
        return bins;
    }

    public static void main(String args[]) {
        double min = 33;
        double max = 100;
        int n = 6;
        double[] bins = BinsHandler.generateBins(min, max, n);
        for (double l : bins) {
            System.out.print("| " + l);
        }
    }
}
