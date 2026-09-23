package com.app.aisheildme.bytrix.di;

import com.app.aisheildme.bytrix.data.remote.api.DocumentApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import retrofit2.Retrofit;

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
public final class DataModule_ProvideDocumentApiFactory implements Factory<DocumentApi> {
  private final Provider<Retrofit> retrofitProvider;

  public DataModule_ProvideDocumentApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public DocumentApi get() {
    return provideDocumentApi(retrofitProvider.get());
  }

  public static DataModule_ProvideDocumentApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new DataModule_ProvideDocumentApiFactory(retrofitProvider);
  }

  public static DocumentApi provideDocumentApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideDocumentApi(retrofit));
  }
}
