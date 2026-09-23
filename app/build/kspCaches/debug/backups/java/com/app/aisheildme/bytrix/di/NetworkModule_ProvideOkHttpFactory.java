package com.app.aisheildme.bytrix.di;

import com.app.aisheildme.bytrix.data.local.SessionStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;
import okhttp3.OkHttpClient;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class NetworkModule_ProvideOkHttpFactory implements Factory<OkHttpClient> {
  private final Provider<SessionStore> sessionStoreProvider;

  private final Provider<Json> jsonProvider;

  public NetworkModule_ProvideOkHttpFactory(Provider<SessionStore> sessionStoreProvider,
      Provider<Json> jsonProvider) {
    this.sessionStoreProvider = sessionStoreProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public OkHttpClient get() {
    return provideOkHttp(sessionStoreProvider.get(), jsonProvider.get());
  }

  public static NetworkModule_ProvideOkHttpFactory create(
      Provider<SessionStore> sessionStoreProvider, Provider<Json> jsonProvider) {
    return new NetworkModule_ProvideOkHttpFactory(sessionStoreProvider, jsonProvider);
  }

  public static OkHttpClient provideOkHttp(SessionStore sessionStore, Json json) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideOkHttp(sessionStore, json));
  }
}
