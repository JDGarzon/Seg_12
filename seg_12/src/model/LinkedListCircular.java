package model;

public class LinkedListCircular<E> {
	private Node first;
	private Node last;
	private Node actualNode;
	private int size;
	public LinkedListCircular() {
		size=0;
	}
	
	public E getLast() {
		return last.getValue();
	}
	
	
	public void add(E toAdd) {
		if(first==null&&last==null) {
			first=new Node(toAdd);
			last=first;
			first.setPrev(last);
			last.setNext(first);
			size++;
		}else {
			Node newNode=new Node(toAdd);
			last.setNext(newNode);
			newNode.setPrev(last);
			last=newNode;
			last.setNext(first);
			first.setPrev(last);
			size++;
		}
	}
	
	public boolean search(E value) {
		if(first!=null) {
			return first.search(value);
		}
		return false;
	}
	
	public boolean delete(E value) {
		if(first!=null) {
			return first.delete(value);
		}
		return false;
	}
	
	public String toString() {
		String out="[";
		if(first!=null) {
			out+=first;
		}
		out+="]";
		return out;
	}
	
	public boolean isEmpty() {
		return first==null;
	}
	
	public E getActual() {
		if(actualNode==null) {
			return null;
		}
		return actualNode.getValue();
	}
	
	public void reset() {
		actualNode=first;
	}
	
	public boolean up() {
		if(first==null) {
			return false;
		}else {
			actualNode=actualNode.getNext();
			return true;
		}
		
	}
	public boolean down() {
		if(first==null) {
			return false;
		}else {
			actualNode=actualNode.getPrev();
			return true;
		}
		
	}

	public int getSize() {
		return size;
	}

	private class Node{
		Node next;
		Node prev;
		E value;
		@SuppressWarnings("unused")
		public Node() {
			
		}
		
		public Node(E element) {
			value=element;
		}
		
		public Node getNext() {
			return next;
		}
		public Node getPrev() {
			return prev;
		}
		public E getValue() {
			return value;
		}
		
		public void setNext(Node node) {
			this.next=node;
		}
		
		public void setPrev(Node node) {
			prev=node;
		}
		
		public String toString() {
			String out="";
			if(next==first) {
				return value+"";
			}else {
				out+=value+","+next;
			}
			return out;
		}
		
		public boolean search(E value) {
			if(next==first) {
				return false;
			}else {
				if(this.value==value) {
					return true;
				}else return next.search(value);
			}
		}
		
		public boolean delete(E value) {
			if(next==first) {
				if(first.getValue()==value&&last.getValue()==value) {
					first=null;
					last=null;
					size--;
					return true;
				}else return false;
			}else {
				 if(first.getValue()==value) {
					first=first.getNext();
					first.setPrev(last);
					last.setNext(first);
					size--;
					return true;
				}else if(last.getValue()==value) {
					last=last.getPrev();
					last.setNext(first);
					first.setPrev(last);
					size--;
					return true;
				}else if(this.value==value){
					prev.setNext(next);
					next.setPrev(prev);
					size--;
					return true;
				}else {
					return next.delete(value);
				}
			}
		}
	}
}
