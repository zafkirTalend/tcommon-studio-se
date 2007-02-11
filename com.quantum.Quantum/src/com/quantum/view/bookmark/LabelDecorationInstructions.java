package com.quantum.view.bookmark;

/**
 * <p>Label decorations are specified for the entire view<p>.
 * 
 * @author BC
 */
public class LabelDecorationInstructions {
    private boolean sizeVisible = false;
    private boolean databaseDataVisible = false;
    /**
     * @return
     */
    public boolean isSizeVisible() {
        return sizeVisible;
    }

    /**
     * @param b
     */
    public void setSizeVisible(boolean b) {
        sizeVisible = b;
    }

    /**
     * @return
     */
    public boolean isDatabaseDataVisible() {
        return databaseDataVisible;
    }

    /**
     * @param b
     */
    public void setDatabaseDataVisible(boolean b) {
        databaseDataVisible = b;
    }

}
