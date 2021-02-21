package model;

interface SimpleCollection<E> {
    /*
     * Read in a data file and create a collection from the data
     */
    void readAndCreate();

    /*
     * Save the collection to the datafile
     */
    void save();

    /*
     * Add an element to the collection
     */
    void add(E e);

    /*
     * Remove an element from the collection
     */
    void remove(E e);

    /*
     * Display the contents of the collection
     */
    void showCollection();
}
