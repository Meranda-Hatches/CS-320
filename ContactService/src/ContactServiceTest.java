import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {
    private ContactService service;
    private Contact contact;

    @BeforeEach
    public void setup() {
        service = new ContactService();
        contact = new Contact("001", "Jane", "Smith", "5551234567", "456 Maple St");
        service.addContact(contact);
    }

    @Test
    public void testAddDuplicateContact() {
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact));
    }

    @Test
    public void testDeleteContact() {
        service.deleteContact("001");
        assertThrows(IllegalArgumentException.class, () -> service.deleteContact("001"));
    }

    @Test
    public void testUpdateFields() {
        service.updateFirstName("001", "Janet");
        service.updateLastName("001", "Johnson");
        service.updatePhone("001", "1234567890");
        service.updateAddress("001", "789 Oak Ave");

        assertEquals("Janet", contact.getFirstName());
        assertEquals("Johnson", contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals("789 Oak Ave", contact.getAddress());
    }

    @Test
    public void testUpdateInvalidContactId() {
        assertThrows(IllegalArgumentException.class, () -> service.updateFirstName("999", "NewName"));
    }
}