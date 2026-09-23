package com.app.aisheildme.bytrix.core.util;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
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
public final class FileOpener_Factory implements Factory<FileOpener> {
  private final Provider<OkHttpClient> clientProvider;

  public FileOpener_Factory(Provider<OkHttpClient> clientProvider) {
    this.clientProvider = clientProvider;
  }

  @Override
  public FileOpener get() {
    return newInstance(clientProvider.get());
  }

  public static FileOpener_Factory create(Provider<OkHttpClient> clientProvider) {
    return new FileOpener_Factory(clientProvider);
  }

  public static FileOpener newInstance(OkHttpClient client) {
    return new FileOpener(client);
  }
}
