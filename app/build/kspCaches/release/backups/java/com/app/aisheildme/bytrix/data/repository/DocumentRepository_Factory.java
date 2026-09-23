package com.app.aisheildme.bytrix.data.repository;

import android.content.Context;
import com.app.aisheildme.bytrix.data.remote.api.DocumentApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class DocumentRepository_Factory implements Factory<DocumentRepository> {
  private final Provider<DocumentApi> apiProvider;

  private final Provider<Context> contextProvider;

  public DocumentRepository_Factory(Provider<DocumentApi> apiProvider,
      Provider<Context> contextProvider) {
    this.apiProvider = apiProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public DocumentRepository get() {
    return newInstance(apiProvider.get(), contextProvider.get());
  }

  public static DocumentRepository_Factory create(Provider<DocumentApi> apiProvider,
      Provider<Context> contextProvider) {
    return new DocumentRepository_Factory(apiProvider, contextProvider);
  }

  public static DocumentRepository newInstance(DocumentApi api, Context context) {
    return new DocumentRepository(api, context);
  }
}
