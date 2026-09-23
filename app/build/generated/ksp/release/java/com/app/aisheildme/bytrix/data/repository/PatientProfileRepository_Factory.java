package com.app.aisheildme.bytrix.data.repository;

import com.app.aisheildme.bytrix.data.remote.api.PatientProfileApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;

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
public final class PatientProfileRepository_Factory implements Factory<PatientProfileRepository> {
  private final Provider<PatientProfileApi> apiProvider;

  private final Provider<Json> jsonProvider;

  public PatientProfileRepository_Factory(Provider<PatientProfileApi> apiProvider,
      Provider<Json> jsonProvider) {
    this.apiProvider = apiProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public PatientProfileRepository get() {
    return newInstance(apiProvider.get(), jsonProvider.get());
  }

  public static PatientProfileRepository_Factory create(Provider<PatientProfileApi> apiProvider,
      Provider<Json> jsonProvider) {
    return new PatientProfileRepository_Factory(apiProvider, jsonProvider);
  }

  public static PatientProfileRepository newInstance(PatientProfileApi api, Json json) {
    return new PatientProfileRepository(api, json);
  }
}
