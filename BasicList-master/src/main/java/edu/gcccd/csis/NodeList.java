package edu.gcccd.csis;

import java.util.Iterator;

public final class NodeList<E> implements Iterable<E>, org.w3c.dom.NodeList {

    private Node<E> head;

    @Override
    public org.w3c.dom.Node item(int index) {
        return null;
    }

    /**
     * @return {@link int} number of nodes in this list
    */
    public int getLength() {
        int result = 0;
        Node<E> node = head;
        while (node != null) {
            node = node.getNext();
            result++;
        }
        return result;
    }

    /**
     * Appends the provided element to the end of this list.
     *
     * @param element {@link E} element to be appended.
     */
    public void append(final E element) {
        if (head == null) {
            head = new Node<>(element);
        } else {
            Node<E> node = head;
            while (node.getNext() != null) {
                node = node.getNext();
            }
            node.setNext(new Node<>(element));
        }
    }

    /**
     * Removes the 1st element from this list that is equal to the provided element.
     *
     * @param element {@link E} element to be removed.
     */
    public void remove(final E element) {
        if (head != null) {
            if (head.getElement().equals(element)) {
                head = head.getNext();
            } else {
                Node<E> node = head;
                while (node.getNext() != null) {
                    if (node.getNext().getElement().equals(element)) {
                        node.setNext(node.getNext().getNext());
                        break;
                    }
                    node = node.getNext();
                }
            }
        }
    }

    /**
     * Checks if this list contains the provided element.
     *
     * @param element {@link E} element to look for.
     * @return {@link boolean} - true if the provided element can be found in this list.
     */
    public boolean contains(final E element) {
        for (E e : this) {
            if (e.equals(element)) {
                return true;
            }
        }
        return false;
    }

    // implement Iterable<E> interface
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E obj = node.getElement();
                node = node.getNext();
                return obj;
            }


            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    public void addHead(E nodesElement) {
        Node<E> node = new Node<>(nodesElement);
        node.setNext(head);
        head = node;
    }

    public Node<E> atIndex(int i) {
        Node<E> node = head;
        if (i < 0 || i >= this.getLength()) {
            return null;
        } else {
            for (int j = 0; j!= i; j++) {
                if (node != null)
                    node = node.getNext();

            }
        }
        return  node;
    }
}