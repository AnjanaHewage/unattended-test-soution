package culturetrip.articles;

import culturetrip.articles.models.ArticleReference;
import culturetrip.articles.models.Image;
import culturetrip.articles.models.RichArticle;
import culturetrip.articles.models.Video;
import culturetrip.articles.service.ArticleApiService;
import culturetrip.articles.util.UrlUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.*;

public class ArticleEnricherImpl implements ArticleEnricher {

    private static final Logger LOG =  LoggerFactory.getLogger(ArticleEnricherImpl.class);

    private ArticleApiService articleApiService;

    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public ArticleEnricherImpl() {
        this.articleApiService = new ArticleApiService();
    }

    @Override
    public Future<RichArticle> enrichArticleWithId(String articleId) {

        return executor.submit(() -> {
            //Get article details
            ArticleReference articleReference= articleApiService.getArticleDetils(articleId);

            //NOTE: An assumption has been made that the Image and Video Ids are part of the their URLs

            //Get image details
            Image image = articleApiService.getImageDetails(UrlUtils.getIdFromUrl(articleReference.getHeroImageUrl()));

            //Get Video details
            Collection<Video> videos = new ArrayList<>();

            for (String url : articleReference.getVideoUrls()){
                Video videoFuture = articleApiService.getVideoDetails(UrlUtils.getIdFromUrl(url));
                videos.add(videoFuture);
            }
            //Create the article with the above and return
            return new RichArticle(articleReference.getId(), articleReference.getName(), image, videos);
        });
    }
}
