/*
 * NAME: Aneesh Pamula
 * PID: A17319059
 */

/**
 * WebBrowser Class
 *
 * @author Aneesh Pamula
 * @since 5/8/2023
 */
public class WebBrowser {

    private String currentPage;
    private MyArrayList<String> history;
    private MyStack<String> prev;
    private MyStack<String> next;

    private static final String DEFAULT_PAGE = "google.com";

    /**
     * Instantiates new WebBrowser object with empty instance variables
     * and default current page
     */
    public WebBrowser() {
        currentPage = DEFAULT_PAGE;
        history = new MyArrayList<>();
        prev = new MyStack<>();
        next = new MyStack<>();
    }

    /**
     * Returns current page
     */
    public String getCurrentPage() {
        return currentPage;
    }

    /**
     * Opens a new page, pushing the current one to prev
     */
    public void openNewPage(String newLink) {
        next = new MyStack<>();
        prev.push(currentPage);
        currentPage = newLink;
        history.add(history.size(), currentPage);
    }

    /**
     * Goes to the previous page
     */
    public void previousPage() throws IllegalStateException {
        if(prev.isEmpty())
            throw new IllegalStateException();
        next.push(currentPage);
        currentPage = prev.pop();
        history.add(history.size(), currentPage);
    }

    /**
     * Goes to next page
     */
      public void nextPage() {
          if(next.isEmpty())
              throw new IllegalStateException();
        prev.push(currentPage);
        currentPage = next.pop();
        history.add(history.size(), currentPage);
    }

    /**
     * Clears prev and next and goes to default page, does not reset history
     */
    public void newTab() {
        next = new MyStack<>();
        prev = new MyStack<>();
        currentPage = DEFAULT_PAGE;
    }

    /**
     * Returns the history
     */
    public MyArrayList<String> getHistory() {
        return history;
    }

    /**
     * If link is in history, open new page with link. If not, return false
     */
    public boolean findLink(String link) {
       if(history.contains(link)){
           this.openNewPage(link);
           return true;
       }
       return false;
    }
}
