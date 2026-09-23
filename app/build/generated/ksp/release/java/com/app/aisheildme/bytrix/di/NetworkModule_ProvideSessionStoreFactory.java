package com.app.aisheildme.bytrix.di;

import android.content.Context;
import com.app.aisheildme.bytrix.data.local.SessionStore;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.serialization.json.Json;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class NetworkModule_ProvideSessionStoreFactory implements Factory<SessionStore> {
  private final Provider<Context> contextProvider;

  private final Provider<Json> jsonProvider;

  public NetworkModule_ProvideSessionStoreFactory(Provider<Context> contextProvider,
      Provider<Json> jsonProvider) {
    this.contextProvider = contextProvider;
    this.jsonProvider = jsonProvider;
  }

  @Override
  public SessionStore get() {
    return provideSessionStore(contextProvider.get(), jsonProvider.get());
  }

  public static NetworkModule_ProvideSessionStoreFactory create(Provider<Context> contextProvider,
      Provider<Json> jsonProvider) {
    return new NetworkModule_ProvideSessionStoreFactory(contextProvider, jsonProvider);
  }

  public static SessionStore provideSessionStore(Context context, Json json) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideSessionStore(context, json));
  }
}
