package myjavafx;

import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.scene.control.ComboBox;

/**
 * ComboBox improved.
 * @author Johan
 * @param <T> ComboBox's items type
 * @see javafx.scene.control.ComboBox
 */
public class MyComboBox<T> extends ComboBox<T> {
    /**
     * Creates a default ComboBox instance with an empty items list and default selection model.
     */
    public MyComboBox() {
        super();
    }
    
    /**
     * Add the given item to the ComboBox.
     * @param item item to add
     * @throws UnsupportedOperationException if the add operation is not supported by this ComboBox
     * @throws IllegalArgumentException if some property of this element prevents 
     * it from being added to this ComboBox
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ComboBox
     * @throws NullPointerException if the specified element is null and this ComboBox does not permit null elements
     */
    public void add(T item) {
        getItems().add(item);
    }
    
    /**
     * Inserts the specified element at the specified position in this ComboBox 
     * (optional operation). 
     * Shifts the element currently at that position (if any) and any subsequent 
     * elements to the right (adds one to their indices).
     * @param index index at which add the item
     * @param item item to add
     * @UnsupportedOperationException if the add operation is not supported by this ComboBox
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of the specified element 
     * prevents it from being added to this ComboBox
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ComboBox
     * @throws NullPointerException if the specified element is null and this 
     * ComboBox does not permit null elements
     */
    public void add(int index, T item) {
        getItems().add(index, item);
    }
    
    /**
     * Appends all of the elements in the specified collection to the end of 
     * this ComboBox, in the order that they are returned by the specified 
     * collection's iterator (optional operation). 
     * The behavior of this operation is undefined if the specified collection 
     * is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this ComboBox, and it's nonempty.)
     * @param collection collection containing elements to be added to this ComboBox
     * @return <tt>true</tt> if this collection changed as a result of the calltrue 
     * if this ComboBox changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this ComboBox
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this ComboBox
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this ComboBox
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ComboBox does not permit null elements, 
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
     * Inserts all of the elements in the specified collection into this ComboBox at 
     * the specified position (optional operation). Shifts the element currently 
     * at that position (if any) and any subsequent elements to the right (increases their indices). 
     * The new elements will appear in this ComboBox in the order that they are returned 
     * by the specified collection's iterator. The behavior of this operation is 
     * undefined if the specified collection is modified while the operation is in progress. 
     * (Note that this will occur if the specified collection is this ComboBox, and it's nonempty.)
     * @param index index at which to insert the first element from the specified collection
     * @param collection collection containing elements to be added to this ComboBox
     * @return <tt>true</tt> if this ComboBox changed as a result of the call
     * @throws UnsupportedOperationException if the addAll operation is not supported by this ComboBox
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index > size())
     * @throws IllegalArgumentException if some property of an element of the 
     * specified collection prevents it from being added to this ComboBox
     * @throws ClassCastException if the class of an element of the specified 
     * collection prevents it from being added to this ComboBox
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ComboBox does not permit null elements, 
     * or if the specified collection is null
     */
    public boolean addAll(int index, Collection<? extends T> collection) {
        return getItems().addAll(index, collection);
    }
    
    /**
     * Returns true if this ComboBox contains the specified element. 
     * More formally, returns true if and only if this ComboBox contains at least 
     * one element e such that (item==null ? e==null : item.equals(e)).
     * @param item item whose presence in this ComboBox is to be tested
     * @return <tt>true</tt> if this collection contains the specified element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ComboBox (optional)
     * @throws NullPointerException if the specified element is null and this 
     * ComboBox does not permit null elements (optional)
     */
    public boolean contains(T item) {
        return getItems().contains(item);
    }
    
    /**
     * Returns <tt>true</tt> if this ComboBox contains all of the elements of the specified collection.
     * @param collection collection to be checked for containment in this ComboBox
     * @return <tt>true</tt> if this collection contains all of the elements in 
     * the specified collection
     * @throws ClassCastException if the types of one or more elements in the 
     * specified collection are incompatible with this ComboBox (optional)
     * @throws NullPointerException if the specified collection contains one or 
     * more null elements and this ComboBox does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean containsAll(Collection<?> collection) {
        return getItems().containsAll(collection);
    }
    
    /**
     * Creates a FilteredList wrapper of this ComboBox using the specified predicate.
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
     * Returns the element at the specified position in this ComboBox.
     * @param index index of the element to return 
     * @return the element at the specified position in this ComboBox
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     */
    public T get(int index) {
        return getItems().get(index);
    }

    /**
     * Returns the index of the first occurrence of the specified element in 
     * this ComboBox, or -1 if this ComboBox does not contain the element. 
     * More formally, returns the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))), or -1 if there is no such index.
     * @param item element to search for
     * @return the index of the first occurrence of the specified element in this ComboBox, 
     * or -1 if this ComboBox does not contain the element
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ComboBox (optional)
     * @throws NullPointerException if the specified element is null and this 
     * ComboBox does not permit null elements (optional)
     */
    public int indexOf(T item) {
        return getItems().indexOf(item);
    }
    
    /**
     * Returns true if this ComboBox contains no elements.
     * @return <tt>true</tt> if this ComboBox contains no elements
     */
    public boolean isEmpty() {
        return getItems().isEmpty();
    }
    
    /**
     * Returns an iterator over the elements in this ComboBox in proper sequence.
     * @return an iterator over the elements in this ComboBox in proper sequence
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
     * Removes the first occurrence of the specified element from this ComboBox, 
     * if it is present (optional operation). 
     * If this ComboBox does not contain the element, it is unchanged. 
     * More formally, removes the element with the lowest index i such that 
     * (item==null ? get(i)==null : item.equals(get(i))) 
     * (if such an element exists). 
     * Returns true if this ComboBox contained the specified element 
     * (or equivalently, if this ComboBox changed as a result of the call).
     * @param item element to be removed from this ComboBox, if present
     * @return true if an element was removed as a result of this call
     * @throws UnsupportedOperationException if the remove operation is not 
     * supported by this ComboBox
     * @throws ClassCastException if the type of the specified element is 
     * incompatible with this ComboBox (optional)
     * @throws NullPointerException if the specified element is null and 
     * this ComboBox does not permit null elements (optional)
     */
    public boolean remove(T item) {
        return getItems().remove(item);
    }
    
    /**
     * Removes the element at the specified position in this ComboBox (optional operation). 
     * Shifts any subsequent elements to the left (subtracts one from their indices). 
     * Returns the element that was removed from the ComboBox.
     * @param index the index of the element to be removed
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the remove operation is not supported by this ComboBox
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
     * Removes from this ComboBox all of its elements that are contained in the 
     * specified collection (optional operation).
     * @param collection collection containing elements to be removed from this ComboBox
     * @return <tt>true</tt> if this collection changed as a result of the call
     */
    public boolean removeAll(Collection<?> collection) {
        return getItems().removeAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of removaAll method.
     * @param elements the elements to be removed
     * @return <tt>true</tt> if ComboBox changed as a result of this call
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
     * Replaces each element of this ComboBox with the result of applying the 
     * operator to that element. 
     * Errors or runtime exceptions thrown by the operator are relayed to the caller.
     * @param operator the operator to apply to each element
     * @throws UnsupportedOperationException if this ComboBox is unmodifiable. 
     * Implementations may throw this exception if an element cannot be replaced 
     * or if, in general, modification is not supported
     * @throws NullPointerException if the specified operator is null or if the 
     * operator result is a null value and this ComboBox does not permit null elements 
     * (optional) 
     * @since 1.8
     */
    public void replaceAll(UnaryOperator<T> operator) {
        getItems().replaceAll(operator);
    }
    
    /**
     * Retains only the elements in this ComboBox that are contained in the 
     * specified collection (optional operation). 
     * In other words, removes from this ComboBox all of its elements that are not 
     * contained in the specified collection.
     * @param collection collection containing elements to be retained in this ComboBox
     * @return <tt>true</tt> if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the retainAll operation is not 
     * supported by this ComboBox
     * @throws ClassCastException if the class of an element of this ComboBox is 
     * incompatible with the specified collection (optional)
     * @throws NullPointerException if this ComboBox contains a null element and the 
     * specified collection does not permit null elements (optional), 
     * or if the specified collection is null
     */
    public boolean retainAll(Collection<?> collection) {
        return getItems().retainAll(collection);
    }
    
    /**
     * A convenient method for var-arg usage of retain method.
     * @param elements the elements to be retained 
     * @return <tt>true</tt> if ComboBox changed as a result of this call
     */
    public boolean retainAll(T... elements) {
        return getItems().retainAll(elements);
    }
    
    /**
     * Replaces the element at the specified position in this ComboBox with the 
     * specified element (optional operation).
     * @param index index of the element to replace
     * @param element element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws UnsupportedOperationException if the set operation is not supported by this ComboBox
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 || index >= size())
     * @throws IllegalArgumentException if some property of the specified 
     * element prevents it from being added to this ComboBox
     * @throws ClassCastException if the class of the specified element prevents 
     * it from being added to this ComboBox
     * @throws NullPointerException if the specified element is null and this 
     * ComboBox does not permit null elements
     */
    public T set(int index, T element) {
        return getItems().set(index, element);
    }
    
    /**
     * Clears the ObservableList and add all elements from the collection.
     * @param collection the collection with elements that will be added to this ComboBox
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
     * Returns the number of elements in this ComboBox. 
     * If this ComboBox contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE.
     * @return the number of elements in this ComboBox
     */
    public int size() {
        return getItems().size();
    }
    
    /**
     * Sorts this ComboBox according to the order induced by the specified Comparator.
     * All elements in this ComboBox must be mutually comparable using the specified 
     * comparator (that is, c.compare(e1, e2) must not throw a ClassCastException 
     * for any elements e1 and e2 in the ComboBox).
     * If the specified comparator is null then all elements in this ComboBox must implement 
     * the Comparable interface and the elements' natural ordering should be used.
     * This ComboBox must be modifiable, but need not be resizable.
     * @param comparator the Comparator used to compare ComboBox elements. 
     * A null value indicates that the elements' natural ordering should be used
     * @throws UnsupportedOperationException if the ComboBox's list-iterator does 
     * not support the set operation
     * @throws IllegalArgumentException (optional) if the comparator is found 
     * to violate the Comparator contract
     * @throws ClassCastException if the ComboBox contains elements that are not 
     * mutually comparable using the specified comparator
     * @since 1.8
     */
    public void sort(Comparator<? super T> comparator) {
        getItems().sort(comparator);
    }
    
    /**
     * Creates a SortedList wrapper of this ComboBox with the natural ordering.
     * @return new SortedList
     * @since JavaFX 8.0
     */
    public SortedList<T> sorted() {
        return getItems().sorted();
    }
    
    /**
     * Creates a SortedList wrapper of this ComboBox using the specified comparator.
     * @param comparator the comparator to use or null for unordered ComboBox
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
     * @return a sequential Stream over the elements in this ComboBox
     * @since 1.8
     */
    public Stream<T> stream() {
        return getItems().stream();
    }
    
    /**
     * Returns an array containing all of the elements in this ComboBox in proper 
     * sequence (from first to last element).
     * The returned array will be "safe" in that no references to it are 
     * maintained by this ComboBox. 
     * (In other words, this method must allocate a new array even if this ComboBox is backed by an array). 
     * The caller is thus free to modify the returned array.
     * This method acts as bridge between array-based and collection-based APIs.
     * @return an array containing all of the elements in this ComboBox in proper sequence
     */
    public T[] toArray() {
        return (T[])getItems().toArray();
    }
    
    /**
     * Returns an array containing all of the elements in this ComboBox in proper 
     * sequence (from first to last element); 
     * the runtime type of the returned array is that of the specified array. 
     * If the ComboBox fits in the specified array, it is returned therein. 
     * Otherwise, a new array is allocated with the runtime type of the specified 
     * array and the size of this ComboBox.
     * @param dest the array into which the elements of this ComboBox are to be stored, if it 
     * is big enough; otherwise, a new array of the same runtime type is allocated for this purpose.
     * @return an array containing all of the elements in this collectionan array 
     * containing the elements of this ComboBox
     * @throws ArrayStoreException if the runtime type of the specified array is 
     * not a supertype of the runtime type of every element in this ComboBox
     * @throws NullPointerException if the specified array is null
     */
    public T[] toArray(T[] dest) {
        return getItems().toArray(dest);
    }
    
    /**
     * Returns the currently selected object (which resides in the selected index position). 
     * If there are multiple items selected, this will return the object contained 
     * at the index returned by getSelectedIndex()
     * (which is always the index to the most recently selected item).
     * @return the currently selected object
     */
    public T getSelectedItem() {
        return getSelectionModel().getSelectedItem();
    }
    
    /**
     * Returns the integer value indicating the currently selected index in this model. 
     * If there are multiple items selected, this will return the most recent selection made.
     * @return the integer value indicating the currently selected index
     */
    public int getSelectedIndex() {
        return getSelectionModel().getSelectedIndex();
    }
    
    /**
     * Selects the index for the first instance of given object in the underlying data model. 
     * Since the SingleSelectionModel can only support having a single index selected at a time, 
     * this also causes any previously selected index to be unselected.
     * @param item new item to be selected
     */
    public void setSelectedItem(T item) {
        this.getSelectionModel().select(item);
    }
    
    /**
     * Select the given index.
     * @param index index to select
     */
    public void setSelectedIndex(int index) {
        getSelectionModel().select(index);
    }
    
    /**
     * In the SingleSelectionModel, this method is functionally equivalent to 
     * calling select(index), as only one selection is allowed at a time.
     * @param index index to select
     */
    public void clearAndSelect(int index) {
        getSelectionModel().clearAndSelect(index);
    }
    
    /**
     * Clears the selection model of all selected indices.
     */
    public void clearSelection() {
       getSelectionModel().clearSelection();
    }
    
    /**
     * Clears the selection of the given index, if it is currently selected.
     * @param index the index to be unselect
     */
    public void clearSelection(int index) {
        getSelectionModel().clearSelection(index);
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
     * This method will return true if the given index is the currently selected 
     * index in the SingleSelectionModel.
     * @param index The index to check as to whether it is currently selected or not
     * @return <tt>true</tt> if the given index is selected, <tt>false</tt> otherwise.
     */
    public boolean isSelected(int index) {
        return getSelectionModel().isSelected(index);
    }
    
    /**
     * Selects the index for the first instance of given object in the underlying data model. 
     * Since the SingleSelectionModel can only support having a single index selected at a time, 
     * this also causes any previously selected index to be unselected.
     * @param item new item to be selected
     */
    public void select(T item) {
        getSelectionModel().select(item);
    }
    
    /**
     * Selects the given index. 
     * Since the SingleSelectionModel can only support having a single index selected 
     * at a time, this also causes any previously selected index to be unselected.
     * @param index index to select
     */
    public void select(int index) {
        getSelectionModel().select(index);
    }
    
    /**
     * Selects the first index.
     * Since the SingleSelectionModel can only support having a single index selected 
     * at a time, this also causes any previously selected index to be unselected.
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
     * Selects the last index. 
     * Since the SingleSelectionModel can only support having a single index selected 
     * at a time, this also causes any previously selected index to be unselected.
     */
    public void selectLast() {
        getSelectionModel().selectLast();
    }
    
    /**
     * Selects the next index. 
     * Since the SingleSelectionModel can only support having a single index selected 
     * at a time, this also causes any previously selected index to be unselected.
     */
    public void selectNext() {
        getSelectionModel().selectNext();
    }
    
    /**
     * Selects the previous index. 
     * Since the SingleSelectionModel can only support having a single index selected 
     * at a time, this also causes any previously selected index to be unselected.
     */
    public void selectPrevious() {
        getSelectionModel().selectPrevious();
    }
    
    /**
     * Focus the given index.
     * @param index index to focus
     */
    public void focus(int index) {
        ((ComboBoxListViewSkin)getSkin()).getListView().getFocusModel().focus(index);
    }
    
    /**
     * Scroll to the given index.
     * @param index index to scroll to
     */
    public void scrollTo(int index) {
        ((ComboBoxListViewSkin)getSkin()).getListView().scrollTo(index);
    }
    
    /**
     * Scroll to the given item.
     * @param item item to scroll to
     */
    public void scrollTo(T item) {
        ((ComboBoxListViewSkin)getSkin()).getListView().scrollTo(item);
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
