package org.kiwix.kiwixmobile.network;

import org.kiwix.kiwixmobile.library.entity.LibraryNetworkEntity;
import org.kiwix.kiwixmobile.library.entity.MetaLinkNetworkEntity;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface KiwixService {
  @GET("/library/library_zim.xml") Observable<LibraryNetworkEntity> getLibrary();

  @GET Observable<MetaLinkNetworkEntity> getMetaLinks(@Url String url);


  /******** Helper class that sets up new services *******/
  class ServiceCreator {

    public static KiwixService newHacklistService(OkHttpClient okHttpClient, String baseUrl) {
      Retrofit retrofit = new Retrofit.Builder()
          .baseUrl(baseUrl)
          .client(okHttpClient)
          .addConverterFactory(SimpleXmlConverterFactory.create())
          .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
          .build();

      return retrofit.create(KiwixService.class);
    }
  }
}
