package myjavafx;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;

/**
 * TableView improved.
 * @author Johan
 * @param <T> TableView's items type
 * @see javafx.scene.control.TableView
 */
public class MyTableView<T> extends TableView<T> {
    /**
     * Creates a default TableView control with no content.
     */
    public MyTableView() {
        super();
    }
    
    /**
     * Add the given item to the TableView.
     * @param item item to add
     * @throws UnsupportedOperationException if the add operation is not supported by this TableView
     * @throws IllegalArgumentException if some property of this element prevents 
     * it from being added to this TableView
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this TableView
     * @throws NullPointerException if the specified element is null and this TableView does not permit null elements
     */
    public void add(T item) {
        getItems().add(item);
    }
    
    /**
     * Inserts the specified element at the specified position in this TableView 
     * (optional operation). 
     * Shifts the element currently at that position (if any) and any subsequent 
     * elements to the right (adds one to their indices).
     * @param index index at which add the item
     * @param item item to add
     * @UnsupportedOperationException if the add operation is not supported by this TableView
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of the specified element 
     * prevents it from being added to this TableView
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this TableView
     * @throws NullPointerException if the specified element is null and this 
     * TableView does not permit null elements
     */
    public void add(int index, T item) {
        getItems().add(index, item);
    }
    
    /**
     * Appends all of the elements in the specified collection to the end of 
     * this TableView, in the order that they are returned by the specified 
     * collection's iterator (optional operation). 
     * The behavior of this operation is undefined if the specified collection 
     * is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this TableView, and it's nonempty.)
     * @param collection collection containing elements to be added to this TableView
     * @return <tt>true</tt> if this collection changed as a result of the calltrue 
     * if this TableView changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this TableView
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this TableView
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this TableView
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this TableView does not permit null elements, 
     * or if the specified collection is null
     */
    public boolean addAll(Collection<? extends T> collection) {
        return getItems().addAll(collection);
    }
    
    /**
     * A convenient method for var-arg adding of elements.
     * @param elements the elements to add 
     * @return <tt>true</tt> (as specified by Collection.add(E))
     */
    public boolean addAll(T... elements) {
        return getItems().addAll(elements);
    }
    
    /**
     * Inserts all of the elements in the specified collection into this TableView at 
     * the specified position (optional operation). Shifts the element currently 
     * at that position (if any) and any subsequent elements to the right (increases their indices). 
     * The new elements will appear in this TableView in the order that they are returned 
     * by the specified collection's iterator. The behavior of this operation is 
     * undefined if the specified collection is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this TableView, and it's nonempty.)
     * @param index index at which to insert the first element from the specified collection
     * @param collection collection containing elements to be added to this TableView
     * @return <tt>true</tt> if this TableView changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this TableView
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this TableView
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this TableView
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this TableView does not permit null elements, 
     * or if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends T> collection) {
        return getItems().addAll(index, collection);
    }
    
    /**
     * Returns true if this TableView contains the specified element. 
     * More formally, returns true if and only if this TableView contains at least 
     * one element e such that (item==null ? e==null : item.equals(e)).
     * @param item item whose presence in this TableView is to be tested
     * @return <tt>true</tt> if this collection contains the specified element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this TableView (optional)
     * @throws NullPointerException if the specified element is null and this 
     * TableView does not permit null elements (optional)
     */
    public boolean contains(T item) {
        return getItems().contains(item);
    }
    
    /**
     * Returns <tt>true</tt> if this TableView contains all of the elements of the specified collection.
     * @param collection collection to be checked for containment in this TableView
     * @return <tt>true</tt> if this collection contains all of the elements in 
     * the specified collection
     * @throws ClassCastException if the types of one or more elements in the 
     * specified collection are incompatible with this TableView (optional)
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this TableView does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean containsAll(Collection<?> collection) {
        return getItems().containsAll(collection);
    }
    
    /**
     * Creates a FilteredList wrapper of this TableView using the specified predicate.
     * @param predicate the predicate to use 
     * @return new FilteredList
     * @since JavaFX 8.0
     */
    public FilteredList<T> filtered(Predicate<T> predicate) {
        return getItems().filtered(predicate);
    }
    
    /**
     * Performs the given action for each element of the Iterable until all 
     * elements have been processed or the action throws an exception. 
     * Unless otherwise specified by the implementing class, actions are 
     * performed in the order of iteration (if an iteration order is specified). 
     * Exceptions thrown by the action are relayed to the caller.
     * @param action The action to be performed for each element
     * @throws NullPointerException - if the specified action is null
     * @since 1.8
     */
    public void forEach(Consumer<? super T> action) {
        getItems().forEach(action);
    }
    
    /**
     * Returns the element at the specified position in this TableView.
     * @param index index of the element to return 
     * @return the element at the specified position in this TableView
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public T get(int index) {
        return getItems().get(index);
    }

    /**
     * Returns the index of the first occurrence of the specified element in 
     * this TableView, or -1 if this TableView does not contain the element. 
     * More formally, returns the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))), or -1 if there is no such index.
     * @param item element to search for
     * @return the index of the first occurrence of the specified element in this TableView, 
     * or -1 if this TableView does not contain the element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this TableView (optional)
     * @throws NullPointerException if the specified element is null and this 
     * TableView does not permit null elements (optional)
     */
    public int indexOf(T item) {
        return getItems().indexOf(item);
    }
    
    /**
     * Returns true if this TableView contains no elements.
     * @return <tt>true</tt> if this TableView contains no elements
     */
    public boolean isEmpty() {
        return getItems().isEmpty();
    }
    
    /**
     * Returns an iterator over the elements in this TableView in proper sequence.
     * @return an iterator over the elements in this TableView in proper sequence
     */
    public Iterator<T> iterator() {
        return getItems().iterator();
    }
    
    /**
     * Returns a possibly parallel Stream with this collection as its source. 
     * It is allowable for this method to return a sequential stream.
     * This method should be overridden when the spliterator() method cannot 
     * return a spliterator that is IMMUTABLE, CONCURRENT, or late-binding. 
     * (See spliterator() for details.)
     * @return a possibly parallel Stream over the elements in this collection
     */
    public Stream<T> parallelStream() {
        return getItems().parallelStream();
    }
    
    /**
     * Removes the first occurrence of the specified element from this TableView, 
     * if it is present (optional operation). 
     * If this TableView does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))) 
     * (if such an element exists). 
     * Returns true if this TableView contained the specified element 
     * (or equivalently, if this TableView changed as a result of the call).
     * @param item element to be removed from this TableView, if present
     * @return true if an element was removed as a result of this call
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this TableView
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this TableView (optional)
     * @throws NullPointerException if the specified element is null and 
     * this TableView does not permit null elements (optional)
     */
    public boolean remove(T item) {
        return getItems().remove(item);
    }
    
    /**
     * Removes the element at the specified position in this TableView (optional operation). 
     * Shifts any subsequent elements to the left (subtracts one from their indices). 
     * Returns the element that was removed from the TableView.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the remove operation is not supported by this TableView
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public T remove(int index) {
        return getItems().remove(index);
    }
    
    /**
     * Basically a shortcut to sublist(from, to).clear(). 
     * As this is a common operation, ObservableList has this method for convenient usage.
     * @param from the start of the range to remove (inclusive)
     * @param to the end of the range to remove (exclusive)
     */
    public void remove(int from, int to) {
        getItems().remove(from, to);
    }
    
    /**
     * Removes from this TableView all of its elements that are contained in the 
     * specified collection (optional operation).
     * @param collection collection containing elements to be removed from this TableView
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    public boolean removeAll(Collection<?> collection) {
        return getItems().removeAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of removaAll method.
     * @param elements the elements to be removed
     * @return <tt>true</tt> if TableView changed as a result of this call
     */
    public boolean removeAll(T... elements) {
        return getItems().removeAll(elements);
    }
    
    /**
     * Removes all of the elements of this collection that satisfy the given predicate. 
     * Errors or runtime exceptions thrown during iteration or by the predicate 
     * are relayed to the caller.
     * @param filter a predicate which returns true for elements to be removed
     * @return <tt>true</tt> if any elements were removed
     * @throws UnsupportedOperationException if elements cannot be removed from this collection. 
     * Implementations may throw this exception if a matching element cannot be removed or if, 
     * in general, removal is not supported.
     * @throws NullPointerException if the specified filter is null
     * @since 1.8
     */
    public boolean removeIf(Predicate<? super T> filter) {
        return getItems().removeIf(filter);
    }
    
    /**
     * Replaces each element of this TableView with the result of applying the 
     * operator to that element. 
     * Errors or runtime exceptions thrown by the operator are relayed to the caller.
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException if this TableView is unmodifiable. 
     * Implementations may throw this exception if an element cannot be replaced 
     * or if, in general, modification is not supported
     * @throws NullPointerException if the specified operator is null or if the 
     * operator result is a null value and this TableView does not permit null elements 
     * (optional) 
     * @since 1.8
     */
    public void replaceAll(UnaryOperator<T> operator) {
        getItems().replaceAll(operator);
    }
    
    /**
     * Retains only the elements in this TableView that are contained in the 
     * specified collection (optional operation). 
     * In other words, removes from this TableView all of its elements that are not 
     * contained in the specified collection.
     * @param collection collection containing elements to be retained in this TableView
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the retainAll operation is not 
     * supported by this TableView
     * @throws ClassCastException if the class of an element of this TableView is 
     * incompatible with the specified collection (optional)
     * @throws NullPointerException if this TableView contains a null element and the 
     * specified collection does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean retainAll(Collection<?> collection) {
        return getItems().retainAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of retain method.
     * @param elements the elements to be retained 
     * @return <tt>true</tt> if TableView changed as a result of this call
     */
    public boolean retainAll(T... elements) {
        return getItems().retainAll(elements);
    }
    
    /**
     * Replaces the element at the specified position in this TableView with the 
     * specified element (optional operation).
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the set operation is not supported by this TableView
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @throws IllegalArgumentException if some property of the specified 
     * element prevents it from being added to this TableView
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this TableView
     * @throws NullPointerException if the specified element is null and this 
     * TableView does not permit null elements
     */
    public T set(int index, T element) {
        return getItems().set(index, element);
    }
    
    /**
     * Clears the ObservableList and add all elements from the collection.
     * @param collection the collection with elements that will be added to this TableView
     * @return <tt>true</tt> (as specified by Collection.add(T))
     */
    public boolean setAll(Collection<? extends T> collection) {
        return getItems().setAll(collection);
    }
    
    /**
     * Clears the ObservableList and add all the elements passed as var-args.
     * @param elements the elements to set
     * @return <tt>true</tt> (as specified by Collection.add(E))
     * @throws NullPointerException if the specified arguments contain one or more null elements
     */
    public boolean setAll(T... elements) {
        return getItems().setAll(elements);
    }
    
    /**
     * Returns the number of elements in this TableView. 
     * If this TableView contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this TableView
     */
    public int size() {
        return getItems().size();
    }
    
    /**
     * Sorts this TableView according to the order induced by the specified Comparator.
     * All elements in this TableView must be mutually comparable using the specified 
     * comparator (that is, c.compare(e1, e2) must not throw a ClassCastException 
     * for any elements e1 and e2 in the TableView).
     * If the specified comparator is null then all elements in this TableView must implement 
     * the Comparable interface and the elements' natural ordering should be used.
     * This TableView must be modifiable, but need not be resizable.
     * @param comparator the Comparator used to compare TableView elements. 
     * A null value indicates that the elements' natural ordering should be used
     * @throws UnsupportedOperationException if the TableView's list-iterator does 
     * not support the set operation
     * @throws IllegalArgumentException (optional) if the comparator is found 
     * to violate the Comparator contract
     * @throws ClassCastException if the TableView contains elements that are not 
     * mutually comparable using the specified comparator
     * @since 1.8
     */
    public void sort(Comparator<? super T> comparator) {
        getItems().sort(comparator);
    }
    
    /**
     * Creates a SortedList wrapper of this TableView with the natural ordering.
     * @return new SortedList
     * @since JavaFX 8.0
     */
    public SortedList<T> sorted() {
        return getItems().sorted();
    }
    
    /**
     * Creates a SortedList wrapper of this TableView using the specified comparator.
     * @param comparator the comparator to use or null for unordered TableView
     * @return new SortedList
     * @since JavaFX 8.0
     */
    public SortedList<T> sorted(Comparator<T> comparator) {
        return getItems().sorted(comparator);
    }
    
    /**
     * Returns a sequential Stream with this collection as its source.
     * This method should be overridden when the spliterator() method cannot 
     * return a spliterator that is IMMUTABLE, CONCURRENT, or late-binding. 
     * (See spliterator() for details.)
     * @return a sequential Stream over the elements in this TableView
     * @since 1.8
     */
    public Stream<T> stream() {
        return getItems().stream();
    }
    
    /**
     * Returns an array containing all of the elements in this TableView in proper 
     * sequence (from first to last element).
     * The returned array will be "safe" in that no references to it are 
     * maintained by this TableView. 
     * (In other words, this method must allocate a new array even if this TableView is backed by an array). 
     * The caller is thus free to modify the returned array.
     * This method acts as bridge between array-based and collection-based APIs.
     * @return an array containing all of the elements in this TableView in proper sequence
     */
    public T[] toArray() {
        return (T[])getItems().toArray();
    }
    
    /**
     * Returns an array containing all of the elements in this TableView in proper 
     * sequence (from first to last element); 
     * the runtime type of the returned array is that of the specified array. 
     * If the TableView fits in the specified array, it is returned therein. 
     * Otherwise, a new array is allocated with the runtime type of the specified 
     * array and the size of this TableView.
     * @param dest the array into which the elements of this TableView are to be stored, if it 
     * is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collectionan array 
     * containing the elements of this TableView
     * @throws ArrayStoreException if the runtime type of the specified array is 
     * not a supertype of the runtime type of every element in this TableView
     * @throws NullPointerException if the specified array is null
     */
    public T[] toArray(T[] dest) {
        return getItems().toArray(dest);
    }
    
    /**
     * Returns the currently selected object (which resides in the selected index
     * position).If there are multiple items selected, this will return the 
     * object contained at the index returned by getSelectedIndex() (which is 
     * always the index to the most recently selected item).
     * 
     * <p>Note that the returned value is a snapshot in time - if you wish to
     * observe the selection model for changes to the selected item, you can
     * add a ChangeListener as such:
     *
     * <pre><code>
     * SelectionModel sm = ...;
     * InvalidationListener listener = ...;
     * sm.selectedItemProperty().addListener(listener);
     * </code></pre>
     * @return the currently selected item
     */
    public final T getSelectedItem() {
        return getSelectionModel().getSelectedItem();
    }
    
    /**
     * Returns the integer value indicating the currently selected index in this model. 
     * If there are multiple items selected, this will return the most recent selection made.
     * @return the currently selected index
     */
    public int getSelectedIndex() {
        return getSelectionModel().getSelectedIndex();
    }
    
    /**
     * Select the given index.
     * @param index index to select
     */
    public void setSelectedIndex(int index) {
        getSelectionModel().select(index);
    }
    
    /**
     * Select the given item.
     * @param item item to select
     */
    public void setSelectedItem(T item) {
        getSelectionModel().select(item);
    }
    
    /**
     * Clears all selection, and then selects the cell at the given row/column 
     * intersection.
     * @param row row to select
     * @param column column to select
     */
    public void clearAndSelect(int row, TableColumn<T,?> column) {
        getSelectionModel().clearAndSelect(0, column);
    }
    
    /**
     * Clears all selection, and then selects the cell at the given row/column intersection.
     * @param row row to select
     * @param column column to select
     */
    public void clearAndSelect(int row, TableColumnBase<T,?> column) {
        getSelectionModel().clearAndSelect(0, column);
    }
    
    /**
     * Removes selection from the specified row/column position (in view indexes). 
     * If this particular cell (or row if the column value is -1) is not selected, nothing happens.
     * @param row row from which remove selection
     * @param column column from which remove selection
     */
    public void clearSelection(int row, TableColumn<T,?> column) {
        getSelectionModel().clearSelection(0, column);
    }
    
    /**
     * Removes selection from the specified row/column position (in view indexes). 
     * If this particular cell (or row if the column value is -1) is not selected, nothing happens.
     * @param row row from which remove selection
     * @param column column from which remove selection
     */
    public void clearSelection(int row, TableColumnBase<T,?> column) {
        getSelectionModel().clearSelection(0, column);
    }
    
    /**
     * Focus the given index.
     * @param index index to focus
     */
    public void focus(int index) {
        getFocusModel().focus(index);
    }
    
    /**
     * Return the focused index.
     * @return the focused index
     */
    public int getFocusedIndex() {
        return getSelectionModel().getFocusedIndex();
    }
    
    /**
     * A read-only ObservableList representing the currently selected cells in this TableView. 
     * Rather than directly modify this list, please use the other methods provided in the TableViewSelectionModel.
     * @return a read-only ObservableList representing the currently selected cells in this TableView
     */
    public ObservableList<TablePosition> getSelectedCells() {
        return getSelectionModel().getSelectedCells();
    }
    
    /**
     * This method is available to test whether there are any selected indices/items. 
     * It will return true if there are no selected items, and false if there are.
     * @return <tt>true</tt> if there are no selected items, and <tt>false</tt> if there are
     */
    public boolean isSelectionModelEmpty() {
        return getSelectionModel().isEmpty();
    }
    
    /**
     * Convenience method to inform if the given index is currently selected in this SelectionModel. Is functionally equivalent to calling getSelectedIndices().contains(index).
     * @param index the index to check as to whether it is currently selected or not. 
     * @return <tt>true</tt> if the given index is selected, <tt>false</tt> otherwise.
     */
    public boolean isSelected(int index) {
        return getSelectionModel().isSelected(index);
    }
    
    /**
     * This method will attempt to select the index that contains the given object. 
     * It will iterate through the underlying data model until it finds an item 
     * whose value is equal to the given object. 
     * At this point it will stop iterating - this means that this method will not select multiple indices.
     * @param obj The object to attempt to select in the underlying data model.
     */
    public void select(T obj) {
        getSelectionModel().select(obj); 
    }
    
    /**
     * This will select the given index in the selection model, assuming the index 
     * is within the valid range (i.e. greater than or equal to zero, and less than 
     * the total number of items in the underlying data model).
     * If there is already one or more indices selected in this model, calling this method 
     * will not clear these selections - to do so it is necessary to first call clearSelection().
     * If the index is already selected, it will not be selected again, or unselected. 
     * However, if multiple selection is implemented, then calling select on an 
     * already selected index will have the effect of making the index the new 
     * selected index (as returned by getSelectedIndex().
     * @param index - The position of the item to select in the selection model.
     */
    public void select(int index) {
        getSelectionModel().select(index); 
    }
    
    /**
     * Selects the cell directly above the currently selected cell.
     */
    public void selectAboveCell() {
        getSelectionModel().selectAboveCell();
    }
    
    /**
     * Convenience method to select all available indices.
     */
    public void selectAll() {
        getSelectionModel().selectAll();
    }
    
    /**
     * Selects the cell directly below the currently selected cell.
     */
    public void selectBelowCell() {
        getSelectionModel().selectBelowCell();
    }
    
    /**
     * This method will attempt to select the first index in the control. 
     * If clearSelection is not called first, this method will have the result 
     * of selecting the first index, whilst retaining the selection of any 
     * other currently selected indices.
     * If the first index is already selected, calling this method will have no result, 
     * and no selection event will take place.
     */
    public void selectFirst() {
        getSelectionModel().selectFirst();
    }
    
    /**
     * Select the first item starting with the given String ignoring case, 
     * this method is based on the toString() method.
     * No effect if there is no item matching.
     * @param prefix the prefix of the item to select
     */
    public void selectFirstStartingWith(String prefix) {
        for (int i=0; i<size(); i++) {
            if (get(i).toString().toLowerCase().startsWith(prefix.toLowerCase())
             || get(i).toString().toUpperCase().startsWith(prefix.toUpperCase())) {
                select(i);
                scrollTo(i);
                return;
            }
        }
    }
    
    /**
     * Select the first item starting with the given String ignoring case, 
     * this method is based on the toString() method.
     * No effect if there is no item matching.
     * @param prefix the prefix of the item to select
     */
    public void selectNextStartingWith(String prefix) {
        for (int i=getSelectedIndex()+1; i<size(); i++) {
            if (get(i).toString().toLowerCase().startsWith(prefix.toLowerCase())
             || get(i).toString().toUpperCase().startsWith(prefix.toUpperCase())) {
                select(i);
                scrollTo(i);
                return;
            }
        }
        for (int i=0; i<getSelectedIndex()+1; i++) {
            if (get(i).toString().toLowerCase().startsWith(prefix.toLowerCase())
             || get(i).toString().toUpperCase().startsWith(prefix.toUpperCase())) {
                select(i);
                scrollTo(i);
                return;
            }
        }
    }
    
    /**
     * This method allows for one or more selections to be set at the same time. 
     * It will ignore any value that is not within the valid range 
     * (i.e. greater than or equal to zero, and less than the total number of items 
     * in the underlying data model). Any duplication of indices will be ignored.
     * If there is already one or more indices selected in this model, calling this method 
     * will not clear these selections - to do so it is necessary to first call clearSelection.
     * The last valid value given will become the selected index / selected item.
     * @param row row to select
     * @param rows rows to select
     */
    public void selectIndices(int row, int... rows) {
        getSelectionModel().selectIndices(row, rows);
    }
    
    /**
     * This method will attempt to select the last index in the control. 
     * If clearSelection is not called first, this method will have the result 
     * of selecting the last index, whilst retaining the selection of any other 
     * currently selected indices.
     * If the last index is already selected, calling this method will have no result, 
     * and no selection event will take place.
     */
    public void selectLast() {
        getSelectionModel().selectLast();
    }
    
    /**
     * Selects the cell to the left of the currently selected cell.
     */
    public void selectLeftCell() {
        getSelectionModel().selectLeftCell();
    }
    
    /**
     * <p>This method will attempt to select the index directly after the current
     * focused index. If clearSelection is not called first, this method
     * will have the result of selecting the next index, whilst retaining
     * the selection of any other currently selected indices.</p>
     *
     * <p>Calling this method will only succeed if:</p>
     *
     * <ul>
     *   <li>There is currently a lead/focused index.
     *   <li>The lead/focus index is not the last index in the control.
     *   <li>The next index is not already selected.
     * </ul>
     *
     * <p>If any of these conditions is false, no selection event will take
     * place.</p>
     */
    public void selectNext() {
        getSelectionModel().selectNext();
    }
    
    /**
     * <p>This method will attempt to select the index directly before the current
     * focused index. If clearSelection is not called first, this method
     * will have the result of selecting the previous index, whilst retaining
     * the selection of any other currently selected indices.</p>
     *
     * <p>Calling this method will only succeed if:</p>
     *
     * <ul>
     *   <li>There is currently a lead/focused index.
     *   <li>The lead/focus index is not the first index in the control.
     *   <li>The previous index is not already selected.
     * </ul>
     *
     * <p>If any of these conditions is false, no selection event will take
     * place.</p>
     */
    public void selectPrevious() {
        getSelectionModel().selectPrevious();
    }
    
    /**
     * <p>Selects all indices from the given start index to the item before the
     * given end index. This means that the selection is inclusive of the start
     * index, and exclusive of the end index. This method will work regardless
     * of whether start < end or start > end: the only constant is that the
     * index before the given end index will become the selected index.
     *
     * <p>If there is already one or more indices selected in this model, calling
     * this method will <b>not</b> clear these selections - to do so it is
     * necessary to first call clearSelection.
     *
     * @param start The first index to select - this index will be selected.
     * @param end The last index of the selection - this index will not be selected.
     */
    public void selectRange(int start, int end) {
        getSelectionModel().selectRange(start, end);
    }
    
    /**
     * Selects the cells in the range (minRow, minColumn) to (maxRow, maxColumn), inclusive.
     * @param minRow minimum row to select
     * @param minColumn minimum column to select
     * @param maxRow maximum row to select
     * @param maxColumn maximum column to select
     */
    public void selectRange(int minRow, TableColumnBase<T,?> minColumn, int maxRow, TableColumnBase<T,?> maxColumn) {
        getSelectionModel().selectRange(minRow, minColumn, maxRow, maxColumn);
    }
    
    /**
     * Selects the cell to the right of the currently selected cell.
     */
    public void selectRightCell() {
        getSelectionModel().selectRightCell();
    }
    
    /**
     * Refers to the selected index property, which is used to indicate the 
     * currently selected index value in the selection model. 
     * The selected index is either -1, to represent that there is no selection, 
     * or an integer value that is within the range of the underlying data model size.
     * The selected index property is most commonly used when the selection model only 
     * allows single selection, but is equally applicable when in multiple selection mode. 
     * When in this mode, the selected index will always represent the last selection made.
     * @return the selected index property
     */
    public ReadOnlyIntegerProperty selectedIndexProperty() {
        return getSelectionModel().selectedIndexProperty();
    }
    
    /**
     * Refers to the selected item property, which is used to indicate the 
     * currently selected item in the selection model. 
     * The selected item is either null, to represent that there is no selection, 
     * or an Object that is retrieved from the underlying data model of the 
     * control the selection model is associated with.
     * The selected item property is most commonly used when the selection model is set 
     * to be single selection, but is equally applicable when in multiple selection mode. 
     * When in this mode, the selected item will always represent the last selection made.
     * @return the selected item property
     */
    public ReadOnlyObjectProperty<T> selectedItemProperty() {
        return getSelectionModel().selectedItemProperty();
    }

    /**
     * Add the given style to this ComboBox's style. This method have 
     * no effect if the ComboBox already have the given style.
     * @param style the new style for this ComboBox
     */
    public void addStyleClass(String style) {
        if (!getStyleClass().contains(style)) getStyleClass().add(style);
    }
    
    /**
     * Add all the given styles to this ComboBox's style. This method doesn't 
     * add duplicates styles.
     * @param stylesCollection the new styles for this ComboBox list
     */
    public void addAllStyleClass(Collection<? extends String> stylesCollection) {
        stylesCollection.forEach(this::addStyleClass);
    }
    
    /**
     * Add all the given styles to this ComboBox's styles. This method doesn't 
     * add duplicates styles.
     * @param styles the new styles for this ComboBox
     */
    public void addAllStyleClass(String... styles) {
        for (String style : styles) addStyleClass(style);
    }
    
    /**
     * Returns <tt>true</tt> if this ComboxBox's styles contains the specified element.
     * More formally, returns <tt>true</tt> if and only if this ComboxBox's styles contains
     * at least one style <tt>e</tt> such that
     * <tt>(style==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;style.equals(e))</tt>.
     *
     * @param style style to be tested
     * @return <tt>true</tt> if this ComboBox's styles contains the specified element
     */
    public boolean containsStyleClass(String style) {
        return getStyleClass().contains(style);
    }
    
    /**
     * Remove all the occurences of the given style to this ComboBox.
     * @param style the style to be remove
     */
    public void removeStyleClass(String style) {
        getStyleClass().removeAll(style);
    }
    
    /**
     * Remove all the occurences of the given styles to this ComboBox.
     * @param styles the styles to be remove
     */
    public void removeStylesClass(Collection<String> styles) {
        getStyleClass().removeAll(styles);
    }
    
    /**
     * Remove all the occurences of the given styles to this ComboBox.
     * @param styles the styles to be remove
     */
    public void removeStylesClass(String... styles) {
        getStyleClass().removeAll(styles);
    }
}
