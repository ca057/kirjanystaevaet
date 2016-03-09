package appl.enums;

/**
 * Enums to define how the Database should be searched.
 * ALL means that all books, also "deleted" book (Stock = -1) should be returned
 * SELL where Stock >= 0, returns all books that are sold
 * AVAILABLE where Stock > 0, only books that are available are returned
 * @author Madeleine
 *
 */
public enum SearchMode {
	ALL, SELL, AVAILABLE;

}
