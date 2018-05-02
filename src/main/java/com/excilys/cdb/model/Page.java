package main.java.com.excilys.cdb.model;

import java.util.List;

/**
 * @author Aurelien Denoize
 *
 */
public class Page<T> {

    private List<T> list;

    /**
     * Constructor with the given list.
     * @param list list of elements of the page
     */
    public Page(List<T> list) {

        this.list = list;
    }

    /**
     * Get the page.
     * @return the list
     */
    public List<T> getPage() {
        return list;
    }

    /**
     * Check if the list is empty.
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return list.isEmpty();
    }

}
