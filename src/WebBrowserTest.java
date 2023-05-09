import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WebBrowserTest {
    WebBrowser browser;
    @BeforeEach
    void setUp() {
        browser = new WebBrowser();
    }

    @Test
    void getCurrentPage() {
        assertEquals(browser.getCurrentPage(), "google.com");
        browser.openNewPage("canvas.com");
        assertEquals(browser.getCurrentPage(), "canvas.com");
        browser.openNewPage("somePage.com");
        browser.openNewPage("newpage.com");
        assertEquals(browser.getCurrentPage(), "newpage.com");
    }

    @Test
    void openNewPage() {
        browser.openNewPage("youtube.com");
        assertEquals(browser.getCurrentPage(), "youtube.com");
        browser.openNewPage("youtube.com");
        browser.openNewPage("somepage.com");
        assertEquals(browser.getCurrentPage(), "somepage.com");
        browser.openNewPage("canvas.com");
        browser.openNewPage("somepage.com");
        browser.openNewPage("gradescope.com");
        assertEquals(browser.getCurrentPage(), "gradescope.com");
    }

    @Test
    void previousPage() {
        browser.openNewPage("youtube.com");
        browser.previousPage();
        assertEquals(browser.getCurrentPage(), "google.com");
        browser.openNewPage("youtube.com");
        browser.openNewPage("somepage.com");
        browser.previousPage();
        assertEquals(browser.getCurrentPage(), "youtube.com");
        browser.openNewPage("canvas.com");
        browser.openNewPage("somepage.com");
        browser.openNewPage("gradescope.com");
        browser.previousPage();
        browser.previousPage();
        assertEquals(browser.getCurrentPage(), "canvas.com");
        boolean throwsException = false;
        browser.previousPage();
        browser.previousPage();
        try{
            browser.previousPage();
        }
        catch(IllegalStateException e){
            throwsException = true;
        }
        assertTrue(throwsException);
    }

    @Test
    void nextPage() {
        browser.openNewPage("youtube.com");
        browser.previousPage();
        browser.nextPage();
        assertEquals(browser.getCurrentPage(), "youtube.com");
        browser.openNewPage("dsc30.com");
        browser.openNewPage("zybooks.com");
        browser.previousPage();
        browser.previousPage();
        browser.nextPage();
        assertEquals(browser.getCurrentPage(), "dsc30.com");
        browser.nextPage();
        assertEquals(browser.getCurrentPage(), "zybooks.com");
        boolean throwsException = false;
        try{
            browser.nextPage();
        }
        catch(IllegalStateException e){
            throwsException = true;
        }
        assertTrue(throwsException);

    }

    @Test
    void newTab() {
        browser.openNewPage("dsc30.com");
        browser.openNewPage("docs.google.com");
        browser.previousPage();
        MyArrayList<String> historyBefore = browser.getHistory();
        browser.newTab();
        boolean throwsException = false;
        //Making sure there is nothing in prev
        try{
            browser.previousPage();
        }
        catch(IllegalStateException e){
            throwsException = true;
        }
        assertTrue(throwsException);
        //Making sure there is nothing in next
        throwsException = false;
        try{
            browser.nextPage();
        }
        catch(IllegalStateException e){
            throwsException = true;
        }
        assertTrue(throwsException);
        //History is conserved
        assertEquals(browser.getHistory(), historyBefore);
    }

    @Test
    void getHistory() {
        assertEquals(browser.getHistory() + "", "[]");
        browser.openNewPage("dsc30.com");
        assertEquals(browser.getHistory() + "", "[dsc30.com]");
        browser.openNewPage("zybooks.com");
        browser.openNewPage("docs.google.com");
        assertEquals(browser.getHistory() + "", "[dsc30.com -> zybooks.com -> docs.google.com]");
    }

    @Test
    void findLink() {
        browser.openNewPage("dsc30.com");
        browser.openNewPage("zybooks.com");
        browser.openNewPage("docs.google.com");
        assertTrue(browser.findLink("zybooks.com"));
        assertEquals(browser.getHistory() + "", "[dsc30.com -> zybooks.com -> docs.google.com " +
                "-> zybooks.com]");
        assertFalse(browser.findLink("canvas.com"));
        browser.newTab();
        assertTrue(browser.findLink("dsc30.com"));
    }
}