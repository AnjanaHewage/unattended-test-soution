package culturetrip.articles.util;

public class UrlUtils {

    public static String getIdFromUrl(String url){
        int index = url.lastIndexOf('/');
        return url.substring(index+1, url.length());
    }

}
