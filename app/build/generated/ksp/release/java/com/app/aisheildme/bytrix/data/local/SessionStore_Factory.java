package com.app.aisheildme.bytrix.data.local;

import android.content.Context;
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
public final class SessionStore_Factory implements Factory<SessionStore> {
  private final Provider<Context> contextProvider;

  private final Provider<Json> jsonProvider;

  public SessionStore_Factory(Provider<Context> contextProvider, Provider<Json> jsonProvider) {
    this.contextProvider = contextProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public SessionStore get() {
    return newInstance(contextProvider.get(), jsonProvider.get());
  }

  public static SessionStore_Factory create(Provider<Context> contextProvider,
      Provider<Json> jsonProvider) {
    return new SessionStore_Factory(contextProvider, jsonProvider);
  }

  public static SessionStore newInstance(Context context, Json json) {
    return new SessionStore(context, json);
  }
}
