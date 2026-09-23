package com.app.aisheildme.bytrix.data.repository;

import com.app.aisheildme.bytrix.data.remote.api.RecordApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class RecordRepository_Factory implements Factory<RecordRepository> {
  private final Provider<RecordApi> apiProvider;

  public RecordRepository_Factory(Provider<RecordApi> apiProvider) {
    this.apiProvider = apiProvider;
  }

  @Override
  public RecordRepository get() {
    return newInstance(apiProvider.get());
  }

  public static RecordRepository_Factory create(Provider<RecordApi> apiProvider) {
    return new RecordRepository_Factory(apiProvider);
  }

  public static RecordRepository newInstance(RecordApi api) {
    return new RecordRepository(api);
  }
}
