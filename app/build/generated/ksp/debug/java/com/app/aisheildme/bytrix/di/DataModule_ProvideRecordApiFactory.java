package com.app.aisheildme.bytrix.di;

import com.app.aisheildme.bytrix.data.remote.api.RecordApi;
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
public final class DataModule_ProvideRecordApiFactory implements Factory<RecordApi> {
  private final Provider<Retrofit> retrofitProvider;

  public DataModule_ProvideRecordApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public RecordApi get() {
    return provideRecordApi(retrofitProvider.get());
  }

  public static DataModule_ProvideRecordApiFactory create(Provider<Retrofit> retrofitProvider) {
    return new DataModule_ProvideRecordApiFactory(retrofitProvider);
  }

  public static RecordApi provideRecordApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.provideRecordApi(retrofit));
  }
}
