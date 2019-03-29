package system;

/*
 * Linked List Node that contains info and link to next node.
 * It implements list interface
 * 
 */
public class LLNode<coupon> implements listinterface<coupon> {

	protected coupon info;
	protected LLNode<coupon> link;
	protected int num_el;

	public LLNode() {
		coupon info;
		LLNode<coupon> link;
	}

	public LLNode(coupon toadd) {
		this.info = toadd;
		link = null;
	}

	public coupon getInfo() {
		return info;
	}

	public void setInfo(coupon info) {
		this.info = info;
	}

	public LLNode<coupon> getLink() {
		return link;
	}

	public void setLink(LLNode<coupon> link) {
		this.link = link;
	}

	public boolean add(coupon element) {
		/*
		 * Precondition: List is not full Adds element to the back of list
		 */
		LLNode<coupon> newNode = new LLNode<>(element);
		LLNode<coupon> ref = new LLNode<>();
		ref = this;
		if (ref.getLink() == null) {
			ref.setLink(newNode);
		} else {
			while (ref.getLink() != null) {
				ref = ref.getLink();
			}
			ref.setLink(newNode);
		}
		num_el++;
		return true;

	}

	public boolean remove(coupon object) {
		/*
		 * Precondition: List is not empty
		 * Removes the object from the List
		 */
		
		LLNode<coupon> ref = new LLNode<>();
		LLNode<coupon> back_ref = new LLNode<>();
		boolean toreturn = false;
		
		ref = this.getLink();
		back_ref = this;
		
		while (ref != null){
			if (ref.info == object){
				back_ref.setLink(ref.getLink());
				toreturn = true;
				return toreturn;
			}
			else {
				back_ref = ref;
				ref = ref.getLink();
			}
		}
		
		return toreturn;
	}

	public int size() {
		/*
		 * Returns the number of elements in the list
		 */
		return num_el;
	}

	public boolean isFull() {
		return false;
	}
	/*
	 * Return true for a full list or vice versa
	 */

	public boolean contains(coupon o) {
		/*
		 * Precondition: List is not empty Returns true is the list contains the
		 * element o
		 */

		LLNode<coupon> pointer = new LLNode<>();
		pointer = this;
		boolean found = false;
		
		System.out.println("reached here");
		
		
		while (pointer != null) {
			if (pointer.getInfo().equals(o)) {
				found = true;
				return found;
			} else {
				pointer = pointer.getLink();
			}
		}

		System.out.println("also ");
		return found;
	}

}
