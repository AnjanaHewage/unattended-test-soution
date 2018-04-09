package culturetrip.articles.util;

import org.junit.Before;
import org.junit.Test;

public class URLUtilTest {

    private UrlUtils urlUtils;

    @Before
    public void setUp() throws Exception {
        urlUtils = new UrlUtils();
    }

    @Test
    public void shouldReturnIdFromUrl(){
        assert(UrlUtils.getIdFromUrl("http://culturetrip.com/videos/2").equals("2"));
    }
}
