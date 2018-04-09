package culturetrip.articles.service;

import culturetrip.articles.clients.ArticleRepositoryClient;
import culturetrip.articles.clients.AssetsServiceClient;
import culturetrip.articles.models.ArticleReference;
import culturetrip.articles.models.Image;
import culturetrip.articles.models.Video;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Future;

import static org.mockito.Mockito.when;

public class ArticleApiServiceTest {

    @InjectMocks
    ArticleApiService articleApiService;

    @Mock
    ArticleRepositoryClient articleRepositoryClient;

    @Mock
    AssetsServiceClient assetsServiceClient;

    @Mock
    Future<ArticleReference> articleReferenceFuture;

    @Mock
    Future<Image> imageFuture;

    @Mock
    Future<Video> videoFuture;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetArticleDetails() throws Exception{
        when(articleRepositoryClient.getArticleReferenceForId("1"))
                .thenReturn(articleReferenceFuture);
        when(articleReferenceFuture.get()).thenReturn(createArticleReference());
        when(articleReferenceFuture.isDone()).thenReturn(true);

        assert(articleApiService.getArticleDetils("1").getName().equals("article1"));
    }

    @Test
    public void shouldGetImageDetails() throws Exception{
        when(assetsServiceClient.getImageById("1")).thenReturn(imageFuture);
        when(imageFuture.get()).thenReturn(new Image("1", "image1"));
        when(imageFuture.isDone()).thenReturn(true);

        assert(articleApiService.getImageDetails("1").getAltText().equals("image1"));
    }

    @Test
    public void shouldGetVideoDetail() throws Exception{
        when(assetsServiceClient.getVideoById("1")).thenReturn(videoFuture);
        when(videoFuture.get()).thenReturn(new Video("1", "hero1"));
        when(videoFuture.isDone()).thenReturn(true);
    }

    private ArticleReference createArticleReference(){

        Collection<String> vidoeUrlList = new ArrayList<>();

        vidoeUrlList.add("http://culturetrip.com/vidoes/1");
        vidoeUrlList.add("http://culturetrip.com/vidoes/2");
        vidoeUrlList.add("http://culturetrip.com/vidoes/3");

        return  new ArticleReference("1", "article1",
                "http://culturetrip.com/images/heros/1", vidoeUrlList);
    }

}
