package culturetrip.articles;

import culturetrip.articles.clients.ArticleRepositoryClient;
import culturetrip.articles.clients.AssetsServiceClient;
import culturetrip.articles.models.ArticleReference;
import culturetrip.articles.models.RichArticle;
import culturetrip.articles.service.ArticleApiService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;

import static org.mockito.Mockito.*;

public class ArticleEnricherImplTest {

    @InjectMocks
    ArticleEnricherImpl articleEnricher;

    @Mock
    ArticleApiService articleApiService;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldGetEnrichedArticle() throws Exception{
        when(articleApiService.getArticleDetils("1")).thenReturn(createArticleReference());

        assertThat(articleEnricher.enrichArticleWithId("1"),is(notNullValue()) );
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
