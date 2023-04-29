package BorrowBook;

public class BorrowedBook {
    private String id;
    private String borrowerName;
    private String dateBurrowed;
    private String dueDate;
    private String dateReturned;

    public BorrowedBook(String id, String borrowerName, String dateBurrowed, String dueDate, String dateReturned) {
        this.id = id;
        this.borrowerName = borrowerName;
        this.dateBurrowed = dateBurrowed;
        this.dueDate = dueDate;
        this.dateReturned = dateReturned;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBorrowerName() {
        return borrowerName;
    }

    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }

    public String getDateBurrowed() {
        return dateBurrowed;
    }

    public void setDateBurrowed(String dateBurrowed) {
        this.dateBurrowed = dateBurrowed;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDateReturned() {
        return dateReturned;
    }

    public void setDateReturned(String dateReturned) {
        this.dateReturned = dateReturned;
    }
}
