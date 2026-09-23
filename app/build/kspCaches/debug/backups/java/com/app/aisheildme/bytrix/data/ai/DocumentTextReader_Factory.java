package com.app.aisheildme.bytrix.data.ai;

import android.content.Context;
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
public final class DocumentTextReader_Factory implements Factory<DocumentTextReader> {
  private final Provider<Context> contextProvider;

  public DocumentTextReader_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DocumentTextReader get() {
    return newInstance(contextProvider.get());
  }

  public static DocumentTextReader_Factory create(Provider<Context> contextProvider) {
    return new DocumentTextReader_Factory(contextProvider);
  }

  public static DocumentTextReader newInstance(Context context) {
    return new DocumentTextReader(context);
  }
}
