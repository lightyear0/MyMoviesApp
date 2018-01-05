package com.example.dhanaruban.mymoviesapp.utilities;

import android.net.Uri;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.OngoingStubbing;

import java.net.URL;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by dhanaruban on 04/01/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class NetworkUtilsTest {

    @Mock
    Uri builtUri;

    @Test
    public void buildUrl_isCorrect() throws Exception {
        NetworkUtils nu = new NetworkUtils();
        final Mockito mockito = new Mockito(Uri);
        OngoingStubbing<Uri> uriOngoingStubbing = when(builtUri.parse()).thenAnswer(mockito);

        URL res = nu.buildUrl("ds1328");
        assertEquals ("https://api.github.com/search/repositories/?q=ds1328&sort=stars" , res.toString());

    }
}
