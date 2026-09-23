package com.app.aisheildme.bytrix.di;

import com.app.aisheildme.bytrix.data.remote.api.PagesApi;
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
public final class DataModule_ProvidePagesApiFactory implements Factory<PagesApi> {
  private final Provider<Retrofit> retrofitProvider;

  public DataModule_ProvidePagesApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PagesApi get() {
    return providePagesApi(retrofitProvider.get());
  }

  public static DataModule_ProvidePagesApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new DataModule_ProvidePagesApiFactory(retrofitProvider);
  }

  public static PagesApi providePagesApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.providePagesApi(retrofit));
  }
}
