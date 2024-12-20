import java.util.NoSuchElementException;

class Node {
    String eventId;
    String userId;
    String eventName;
    Node next;

    public Node(String eventId, String userId, String eventName) {
        this.eventId = eventId;
        this.userId = userId;
        this.eventName = eventName;
        this.next = null;
    }

    public String toString() {
        return "Event: " + this.eventId + ", User: " + this.userId + ", Event name: "
                + this.eventName;
    }
}

public class EventManager {
    Node front, rear;

    public EventManager() {
        this.front = this.rear = null;
    }

    public String toString() {
        StringBuilder result = new StringBuilder("Event Queue:\n");
        Node current = front;

        while (current != null) {
            result.append(current.toString()).append("\n");
            current = current.next;
        }

        return result.toString();
    }

    // Method to add an element to the queue
    public void registerForEvent(String eventId, String userId, String eventName) {
        Node temp = new Node(eventId, userId, eventName);

        // If queue is empty, then new node is front and rear both
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }

        // Add the new node at the end of queue and change rear
        this.rear.next = temp;
        this.rear = temp;
    }

    // Method to remove an element from the queue
    public String cancelRegistration(String event) {
        // If queue is empty, throw NoSuchElementException
        if (this.front == null) {
            throw new NoSuchElementException("Queue is empty");
        }

        String canceledEventId = this.front.eventId;

        // Check if the front node's event ID matches the provided event ID
        if (canceledEventId.equals(event)) {
            this.front = this.front.next;

            // If front becomes null, then change rear also as null
            if (this.front == null) {
                this.rear = null;
            }

            return "Event: " + canceledEventId;
        } else {
            // Traverse the queue to find the node with the specified event ID
            Node current = this.front;
            Node previous = null;

            while (current != null && !current.eventId.equals(event)) {
                previous = current;
                current = current.next;
            }

            // If the event ID is found in the queue
            if (current != null) {
                previous.next = current.next;

                // If the removed node is the last node, update rear
                if (current == this.rear) {
                    this.rear = previous;
                }

                return "Event: " + canceledEventId;
            } else {
                // Event ID not found in the queue
                throw new NoSuchElementException("Event ID not found in the queue");
            }
        }
    }

    // Method to check if the queue is empty
    public boolean isEmpty() {
        return this.front == null;
    }
}