package com.app.aisheildme.bytrix.di;

import com.app.aisheildme.bytrix.data.remote.api.PatientProfileApi;
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
public final class DataModule_ProvidePatientProfileApiFactory implements Factory<PatientProfileApi> {
  private final Provider<Retrofit> retrofitProvider;

  public DataModule_ProvidePatientProfileApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public PatientProfileApi get() {
    return providePatientProfileApi(retrofitProvider.get());
  }

  public static DataModule_ProvidePatientProfileApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new DataModule_ProvidePatientProfileApiFactory(retrofitProvider);
  }

  public static PatientProfileApi providePatientProfileApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(DataModule.INSTANCE.providePatientProfileApi(retrofit));
  }
}
